package com.example.erudite.presentation.ui

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
import com.example.erudite.presentation.viewmodelfactory.RightAnswerFragmentVMFactory
import com.example.erudite.databinding.FragmentRightAnswerBinding
import com.example.erudite.di.components.DaggerAppComponent
import com.example.erudite.presentation.viewmodel.RightanswerFragmentViewModel
import javax.inject.Inject


class RightAnswerFragment : Fragment() {

    @Inject
    lateinit var rightAnswerFragmentVMFactory: RightAnswerFragmentVMFactory

    val userViewModel: RightanswerFragmentViewModel by viewModels {rightAnswerFragmentVMFactory}

    var _binding: FragmentRightAnswerBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onAttach(context: Context) {
        DaggerAppComponent
            .builder()
            .context(context)
            .build()
            .rightAnswerFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRightAnswerBinding.inflate(inflater, container, false)
            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btNextQuestion = binding.btContinueQuizRightFragment
        val btPauseQuiz = binding.btMakePause
        val tvUserAnswer = binding.tvUserAnswer
        val tvRightAnswer = binding.tvRightAnswer
        val tvComment = binding.tvComment
        val tvCurrentScore = binding.tvCurrentScoreRightFragment
        val navController = Navigation.findNavController(view)
        val userSharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val currentUser = userSharedPref?.getString(ChangeUserFragment.CURRENT_USER, "User")
        Log.d("MyLog", "RA user: $currentUser")
        if (currentUser != null) {
            userViewModel.getUser(currentUser)
        }

        val userAnswer = arguments?.getString("userAnswer")
        tvUserAnswer.text = userAnswer
        val rightAnswer = arguments?.getString("rightAnswer")
        tvRightAnswer.text = rightAnswer


        userViewModel.currentUser.observe(this, {
            if (userAnswer.equals(rightAnswer)) {
                tvComment.text = "Cool!!!"
                it.score = it.score + 100
                it.addionTime = it.addionTime + 60000
                userSharedPref?.edit()?.putInt(ChangeUserFragment.CURRENT_SCORE, it.score)?.apply()
                userViewModel.updateUser(it)
            } else{
                tvComment.text = "Your answer is not right"
            }
            Log.d("MyLog", "RA user: $it")
            tvCurrentScore.text = "Current score:  ${it.score.toString()}"
        })


        btNextQuestion.setOnClickListener {
            navController.navigate(R.id.action_rightAnswerFragment_to_questionFragment)
        }

        btPauseQuiz.setOnClickListener {
            navController.navigate(R.id.action_rightAnswerFragment_to_rulesFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}