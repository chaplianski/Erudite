package com.example.erudite.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.erudite.DI.DaggerApp
import com.example.erudite.DI.components.AppComponent
import com.example.erudite.DI.components.DaggerAppComponent
import com.example.erudite.R
import com.example.erudite.ViewModels.QuestionFragmentVMFactory
import com.example.erudite.ViewModels.TimerViewModel
import com.example.erudite.ViewModels.TimerViewModelFactory
import com.example.erudite.databinding.FragmentQuestionBinding
import com.example.erudite.repository.QuestionRepository
import dagger.internal.InstanceFactory.create
import javax.inject.Inject


class QuestionFragment : Fragment() {

 //   lateinit var binding: FragmentQuestionBinding
    @Inject
    lateinit var questionFragmentVMFactory: QuestionFragmentVMFactory

    val questionViewModel: QuestionFragmentViewModel by viewModels{questionFragmentVMFactory}


    var _binding: FragmentQuestionBinding? = null
    val  binding get() = _binding!!

    var delay = 1000L
//    private val questionViewModel: QuestionFragmentViewModel by viewModels()



    override fun onAttach(context: Context) {
        DaggerAppComponent
            .builder()
            .context(context)
            .build()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
     //   val view = binding.root
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

        questionViewModel.questions.observe(this.viewLifecycleOwner, {
            val (idQuestion, question, answer) = it
            tvNumberQuestion.text = "Question $idQuestion"
            tvTextQuestion.text = question
            val questionSize = question.length

            bundle.putString("rightAnswer", answer)
        })

        val timerCount = 60000L
        delay = (100*80).toLong()

        Log.d("MyLog", "delay2 in fragment = $delay")

        val timerViewModel: TimerViewModel by viewModels {
            TimerViewModelFactory(
                requireActivity().application,
                timerCount,
                delay
            )
        }

        timerViewModel.timerLiveData.observe(this.viewLifecycleOwner, {
            tvTimer.text = it
            if (it.toInt() == 0) {
                navController.navigate(R.id.action_questionFragment_to_answerFragment, bundle)
            }
        })

        btEarlyAnswer.setOnClickListener {
            navController.navigate(R.id.answerFragment, bundle)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }







}