package com.frank.checkit.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.frank.checkit.R
import com.frank.checkit.adapters.ItemClickListener
import com.frank.checkit.adapters.ProductAdapater
import com.frank.checkit.databinding.FragmentFirstBinding
import com.frank.checkit.model.FakersData
import com.frank.checkit.network.*
import com.frank.checkit.viewmodel.ProductViewmodel


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class FirstFragment : Fragment() ,ItemClickListener{
    private  var _binding :FragmentFirstBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar?.visibility = View.VISIBLE

        val viewModel = ViewModelProvider(this).get(ProductViewmodel::class.java)
        binding.recyclerViewVertical.apply {
            binding.progressBar?.visibility = View.GONE
            viewModel.getFakersData().observe(viewLifecycleOwner, { getFakersData ->
                adapter = ProductAdapater(getFakersData, this@FirstFragment)
                layoutManager = LinearLayoutManager(context)


            })

        }
        binding.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(queryString: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(queryString: String?): Boolean {
                viewModel.getFakersData().observe(viewLifecycleOwner, { getFakersData ->
                    ProductAdapater(getFakersData, this@FirstFragment)
                            .filter
                            .filter(queryString)

                })

                return true
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(data: FakersData) {
        val bundle = bundleOf(
                "price" to data.price,
                "title" to data.title,
                "description" to data.description,
                "image" to data.image,
        )

        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)


    }


}