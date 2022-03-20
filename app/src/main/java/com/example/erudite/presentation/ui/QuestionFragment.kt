package com.example.erudite.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.erudite.R
import com.example.erudite.data.QuestionTimer
import com.example.erudite.databinding.FragmentQuestionBinding
import com.example.erudite.di.components.DaggerAppComponent
import com.example.erudite.presentation.viewmodel.QuestionFragmentViewModel
import com.example.erudite.presentation.viewmodelfactory.QuestionFragmentVMFactory
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


class QuestionFragment : Fragment() {

    @Inject
    lateinit var questionFragmentVMFactory: QuestionFragmentVMFactory

    val questionViewModel: QuestionFragmentViewModel by viewModels{questionFragmentVMFactory}


    var _binding: FragmentQuestionBinding? = null
    val  binding get() = _binding!!

    override fun onAttach(context: Context) {
        DaggerAppComponent
            .builder()
            .context(context)
            .build()
            .questionFragmentInject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
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

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val currentScore = sharedPref?.getInt(ChangeUserFragment.CURRENT_SCORE, 125)

        tvScore.text = "Score: $currentScore"

        questionViewModel.questions.observe(this.viewLifecycleOwner, {
            val (idQuestion, question, answer) = it
            tvNumberQuestion.text = "Question $idQuestion"
            tvTextQuestion.text = question
            val questionSize = question.length
            Log.d("MyLog", "questionLength = $questionSize")
            val questionTimer = QuestionTimer (60, questionSize)

            lifecycleScope.launchWhenCreated {
                questionTimer.timerValue.collect {
                    tvTimer.text = it.toString()
                    if (it == 0) navController.navigate(R.id.action_questionFragment_to_answerFragment, bundle)
                }
            }

            bundle.putString("rightAnswer", answer)
        })


        btEarlyAnswer.setOnClickListener {
            bundle.putBoolean("earlyAnswer", true)
            navController.navigate(R.id.answerFragment, bundle)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }







}