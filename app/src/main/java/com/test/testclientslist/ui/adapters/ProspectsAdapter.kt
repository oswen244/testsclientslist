package com.test.testclientslist.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.testclientslist.data.models.Client
import com.test.testclientslist.databinding.RvItemProspectBinding

class ProspectsAdapter(private var item: ArrayList<Client>, private val listener: OnItemClickListener) :

    RecyclerView.Adapter<ProspectsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val binding = RvItemProspectBinding.inflate(layoutInflater, p0, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        return p0.bind(item[p1], listener)
    }

    fun replaceData(items: ArrayList<Client>){
        this.item.clear()
        this.item.addAll(items)
        notifyDataSetChanged()
    }


    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    class ViewHolder(private var binding: RvItemProspectBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(props: Client, listener: OnItemClickListener){
            binding.model = props
            binding.root.setOnClickListener { _-> listener.onItemClick(layoutPosition) }
            binding.executePendingBindings()
        }
    }
}