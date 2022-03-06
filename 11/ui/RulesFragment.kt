package com.example.erudite.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.erudite.R
import com.example.erudite.ViewModels.RulesFragmentVMFactory
import com.example.erudite.databinding.FragmentRulesBinding
import com.example.erudite.di.components.DaggerAppComponent
import javax.inject.Inject


class RulesFragment : Fragment() {


    var _binding: FragmentRulesBinding? = null
    val binding get() = _binding!!

    @Inject
    lateinit var rulesFragmentVMFactory: RulesFragmentVMFactory

    val userFragmentViewModel: RulesFragmentViewModel by viewModels {rulesFragmentVMFactory}


    override fun onAttach(context: Context) {
            DaggerAppComponent
                .builder()
                .context(context)
                .build()
                .rulesFragmentInject(this)
            super.onAttach(context)
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
        val score = userSharedPref?.getInt(ChangeUserFragment.CURRENT_SCORE, 0)

        if (currentUser != null) {
            userFragmentViewModel.getUser(currentUser)
        }

        userFragmentViewModel.currentUser.observe(this, {

            tvNickName.text = "Your current nickname: ${it.nickname}, score: ${it.score}"

        })


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