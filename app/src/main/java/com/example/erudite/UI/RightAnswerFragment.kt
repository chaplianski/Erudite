package com.example.erudite.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.erudite.R
import com.example.erudite.databinding.FragmentRightAnswerBinding


class RightAnswerFragment : Fragment() {


    var _binding: FragmentRightAnswerBinding? = null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        val userAnswer = arguments?.getString("userAnswer")
        tvUserAnswer.text = userAnswer
        val rightAnswer = arguments?.getString("rightAnswer")
        tvRightAnswer.text = rightAnswer

        if (userAnswer.equals(rightAnswer)) tvComment.text = "Молодец"

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