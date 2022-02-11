package com.example.erudite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.erudite.Models.User

class UserRVAdapter(usera: List<User>): RecyclerView.Adapter<UserRVHolder>() {

    private var user: MutableList<User> = usera as MutableList<User>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRVHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.user_item_list,parent,false)
        return UserRVHolder(v)
    }

    override fun onBindViewHolder(holder: UserRVHolder, position: Int) {
        holder.onBind(user[position])
    }

    override fun getItemCount(): Int {
        return user.size
    }
}

class UserRVHolder(itemView: View) : RecyclerView.ViewHolder (itemView){

    val userItem: TextView = itemView.findViewById(R.id.tv_user_rv_item)
    fun onBind(user: User) {
        userItem.text = user.nickname
    }

}
