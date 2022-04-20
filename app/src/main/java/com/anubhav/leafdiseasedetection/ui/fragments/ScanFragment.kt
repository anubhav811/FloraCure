package com.anubhav.leafdiseasedetection.ui.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.storage.StorageManager
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anubhav.leafdiseasedetection.databinding.FragmentScanBinding
import com.github.drjacky.imagepicker.ImagePicker
import java.io.File


class ScanFragment : Fragment() {

    private var _binding : FragmentScanBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentScanBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                // Use the uri to load the image
                val action = ScanFragmentDirections.actionScanFragmentToResultFragment(uri.toString())
                findNavController().apply {
                    navigate(action)
                }}
        }

        binding.btnUpload.setOnClickListener {

            launcher.launch(
                ImagePicker.with(requireActivity())
                    //...
                    .galleryOnly()
                    .createIntent()
            )
        }

        binding.btnCamera.setOnClickListener {
            launcher.launch(
                ImagePicker.with(requireActivity())
                    //...
                    .cameraOnly() // or galleryOnly()
                    .createIntent()
            )
        }
}}
