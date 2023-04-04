package com.example.happyplaces.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.happyplaces.R
import com.example.happyplaces.activity.HappyPlaceDetailsActivity
import com.example.happyplaces.models.HappyPlaceModel
import de.hdodenhof.circleimageview.CircleImageView

open class HappyPlaceAdapter(
    private val context: Context,
    private var list: ArrayList<HappyPlaceModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private  var onClickListener:OnClickListener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_happy_place, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]
        if (holder is MyViewHolder) {
            holder.iv_place_image.setImageURI(Uri.parse(model.image))
            holder.tvTitle.text = model.title
            holder.tvDescription.text = model.description
            holder.itemView.setOnClickListener(){
                if(onClickListener!=null){
                    onClickListener!!.onClick(position,model)
                }
            }
        }
    }

    interface OnClickListener {
        fun onClick(position: Int, model: HappyPlaceModel)
    }
    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener=onClickListener
    }

    private class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv_place_image: CircleImageView = view.findViewById(R.id.iv_place_image)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
    }

}