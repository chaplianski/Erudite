package com.example.erudite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.erudite.R
import com.example.erudite.UserRVAdapter


class ChangeUserFragment : Fragment() {

    val userViewModel: ChangeUserFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val tvNickname = view.findViewById<EditText>(R.id.etTextPersonName).text.toString()
        val okButton = view.findViewById<Button>(R.id.bt_back_change_user_fragment)
        val navController = Navigation.findNavController(view)


        //  userList = fetchUsers()

        userViewModel.userLiveData.observe(this.viewLifecycleOwner, {
            val userAdapter = UserRVAdapter(it)
            view.findViewById<RecyclerView>(R.id.rv_user_list).apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = userAdapter
            }
        })







        okButton.setOnClickListener {
            /*    CoroutineScope(Dispatchers.IO).launch {
                if (userRepository != null) {

                    if (userRepository.checkUser(tvNickname)){
                        val user = User( nickname = tvNickname)
                        userRepository?.insertUser(user)
                        Log.d("MyLog", "User List = $userList")
                    }
                }
            }*/
            navController.navigate(R.id.action_changeUserFragment_to_rulesFragment)
        }
    }
}