package com.anubhav.Floracure.ui.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anubhav.FloraCure.R
import com.anubhav.FloraCure.databinding.FragmentScanBinding
import com.github.drjacky.imagepicker.ImagePicker


class ScanFragment : Fragment() {

    lateinit var  toggle : ActionBarDrawerToggle

    private var _binding : FragmentScanBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentScanBinding.inflate(inflater,container,false)
        val view = binding.root
        toggle = ActionBarDrawerToggle(requireActivity(),binding.drawerLayout,0,0)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.item1 -> findNavController().navigate(R.id.action_scanFragment_to_firstPage)
                R.id.item2 -> findNavController().navigate(R.id.action_scanFragment_to_secondPage)
                R.id.item3 -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/anubhav811/FloraCure")))
            }
            true
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                // Use the uri to load the image
                val action =
                    ScanFragmentDirections.actionScanFragmentToResultFragment(uri.toString())
                findNavController().navigate(action)
            }
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

        val header = binding.navView.getHeaderView(0)
        val closeBtn = header.findViewById<ImageButton>(R.id.closeBtn)
        closeBtn.setOnClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }

        binding.openButton.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)

        }



}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
