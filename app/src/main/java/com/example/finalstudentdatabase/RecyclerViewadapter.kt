package com.example.finalstudentdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

class RecyclerViewAdapter(val listener: RowClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var item = ArrayList<user>()

    fun setListData(data: ArrayList<user>) {
        this.item = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.itemview, parent, false)
        return MyViewHolder(inflate,listener)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            listener.onItemClickListener(item[position])
        }
        holder.bind(item[position])
    }

    class MyViewHolder(view: View, val listener: RowClickListener) : RecyclerView.ViewHolder(view) {
        private val textViewid: TextView = view.findViewById(R.id.regid)
        private val textViewfname: TextView = view.findViewById(R.id.textFirst)
        private val textViewlname: TextView = view.findViewById(R.id.lname)
        private val textViewdob: TextView = view.findViewById(R.id.dob)
        private val textViewfathername: TextView = view.findViewById(R.id.fname)
        private val textViewcourse: TextView = view.findViewById(R.id.coursename)
        private val textViewcsdate: TextView = view.findViewById(R.id.csdate)
        private val textViewgender: TextView = view.findViewById(R.id.gender)
        private val textViewcontact: TextView = view.findViewById(R.id.Contact)
        private val deleteId: Button = view.findViewById(R.id.deleteUserId)


        fun bind(data: user) {
            textViewid.text = data.id.toString()
            textViewfname.text = data.firstName
            textViewlname.text = data.lastName
            textViewdob.text = data.dateOfBirth
            textViewfathername.text = data.fatherName
            textViewcourse.text = data.courseName
            textViewcsdate.text = data.courseStartDate
            textViewgender.text = data.gender
            textViewcontact.text = data.contact


            deleteId.setOnClickListener{
                listener.onDeleteUserClickListener(data)

            }
        }
    }
    interface RowClickListener{
        fun onDeleteUserClickListener(User:user)
        fun onItemClickListener(User:user)

    }
}
