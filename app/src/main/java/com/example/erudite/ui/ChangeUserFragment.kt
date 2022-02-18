package com.example.erudite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import com.example.erudite.R
import com.example.erudite.repository.*


class ChangeUserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userRepository = context?.let { UserRepository(it) }

        val tvNickname = view.findViewById<EditText>(R.id.etTextPersonName).text.toString()
        val  okButton = view.findViewById<Button>(R.id.bt_back_change_user_fragment)
        val  navController = Navigation.findNavController(view)
    //    val userAdapter = UserRVAdapter()




        okButton.setOnClickListener {
            if (userRepository != null) {
        //        if (userRepository.checkUser(tvNickname)){
        //            val user = User(1, tvNickname)
        //            userRepository?.insertUser(user)
        //        }
            }



            navController.navigate(R.id.action_changeUserFragment_to_rulesFragment)
        }
    }
}