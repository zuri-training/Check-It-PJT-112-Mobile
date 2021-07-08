package com.frank.checkit.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frank.checkit.R
import com.frank.checkit.appendDollar
import com.frank.checkit.model.FakersData
import com.squareup.picasso.Picasso


class ProductAdapater(private val data:List<FakersData>, val clickListener:ItemClickListener):
        RecyclerView.Adapter<ProductAdapater.ViewHolder>(),Filterable{
    var filteredProductList :List<FakersData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count :${data.size} ")
        holder.bindData(data[position],clickListener)

    }

    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {

        private var title: TextView = itemView.findViewById(R.id.item_title)
            private var price: TextView = itemView.findViewById(R.id.item_price)
            var imageView: ImageView = itemView.findViewById(R.id.item_image)

            fun bindData(data: FakersData, clickListener: ItemClickListener){

                title.text = data.title
                price.text = data.price.toString().appendDollar()
                Picasso.get().load(data.image).into(imageView)

                itemView.setOnClickListener {
                    clickListener.onItemClicked(data)
                }


            }





    }

    override fun getFilter(): Filter {
        return object :Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString=constraint?.toString()?:""
                if (charString.isEmpty()) filteredProductList = data else{
                    val filteredList = ArrayList<FakersData>()
                    data.filter {
                        (it.title.contains(constraint!!))
                    }.forEach { filteredList.add(it) }
                    filteredProductList = filteredList
                }
                return FilterResults().apply { values = filteredProductList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
             filteredProductList = if (results?.values == null) ArrayList() else results.values as ArrayList<FakersData>
            }

        }
    }

}
interface ItemClickListener {
    fun onItemClicked(data: FakersData)
}


