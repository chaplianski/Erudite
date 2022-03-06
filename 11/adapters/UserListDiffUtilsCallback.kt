package com.example.erudite.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.erudite.model.User

class UserListDiffUtilsCallback: DiffUtil.ItemCallback<User> (){
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}