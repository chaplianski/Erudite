package com.example.erudite.presentation.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.erudite.R
import com.example.erudite.presentation.adapter.UserRVAdapter
import com.example.erudite.databinding.FragmentChangeUserBinding
import com.example.erudite.data.model.User
import com.example.erudite.presentation.viewmodel.ChangeUserFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ChangeUserFragment : Fragment() {

    var _binding: FragmentChangeUserBinding?  = null
    val binding get() = _binding!!

    private val userViewModel: ChangeUserFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChangeUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val okButton = binding.btBackChangeUserFragment
        val navController = Navigation.findNavController(view)

        userViewModel.userLiveData.observe(this.viewLifecycleOwner, {
            val userAdapter = UserRVAdapter()
            userAdapter.submitList(it)
            view.findViewById<RecyclerView>(R.id.rv_user_list).apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = userAdapter

                userAdapter.userLongOnClickListener = object : UserRVAdapter.UserLongOnClickListener{
                    override fun userLongClick(userItem: User) {

                        val builder = AlertDialog.Builder(context)
                        with(builder){
                            setTitle("Delete user")
                            setCancelable(true)
                            setMessage("Do your sure?")
                            setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                                userViewModel.deleteUser(userItem.userId)
                          //      userAdapter.submitList(it)
                            })
                            setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, i ->
                                dialog.cancel()
                            })

                        }
                        val alertDialog = builder.create()
                        alertDialog.show()
                    }
                }

                userAdapter.userShortOnClickListener = object : UserRVAdapter.UserShortOnClickListener{
                    override fun userShortClick(userItem: User) {
                        addUserSharedPref(userItem.nickname, userItem.score, userItem.addionTime)
                    }

                }
            }
        })


        okButton.isEnabled = false
        okButton.setOnClickListener {
               CoroutineScope(Dispatchers.IO).launch {
//
                   val tvNickname = binding.etTextPersonName.text.toString()
                   val user = User( nickname = tvNickname)
                   if (!tvNickname.isBlank()){
                       okButton.isEnabled = true
                       userViewModel.addUser(user)
                   }else{

                   }

                   addUserSharedPref(tvNickname, 0, 0L)
            }
            navController.navigate(R.id.action_changeUserFragment_to_rulesFragment)
        }
    }

    fun addUserSharedPref (nickname: String, score: Int, additionTime: Long){
        val userSharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        with(userSharedPref?.edit()){
            this?.putString(CURRENT_USER, nickname)
            this?.putInt(CURRENT_SCORE, score)
            this?.putLong(CURRENT_ADDION_TIME, additionTime)
            this?.apply()
            Log.d("MyLog", "current user: $nickname")
        }
    }


    companion object {

        const val CURRENT_USER = "Current user"
        const val CURRENT_SCORE = "Current score"
        const val CURRENT_ADDION_TIME = "Addition time"
    }
}