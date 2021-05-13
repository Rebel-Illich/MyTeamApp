package com.hunter.myteams.fragmentAllTeams.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hunter.myteams.R
import com.hunter.myteams.fragmentAllTeams.Result


class AllTeamsListAdapter(private val allTeams: List<Result>) :

    RecyclerView.Adapter<AllTeamsListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllTeamsListAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_all_teams, parent, false)
        return MyViewHolder(inflater)     }

    override fun getItemCount(): Int {
        return allTeams.size
    }

    override fun onBindViewHolder(holder: AllTeamsListAdapter.MyViewHolder, position: Int) {
        holder.numberAllTeam?.text = allTeams[position].rank.toString()
        holder.cityAllTeam?.text = allTeams[position].displayName

        if (allTeams[position].average != 0) {
            holder.activityAllTeam?.text =
                allTeams[position].average.toString()
        } else {
            holder.activityAllTeam?.text = "no activity"

        }
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var numberAllTeam: TextView? = null
        var cityAllTeam: TextView? = null
        var activityAllTeam: TextView? = null


        init {
            numberAllTeam = itemView.findViewById(R.id.all_teams_number)
            cityAllTeam = itemView.findViewById(R.id.t_display_name)
            activityAllTeam = itemView.findViewById(R.id.t_activity)
        }

    }
}