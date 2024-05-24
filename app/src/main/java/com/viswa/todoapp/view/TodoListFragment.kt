package com.viswa.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.viswa.todoapp.R
import com.viswa.todoapp.databinding.FragmentTodoListBinding
import com.viswa.todoapp.viewmodel.ListTodoViewModel


class TodoListFragment : Fragment() {
    private lateinit var binding:FragmentTodoListBinding
    private lateinit var viewModel:ListTodoViewModel
    private val adapter = TodoListAdapter(arrayListOf(), {item->viewModel.doneTask(item)})
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTodoListBinding.inflate(inflater, container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(
            ListTodoViewModel::class.java
        )
        viewModel.refresh()
        binding.recViewTodo.layoutManager=LinearLayoutManager(context)
        binding.recViewTodo.adapter=adapter

        binding.btnFab.setOnClickListener{
            val action = TodoListFragmentDirections.actionCreateTodo()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner,
            Observer {
            adapter.updateTodoList(it)
                if(it.isEmpty()){
                    binding.recViewTodo.visibility=View.GONE
                }else{
                    binding.recViewTodo.visibility=View.VISIBLE
                }
        })
//        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
//            if(it==false){
//                binding.progressLoad.visibility = View.GONE
//            }
//        })

//        viewModel.todoLoadErrorLD.observe(viewLifecycleOwner, Observer {
//            if(it==false){
//                binding.txtError.visibility = View.GONE
//            }
//        })
    }
}