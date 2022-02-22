package com.example.erudite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.erudite.model.User

class UserRVAdapter(users: List<User>): RecyclerView.Adapter<UserRVHolder>() {

     var user: List<User> = users

    var userLongOnClickListener: UserLongOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRVHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.user_item_list,parent,false)
        return UserRVHolder(v)
    }

    override fun onBindViewHolder(holder: UserRVHolder, position: Int) {
        val userItem = user[position]
        holder.onBind(userItem)
        holder.itemView.setOnLongClickListener {
            userLongOnClickListener?.userLongClick(userItem)
            notifyDataSetChanged()
            true
        }
    }

    override fun getItemCount(): Int {
        return user.size
    }

    interface UserLongOnClickListener{
        fun userLongClick(userItem: User )
    }
}

class UserRVHolder(itemView: View) : RecyclerView.ViewHolder (itemView){

    val userItem: TextView = itemView.findViewById(R.id.tv_user_rv_item)
    fun onBind(user: User) {
        userItem.text = user.nickname

    }



}
