package com.example.ch_machinetest.productdetails.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.ch_machinetest.R
import com.example.ch_machinetest.databinding.FragmentProductDetailsBinding
import com.example.ch_machinetest.productdetails.model.ProductResponse
import com.example.ch_machinetest.productdetails.presenter.ProductsPresenter
import com.example.ch_machinetest.sharedpreference.SharedPreference


class ProductDetailsFragment : Fragment(), ProductInterface, View.OnClickListener {

    lateinit var binding: FragmentProductDetailsBinding
    lateinit var presenter: ProductsPresenter
    private lateinit var sharedPreference: SharedPreference
    var selectedProductId: Int? = null
    var productDetail = ProductResponse()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        presenter = ProductsPresenter(requireContext(), this)
        sharedPreference = SharedPreference(requireContext())
        binding.arrowBack.setOnClickListener(this)

        selectedProductId?.let {
            binding.descriptionBar.visibility = View.VISIBLE
            presenter.getProductDetails(it)
        }

        return binding.root
    }

    override fun productDetailsSuccess() {
        binding.descriptionBar.visibility = View.GONE
        productDetail = presenter.getDeatils()!!
        binding.productName.text = productDetail.barnd
        binding.rating.text = productDetail.rating.toString()
        binding.rate.text = productDetail.price.toString()
        binding.offer.text = productDetail.discountPercentage.toString()
        binding.description.text = productDetail.description
        Glide.with(requireContext()).load(productDetail.thumbnail).into(binding.product)

    }

    override fun productDetailsError(error: String) {

        showDialog(error)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.arrow_back -> {
                parentFragmentManager.popBackStackImmediate()
            }
        }

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