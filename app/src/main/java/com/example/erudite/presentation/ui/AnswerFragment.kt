package com.example.erudite.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.example.erudite.R
import com.example.erudite.data.QuestionTimer
import com.example.erudite.databinding.FragmentAnswerBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class AnswerFragment : Fragment() {

    var _binding: FragmentAnswerBinding? = null
    val binding get() = _binding!!

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

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val currentScore = sharedPref?.getInt(ChangeUserFragment.CURRENT_SCORE, 0)
        var additionTime = sharedPref?.getLong(ChangeUserFragment.CURRENT_ADDION_TIME, 0L)
        tvCurrentScore.text = "Score: $currentScore"
        tvAddionTime.text = "Your addition time: $additionTime"

        val answer = etAnswer.text.toString()
        val rightAnswer = arguments?.getString("rightAnswer")
        val earlyAnswer = arguments?.getBoolean("earlyAnswer")
        bundle.putString("rightAnswer", rightAnswer)
        bundle.putString("userAnswer", answer)
        if (earlyAnswer == false) {
            bundle.putBoolean("earlyAnswer", earlyAnswer)
        }

        btEnterAnswer.setOnClickListener {
            navController.navigate(R.id.action_answerFragment_to_rightAnswerFragment, bundle)
        }

        if (additionTime == 0L && earlyAnswer == true) {btAddTime.isEnabled = false}
        //if (additionTime > 0) {

     //   }


        btAddTime.setOnClickListener {

            if (additionTime!! > 0L) {
                additionTime = additionTime!! - 60000
            }
            sharedPref?.edit()?.putLong(ChangeUserFragment.CURRENT_ADDION_TIME, additionTime!!)?.apply()
            // ***** Add correction value Addition Time *****************

            navController.navigate(R.id.action_answerFragment_to_questionFragment)
        }

        val questionTimer = QuestionTimer(20, 0)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                questionTimer.timerValue.collect {
                    tvTimer.text = it.toString()
                    if (it == 0) {
                        navController.navigate(R.id.rightAnswerFragment, bundle)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}