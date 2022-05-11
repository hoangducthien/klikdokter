package com.example.klikdoctertest.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.product.Product
import com.example.klikdoctertest.R
import com.example.klikdoctertest.utils.showConfirmDialog
import com.example.klikdoctertest.utils.showProductUpdateDialog

class ProductListAdapter: RecyclerView.Adapter<ProductListViewHolder>() {

    private var products: ArrayList<Product> = ArrayList()

    var onEditCallback: ((product: Product) -> Unit)? = null
    var onDeleteCallback: ((product: Product) -> Unit)? = null

    fun updateProductList(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    fun removeProduct(product: Product) {
        var removeIndex = -1
        products.forEachIndexed { index, item ->
            if (product.sku == item.sku) {
                removeIndex = index
                return@forEachIndexed
            }
        }
        if (removeIndex > -1) {
            products.removeAt(removeIndex)
            notifyItemRemoved(removeIndex)
        }
    }

    fun updateProduct(product: Product) {
        var updatedIndex = -1
        products.forEachIndexed { index, item ->
            if (product.sku == item.sku) {
                updatedIndex = index
                item.update(product)
                return@forEachIndexed
            }
        }
        if (updatedIndex > -1) {
            notifyItemChanged(updatedIndex)
        } else {
            products.add(product)
            notifyItemInserted(products.size - 1)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.view_product_item, parent, false
        )).apply {
            delete.setOnClickListener {
                showConfirmDialog(it.context, it.context.getString(R.string.confirm_delte)) { confirmed ->
                    if (confirmed) {
                        onDeleteCallback?.invoke(itemView.tag as Product)
                    }
                }
            }
            edit.setOnClickListener {
                showProductUpdateDialog(it.context, itemView.tag as Product) { product ->
                    onEditCallback?.invoke(product)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        products[position].apply {
            holder.itemView.tag = this
            holder.sku.text = sku
            holder.name.text = productName
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }
}

class ProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val sku = itemView.findViewById<TextView>(R.id.tv_sku)
    val name = itemView.findViewById<TextView>(R.id.tv_product_name)
    val edit = itemView.findViewById<TextView>(R.id.product_edit)
    val delete = itemView.findViewById<TextView>(R.id.product_delete)
}