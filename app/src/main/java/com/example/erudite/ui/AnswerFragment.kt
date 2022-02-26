package com.example.erudite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.erudite.R
import com.example.erudite.ViewModels.TimerViewModel
import com.example.erudite.ViewModels.TimerViewModelFactory
import com.example.erudite.databinding.FragmentAnswerBinding


class AnswerFragment : Fragment() {

    var _binding: FragmentAnswerBinding? = null
    val binding get() = _binding!!

    private val timerViewModel : TimerViewModel by viewModels{TimerViewModelFactory(requireActivity()
        .application, timerCount = 20000L, delay = 0L)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnswerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btEnterAnswer = binding.btEnterAnswer
        val btAddTime = binding.btUseAdditionTime
        val tvTimer = binding.tvAnswerTimer
        val etAnswer = binding.etYourAnswer
        val tvCurrentScore = binding.tvScoreAnswerFragment
        val tvAddionTime = binding.tvAddionTime
        val navController = Navigation.findNavController(view)
        val bundle = Bundle()

        btEnterAnswer.setOnClickListener {
            val answer = etAnswer.text.toString()
            val rightAnswer = arguments?.getString("rightAnswer")
            bundle.putString("rightAnswer", rightAnswer)
            bundle.putString("userAnswer", answer)

            navController.navigate(R.id.action_answerFragment_to_rightAnswerFragment, bundle)
        }

        btAddTime.setOnClickListener {
            navController.navigate(R.id.action_answerFragment_to_questionFragment)
        }

        timerViewModel.timerLiveData.observe(this.viewLifecycleOwner, {
            tvTimer.text = it
            if (it.toInt() == 0) {
                navController.navigate(R.id.action_answerFragment_to_rightAnswerFragment, bundle)
            }
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}