package com.anubhav.Floracure.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.anubhav.FloraCure.R
import com.anubhav.FloraCure.databinding.FragmentResultBinding
import com.anubhav.Floracure.ui.activities.Classifier
import java.io.File
import java.nio.ByteBuffer


class ResultFragment : Fragment() {

    private var _binding : FragmentResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var results: Classifier.Recognition
    private lateinit var mClassifier: Classifier
    private lateinit var mBitmap: Uri
    private lateinit var bitmap : Bitmap
    private var list : ArrayList<String> = ArrayList()
    private var labels : ArrayList<String> = ArrayList()
    private lateinit var assetManager : AssetManager

    private val args: ResultFragmentArgs by navArgs()

    private val mInputSize = 224
    private val mModelPath = "plant_disease_model.tflite"
    private val mLabelPath = "labels.txt"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }



    @SuppressLint("ResourceType", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        assetManager = activity?.assets!!
        list = assetManager.open("names.txt").bufferedReader().useLines { it.toList() } as ArrayList<String>
        labels = assetManager.open("labels.txt").bufferedReader().useLines { it.toList() } as ArrayList<String>

        val assets = activity?.assets
        mClassifier = assets?.let { Classifier(it, mModelPath, mLabelPath, mInputSize) }!!

        mBitmap = args.bitmap.toUri()

        bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, mBitmap)

        bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true);
        binding.scannedImage.setImageBitmap(bitmap)


        if (mClassifier.recognizeImage(bitmap).firstOrNull() == null) {
            binding.diseaseText.text= "No leaf detected. Please try again and take a clearer picture.\n"
        }
        else {
            binding.diseaseText.text =
                list[mClassifier.recognizeImage(bitmap).firstOrNull()!!.id.toInt()] + "\n Condition: " + labels[mClassifier.recognizeImage(bitmap).firstOrNull()!!.id.toInt()] + "\n Confidence: " + mClassifier.recognizeImage(bitmap).firstOrNull()!!.confidence * 100 + " %"
        }

        binding.btnTestAgain.setOnClickListener {
            findNavController().apply {
                navigate(R.id.action_resultFragment_to_scanFragment)
            }
        }

        binding.btnRemedies.setOnClickListener {
            val intent  = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=${labels[mClassifier.recognizeImage(bitmap).firstOrNull()!!.id.toInt()]} and its remedies"))
            startActivity(intent)
        }

    }

}