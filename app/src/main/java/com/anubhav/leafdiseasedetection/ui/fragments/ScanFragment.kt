package com.anubhav.leafdiseasedetection.ui.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.media.ThumbnailUtils
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.anubhav.leafdiseasedetection.R
import com.anubhav.leafdiseasedetection.ml.Model
import kotlinx.android.synthetic.main.fragment_scan.*
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer


class ScanFragment : Fragment() {

    private val TAG = "ScanFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_scan, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_upload.setOnClickListener {
            var intent = Intent(Intent.ACTION_GET_CONTENT)

            intent.type = "image/*"

            startActivityForResult(intent, 100)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var bitmapUriToBeSent = data?.data

        var bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, bitmapUriToBeSent)

        bitmap = Bitmap.createScaledBitmap(bitmap,224,224,false)

            val action =
                ScanFragmentDirections.actionScanFragmentToResultFragment(bitmapUriToBeSent.toString(),bitmap)
        findNavController().navigate(action)
    }

      }

