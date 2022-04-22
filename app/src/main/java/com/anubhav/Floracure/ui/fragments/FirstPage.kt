package com.anubhav.Floracure.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anubhav.FloraCure.databinding.FragmentFirstPageBinding


class FirstPage : Fragment() {

    private var _binding : FragmentFirstPageBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentFirstPageBinding.inflate(inflater,container,false)
        val view = binding.root
        return view    }


}