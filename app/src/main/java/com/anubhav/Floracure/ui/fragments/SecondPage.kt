package com.anubhav.Floracure.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anubhav.FloraCure.databinding.FragmentSecondPageBinding



class SecondPage : Fragment() {
    private var _binding : FragmentSecondPageBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentSecondPageBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.howToText.text = "1 . Tap on Upload Image to upload from gallery or Take a picture from your device's camera.\n\n2 . After viewing the result you can look for more info about the device and its diseases.\n"
    }

}