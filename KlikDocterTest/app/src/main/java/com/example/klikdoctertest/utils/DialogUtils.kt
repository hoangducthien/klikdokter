package com.example.klikdoctertest.utils

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import com.example.domain.product.Product
import com.example.klikdoctertest.R
import com.example.klikdoctertest.databinding.ViewUpdateProductBinding


fun showConfirmDialog(context: Context, message: String, callback: (Boolean) -> Unit) {
    androidx.appcompat.app.AlertDialog.Builder(context)
        .setMessage(message)
        .setPositiveButton("Yes") { _, _ ->
            callback(true)
        }
        .setNegativeButton("No") { _, _ ->
            callback(false)
        }
        .create()
        .show()
}

fun showProductUpdateDialog(context: Context, product: Product?, callback: (Product) -> Unit) {
    val binding = ViewUpdateProductBinding.inflate(LayoutInflater.from(context))

    product?.apply {
        binding.edtSku.setText(sku)
        binding.edtSku.isEnabled = false
        binding.edtName.setText(productName)
        binding.edtQuantity.setText(qty.toString())
        binding.edtPrice.setText(price.toString())
        binding.edtUnit.setText(unit)
        binding.edtStatus.setText(status.toString())
    }

    androidx.appcompat.app.AlertDialog.Builder(context)
        .setTitle(if (product == null) R.string.add_product else R.string.update_product)
        .setView(binding.root)
        .setPositiveButton(if (product == null) R.string.add else R.string.udpate) { _, _ ->
            Product(
                sku = binding.edtSku.text.toString(),
                productName = binding.edtName.text.toString(),
                qty = binding.edtQuantity.text.toString().safeInt(),
                price = binding.edtPrice.text.toString().safeInt(),
                unit = binding.edtUnit.text.toString(),
                status = binding.edtStatus.text.toString().safeInt(),
            ).also {
                callback(it)
            }
            (context as Activity).currentFocus?.let {
                hideSoftKeyboard(it)
            }
        }
        .setNegativeButton("Cancel") { _, _ ->
        }
        .create()
        .show()
}