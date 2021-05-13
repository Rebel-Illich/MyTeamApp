package com.hunter.myteams.fragmentCompanies.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hunter.myteams.MyTeamApp
import com.hunter.myteams.R
import com.hunter.myteams.fragmentCompanies.Result

class MyCompanyRecyclerListAdapter(private val myCompaniesList: MutableList<Result>) :
    RecyclerView.Adapter<MyCompanyRecyclerListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_my_company, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.myCompaniesId?.text = myCompaniesList[position].rank.toString()
        holder.myCompanyName?.text = myCompaniesList[position].displayName
        holder.myCompanySteps?.text = myCompaniesList[position].totalDouble.toString()

    }

    override fun getItemCount(): Int {
        return myCompaniesList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var myCompaniesId: TextView? = null
        var myCompanyName: TextView? = null
        var myCompanySteps: TextView? = null


        init {
            myCompaniesId = itemView.findViewById(R.id.my_company_id)
            myCompanyName = itemView.findViewById(R.id.t_name_company)
            myCompanySteps = itemView.findViewById(R.id.t_total_step)

            Glide
                .with(MyTeamApp.instance)
                .load("https://static0.fitbit.com/images/profile/defaultProfile_150.png")
                .centerCrop()
                .into(itemView.findViewById(R.id.image_my_company))
        }
    }
}