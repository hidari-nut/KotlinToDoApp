package com.viswa.todoapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.viswa.todoapp.R
import com.viswa.todoapp.databinding.FragmentCreateTodoBinding
import com.viswa.todoapp.viewmodel.DetailTodoViewModel


class EditTodoFragment : Fragment() {
    private lateinit var binding:FragmentCreateTodoBinding
    private lateinit var viewModel:DetailTodoViewModel
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = FragmentCreateTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        binding.txtJudulTodo.text="Edit Todo"
        val uuid = EditTodoFragmentArgs.fromBundle(
            requireArguments()).uuid
        viewModel.fetch(uuid)

        binding.btnAdd.setOnClickListener{
            val radioID = binding.radioGroupPriority.checkedRadioButtonId
            val radio = view.findViewById<RadioButton>(radioID)
            val priority = radio.tag.toString().toInt()

            viewModel.update(binding.txtTitle.text.toString(), binding.txtNotes.text.toString(),
                priority, uuid)

            Toast.makeText(context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner,
            Observer {
            binding.txtTitle.setText(it.title)
            binding.txtNotes.setText(it.notes)

            when(it.priority){
                1-> binding.radioLow.isChecked = true
                2-> binding.radioMedium.isChecked = true
                else-> binding.radioHigh.isChecked = true
            }
        })
    }

}