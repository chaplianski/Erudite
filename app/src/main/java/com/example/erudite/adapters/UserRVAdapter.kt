package com.example.erudite

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.erudite.adapters.UserListDiffUtilsCallback
import com.example.erudite.model.User
import com.example.erudite.ui.ChangeUserFragment

class UserRVAdapter: androidx.recyclerview.widget.ListAdapter<User, UserRVHolder>(UserListDiffUtilsCallback()){
    //RecyclerView.Adapter<UserRVHolder>() {

    // var user: List<User> = users

    var userLongOnClickListener: UserLongOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRVHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.user_item_list,parent,false)
        return UserRVHolder(v)
    }

    override fun onBindViewHolder(holder: UserRVHolder, position: Int) {
        val userItem = getItem(position)
        holder.onBind(userItem)
        holder.itemView.setOnLongClickListener {
            userLongOnClickListener?.userLongClick(userItem)

            true
        }
    }
/*
    override fun getItemCount(): Int {
        return user.size
    }*/

    interface UserLongOnClickListener{
        fun userLongClick(userItem: User )
    }
}

class UserRVHolder(itemView: View) : RecyclerView.ViewHolder (itemView){

    val userItem: TextView = itemView.findViewById(R.id.tv_user_rv_item)
    fun onBind(user: User) {
        userItem.text = user.nickname

        // ***** требуется контекст на итем вью шаред не работает.
        val changeUserFragment = ChangeUserFragment()
        changeUserFragment.addUserSharedPref(user.nickname)


        userItem.setOnClickListener{
            val navController = Navigation.findNavController(itemView)
            navController.navigate(R.id.action_changeUserFragment_to_rulesFragment)
        }
    }



}
