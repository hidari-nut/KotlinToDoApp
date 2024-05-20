package com.viswa.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.viswa.todoapp.R
import com.viswa.todoapp.databinding.FragmentCreateTodoBinding
import com.viswa.todoapp.databinding.FragmentTodoListBinding
import com.viswa.todoapp.model.Todo
import com.viswa.todoapp.viewmodel.DetailTodoViewModel


class CreateTodoFragment : Fragment() {
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var binding: FragmentCreateTodoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateTodoBinding.inflate(inflater, container,false)
        return binding.root   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        binding.btnAdd.setOnClickListener{

            val radioID = binding.radioGroupPriority.checkedRadioButtonId
            val radio = view.findViewById<RadioButton>(radioID)
            val priority = radio.tag.toString().toInt()


            var todo = Todo(binding.txtTitle.text.toString(), binding.txtNotes.text.toString(), priority)
            //viewModel.addTodo(todo)
            val list = listOf(todo)
            viewModel.addTodo(list)
            Toast.makeText(it.context, "Todo Added", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}