package com.usyssoft.retrofit2_rxjava.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.usyssoft.retrofit2_rxjava.R
import com.usyssoft.retrofit2_rxjava.model.CarList
import com.usyssoft.retrofit2_rxjava.view.util.placeHolder
import com.usyssoft.retrofit2_rxjava.view.util.setImageGlide

class CarAdapter(val list: ArrayList<CarList>) : RecyclerView.Adapter<CarAdapter.d>() {
    inner class d(v: View) : RecyclerView.ViewHolder(v) {
        val image = v.findViewById<ImageView>(R.id.imageView)
        val carbrand = v.findViewById<TextView>(R.id.textView3)
        val carmodel = v.findViewById<TextView>(R.id.textView4)
        val carcolor = v.findViewById<TextView>(R.id.textView5)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): d {
        val l = LayoutInflater.from(parent.context).inflate(R.layout.design_carlist, parent, false)
        return d(l)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: d, position: Int) {
        val item = list[position]
        holder.apply {
            carbrand.text = "Brand: " + item.carBrand
            carmodel.text = "Model: " + item.carModel
            carcolor.text = "Color: " + item.carColor
            image.setImageGlide(itemView.context,item.carImage, placeHolder(itemView.context))
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(getlist:List<CarList>){
        list.clear()
        list.addAll(getlist)
        notifyDataSetChanged()
    }
}