package com.anubhav.Floracure.ui.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anubhav.FloraCure.R
import com.anubhav.FloraCure.databinding.FragmentScanBinding
import com.github.drjacky.imagepicker.ImagePicker
import org.checkerframework.checker.units.qual.Length
import java.io.ByteArrayOutputStream
import java.io.IOException


class ScanFragment : Fragment() {

    lateinit var toggle: ActionBarDrawerToggle

    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!
    private lateinit var mBitmap: Uri


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScanBinding.inflate(inflater, container, false)
        val view = binding.root
        toggle = ActionBarDrawerToggle(requireActivity(), binding.drawerLayout, 0, 0)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item1 -> findNavController().navigate(R.id.action_scanFragment_to_firstPage)
                R.id.item2 -> findNavController().navigate(R.id.action_scanFragment_to_secondPage)
                R.id.item3 -> startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/anubhav811/FloraCure")
                    )
                )
            }
            true
        }

        return view
    }


    @SuppressLint("QueryPermissionsNeeded")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val mBitmap = it.data?.data!!
                // Use the uri to load the image
                val action =
                    ScanFragmentDirections.actionScanFragmentToResultFragment(mBitmap.toString())
                findNavController().navigate(action)

            }
        }

        binding.btnUpload.setOnClickListener {

            launcher.launch(
                activity?.let { it1 ->
                    ImagePicker.with(it1)
                        //...
                        .galleryOnly()
                        .createIntent()
                }
            )


//            val imagePickerIntent = Intent(Intent.ACTION_GET_CONTENT)
//            imagePickerIntent.type = "image/*"
//            startActivityForResult(imagePickerIntent, 100)

        }

        binding.btnCamera.setOnClickListener {

            launcher.launch(
                activity?.let { it1 ->
                    ImagePicker.with(it1)
                        //...
                        .cameraOnly()
                        .createIntent()
                }
            )
//            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            startActivityForResult(callCameraIntent, 101)
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

//    @Deprecated("Deprecated in Java")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode == 101){
//            if(resultCode == Activity.RESULT_OK && data!= null) {
//                val img = data.extras?.get("data")
//                mBitmap = getImageUriFromBitmap(requireContext(), img as Bitmap)
//                val action =
//                    ScanFragmentDirections.actionScanFragmentToResultFragment(mBitmap.toString())
//                findNavController().navigate(action)
//
//            }
//            else{
//                Toast.makeText(activity,"Camera cancelled",Toast.LENGTH_SHORT).show()
//            }
//        } else if(requestCode == 100) {
//            if (data != null) {
//                val uri = data.data
//                if (uri != null) {
//                    mBitmap = uri
//                }
//                val action =
//                    ScanFragmentDirections.actionScanFragmentToResultFragment(mBitmap.toString())
//                findNavController().navigate(action)
//
//            }
//            else {
//                Toast.makeText(context, "Some error occured", Toast.LENGTH_SHORT).show()
//            }
//    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun getImageUriFromBitmap(context: Context, bitmap: Bitmap): Uri{
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
        return Uri.parse(path.toString())
    }



}
