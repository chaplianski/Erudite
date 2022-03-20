package com.example.erudite.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.erudite.R
import com.example.erudite.data.model.User

class UserRVAdapter :
    androidx.recyclerview.widget.ListAdapter<User, UserRVHolder>(UserListDiffUtilsCallback()) {
    //RecyclerView.Adapter<UserRVHolder>() {

    // var user: List<User> = users

    var userLongOnClickListener: UserLongOnClickListener? = null
    var userShortOnClickListener: UserShortOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRVHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_item_list, parent, false)
        return UserRVHolder(v)
    }

    override fun onBindViewHolder(holder: UserRVHolder, position: Int) {
        val userItem = getItem(position)
        holder.onBind(userItem)
        holder.itemView.setOnLongClickListener {
            userLongOnClickListener?.userLongClick(userItem)
            deleteUser(position)
            true
        }
        holder.itemView.setOnClickListener {
            userShortOnClickListener?.userShortClick(userItem)
            val navController = Navigation.findNavController(holder.itemView)
            navController.navigate(R.id.action_changeUserFragment_to_rulesFragment)
        }
    }

    interface UserLongOnClickListener {
        fun userLongClick(userItem: User)
    }

    interface UserShortOnClickListener{
        fun userShortClick (userItem: User)
    }

    fun deleteUser(position: Int) {

        val userList = currentList.toMutableList()
        userList.removeAt(position)
        submitList(userList)

    }
}

    class UserRVHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val userItem: TextView = itemView.findViewById(R.id.tv_user_rv_item)
    val userScoreItem: TextView = itemView.findViewById(R.id.tv_score_rv_item)

    fun onBind(user: User) {
        userItem.text = user.nickname
        userScoreItem.text = user.score.toString()

    }


}