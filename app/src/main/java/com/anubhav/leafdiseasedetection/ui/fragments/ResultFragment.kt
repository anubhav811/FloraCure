package com.anubhav.leafdiseasedetection.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.anubhav.leafdiseasedetection.R
import com.anubhav.leafdiseasedetection.databinding.FragmentResultBinding
import com.anubhav.leafdiseasedetection.ml.MobileNetModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder


class ResultFragment : Fragment() {

    private var _binding : FragmentResultBinding? = null
    private val binding get() = _binding!!

    lateinit var bitmap: Bitmap

    val args: ResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val labels = FileUtil.loadLabels(requireContext(), "labels.txt")
        val names = FileUtil.loadLabels(requireContext(), "names.txt")


        if (arguments != null) {
            binding.scannedImage.setImageURI(args.bitmapUriToBeSent.toUri())
        }

        var bitmap = MediaStore.Images.Media.getBitmap(
            requireContext().contentResolver,
            args.bitmapUriToBeSent.toUri()
        )

        bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true)

        val model = MobileNetModel.newInstance(requireContext())
        val byteBuffer = ByteBuffer.allocateDirect(3 * 224 * 224 * 4)

        val intValues = IntArray(224 * 224)
        bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight())
        var pixel = 0

        for (i in 0 until 224) {
            for (j in 0 until 224) {
                val `val` = intValues[pixel++] // RGB
                byteBuffer.putFloat((`val` shr 16 and 0xFF) * (1f / 1))
                byteBuffer.putFloat((`val` shr 8 and 0xFF) * (1f / 1))
                byteBuffer.putFloat((`val` and 0xFF) * (1f / 1))
            }
        }
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)

        inputFeature0.loadBuffer(byteBuffer)

        val outputs  = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        val confidences = outputFeature0.floatArray

        var maxPos = 0
        var maxConfidence = 0f
        for (i in confidences.indices) {
            if (confidences[i] > maxConfidence) {
                maxConfidence = confidences[i]
                maxPos = i
            }
        }

        binding.diseaseText.text = String.format(names[maxPos] + "\nCondition : "+ labels[maxPos])

        model.close()


        binding.btnTestAgain.setOnClickListener {
            findNavController().apply {
                navigate(R.id.action_resultFragment_to_scanFragment)
            }
        }

        binding.btnRemedies.setOnClickListener {
            var intent  = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=${names[maxPos]} diseases and its remedies"))
            startActivity(intent)
        }

    }

}