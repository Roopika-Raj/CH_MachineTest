package com.example.ch_machinetest.home.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch_machinetest.R
import com.example.ch_machinetest.databinding.FragmentHomeBinding
import com.example.ch_machinetest.home.model.Product
import com.example.ch_machinetest.home.presenter.HomePresenter

class HomeFragment : Fragment(), IHomeFragment {

    lateinit var binding: FragmentHomeBinding
    lateinit var listingAdapter: ProductsListingAdapter
    lateinit var imageListingAdapter: ImageListingAdapter
    var category: ArrayList<String> = ArrayList()
    lateinit var homePresenter: HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homePresenter = HomePresenter(requireContext(), this)
        homePresenter.allProducts()

        categoryDetails()

        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.btnRecyclerview.layoutManager = linearLayoutManager
        listingAdapter = ProductsListingAdapter(requireContext(), category)

        binding.btnRecyclerview.adapter = listingAdapter

        val imageLinearlyt = LinearLayoutManager(context)
        binding.imageRecyclerview.layoutManager = imageLinearlyt
        imageListingAdapter = ImageListingAdapter(requireContext())
        binding.imageRecyclerview.adapter = imageListingAdapter


        return binding.root
    }

    fun categoryDetails() {
        category.add("All")
        category.add("iPhone")
        category.add("Samsung")
        category.add("Oppo")
        category.add("Nokia")
        category.add("Redmi")
        category.add("Realme")

    }

    fun imageListing(allProducts: ArrayList<Product>) {
        imageListingAdapter.setData(allProducts)
        imageListingAdapter.notifyDataSetChanged()

    }

    override fun getAllProductsSuccess() {
        val allProducts = homePresenter.getAllCategories()
        if (allProducts != null) {
            imageListing(allProducts)
        }

        println("getAllProductsSuccess")
    }

    override fun getAllProductsFailure(error: String) {
        showDialog(error)
    }

    private fun showDialog(message: String) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton(resources.getString(R.string.ok),
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()
                })
        val alert = dialogBuilder.create()
        alert.setTitle(getString(R.string.codingHands))
        alert.show()
    }


}