package com.example.erudite.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.erudite.R
import com.example.erudite.databinding.FragmentRulesBinding


class RulesFragment : Fragment() {


    var _binding: FragmentRulesBinding? = null
    val binding get() = _binding!!





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRulesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = view?.let { Navigation.findNavController(it) }

        val tvRules = binding.tvRules
        val btnContinue = binding.btContiueQuiz
        val btnNewUserRegistration = binding.btBeginNewQuiz
        val tvNickName = binding.tvNickname
        val userSharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val currentUser = userSharedPref?.getString(ChangeUserFragment.CURRENT_USER, "Please, registrate new user")
        tvNickName.text = "Your current nickname: $currentUser"
        tvRules.text = "Правила викторины: ..."

        Log.d("MyLog", currentUser.toString())




        btnContinue?.setOnClickListener{
            navController?.navigate(R.id.action_rulesFragment_to_questionFragment)

        }
        btnNewUserRegistration?.setOnClickListener {
            navController?.navigate(R.id.changeUserFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }




}