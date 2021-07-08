package com.frank.checkit.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.frank.checkit.databinding.FragmentSecondBinding
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */


class SecondFragment : Fragment() {
    private  var _binding : FragmentSecondBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val image = arguments?.getString("image")
        val title = arguments?.getString("title")
        val price = arguments?.getDouble("price")


        binding.itemTitle.apply { text = title }
        binding.fakers?.apply { text = "$" + price.toString() }
        binding.fakers2?.apply { text = "$" + price.toString() }

        Picasso.get().load(image).into(binding.itemImage)
        binding.floatingActionButton2?.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Product Name : $title \n " +
                    "Fakers Store: $${price.toString()} \n " +
                    "Zuri Store: $${price.toString()}")
            intent.type = "text/plain"
            startActivity(intent)
        }






    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}