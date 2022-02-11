package com.example.erudite.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.erudite.Models.Questions
import com.example.erudite.R
import com.example.erudite.ViewModels.QuestionQuestionFragmentViewModel
import com.example.erudite.ViewModels.TimerQuestionFragmentViewModel
import com.example.erudite.databinding.FragmentQuestionBinding
import java.util.*
import kotlin.concurrent.schedule


class QuestionFragment : Fragment() {

    lateinit var binding: FragmentQuestionBinding
    private val timerQuestionViewModel: TimerQuestionFragmentViewModel by viewModels()
    private val questionQuestionViewModel: QuestionQuestionFragmentViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btEarlyAnswer = binding.btEarlyAnswer
        val tvTextQuestion = binding.tvTextQuestion
        val tvNumberQuestion = binding.tvQuestion
        val tvTimer = binding.tvTimer
        val tvScore = binding.tvScore
        val navController = Navigation.findNavController(view)
        val bundle = Bundle()




        questionQuestionViewModel.questions.observe(this.viewLifecycleOwner, {
            val (idQuestion, question, answer) = it
            tvNumberQuestion.text = "Question $idQuestion"
            tvTextQuestion.text = question
            bundle.putString("rightAnswer", answer)
        })

        timerQuestionViewModel.timerLiveData.observe(this.viewLifecycleOwner, {
                tvTimer.text = it
                if (it.toInt() == 0){
                    navController.navigate(R.id.action_questionFragment_to_answerFragment, bundle)
                }


        })

        btEarlyAnswer.setOnClickListener {
            navController.navigate(R.id.answerFragment, bundle)
        }

    }
}