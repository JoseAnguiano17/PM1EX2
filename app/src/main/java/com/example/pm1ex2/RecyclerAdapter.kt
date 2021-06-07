package com.example.pm1ex2

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pm1ex2.R.layout
import com.example.pm1ex2.R.id
import com.example.pm1ex2.ui.home.HomeFragment


class RecyclerAdapter(values: ArrayList<ItemList>) : RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {
    private var items = values;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(layout.res_layout_item, parent, false)
        return RecyclerHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        val item = items[position]
        holder.imgItem.setImageResource(item.imgRes)
        holder.tvTitulo.text = item.titulo
        holder.tvDescripcion.text = item.descripcion
    }

    public fun AddData(t:String,d:String,img:Int){
        items.add(ItemList(t, d, img));
        this.notifyDataSetChanged()
        Log.i("INFO","AGREGADO "+itemCount.toString())
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    public fun getItems(): ArrayList<ItemList> {
        return items;
    }

    class RecyclerHolder(itemView_1: View) : RecyclerView.ViewHolder(itemView_1) {
        val imgItem: ImageView
        val tvTitulo: TextView
        val tvDescripcion: TextView

        init {
            imgItem = itemView.findViewById(id.imgView)
            tvTitulo = itemView.findViewById(id.txtTitulo)
            tvDescripcion = itemView.findViewById(id.txtDesc)
        }
    }
}
