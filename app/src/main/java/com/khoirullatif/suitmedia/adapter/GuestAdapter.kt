package com.khoirullatif.suitmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khoirullatif.suitmedia.Guest
import com.khoirullatif.suitmedia.R

class GuestAdapter(val listGuest: ArrayList<Guest>) : RecyclerView.Adapter<GuestAdapter.GridViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestAdapter.GridViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_guest, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GuestAdapter.GridViewHolder, position: Int) {
        val guest = listGuest[position]

        Glide.with(holder.itemView.context)
            .load(guest.photoGuest)
            .into(holder.ivPhotoGuest)
        holder.tvNameGuest.text = guest.name
        holder.tvBirtdateGuest.text = guest.birthdate

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listGuest[holder.adapterPosition]) }

    }

    override fun getItemCount(): Int = listGuest.size

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView){
        var ivPhotoGuest: ImageView = itemView.findViewById(R.id.iv_photo_guest)
        var tvNameGuest: TextView = itemView.findViewById(R.id.tv_name_guest)
        var tvBirtdateGuest: TextView = itemView.findViewById(R.id.tv_birthdate_guest)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Guest)
    }
}