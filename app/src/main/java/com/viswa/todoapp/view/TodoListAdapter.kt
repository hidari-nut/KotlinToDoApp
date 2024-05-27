package com.viswa.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.viswa.todoapp.databinding.TodoItemLayoutBinding
import com.viswa.todoapp.model.Todo

class TodoListAdapter(val todoList:ArrayList<Todo>,
    val adapterOnClick:(Todo)
    ->
    Unit
    ):
    RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(), TodoCheckedChangeListener, TodoEditClickListener {
    class TodoViewHolder(var binding:TodoItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        var binding = TodoItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

//  DataBinding
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

//        holder.binding.checkTask.text=
//            todoList[position].title
        holder.binding.checkTask.isChecked = todoList[position].is_done == 1
        holder.binding.editlistener = this
        holder.binding.todo = todoList[position]

        if (todoList[position].is_done == 1) {
            holder.binding.checkTask.paintFlags = holder.binding.checkTask.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.binding.checkTask.paintFlags = holder.binding.checkTask.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

//        holder.binding.imgEdit.setOnClickListener{
//            val action = TodoListFragmentDirections.actionEditTodo(todoList[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }

//        holder.binding.checkTask.
//        setOnCheckedChangeListener { compoundButton,
//                                     b ->
//            if(compoundButton.isPressed){
//                todoList[position].is_done =
//                    if (b)
//                        1
//                    else
//                        0
//                adapterOnClick(todoList[position])
//            }
//        }

    }

//     ViewBinding
    //    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
    //        holder.binding.checkTask.text=
    //            todoList[position].title
    //        holder.binding.checkTask.isChecked = false
    //
    //        holder.binding.imgEdit.setOnClickListener{
    //            val action = TodoListFragmentDirections.actionEditTodo(todoList[position].uuid)
    //            Navigation.findNavController(it).navigate(action)
    //        }
    //        holder.binding.checkTask.
    //        setOnCheckedChangeListener { compoundButton,
    //                                     b ->
    //            if(compoundButton.isPressed){
    //                adapterOnClick(todoList[position])
    //            }
    //        }
    //    }

    fun updateTodoList(newtodolist:List<Todo>){
        todoList.clear()
        todoList.addAll(newtodolist)
        notifyDataSetChanged()
    }

    override fun onCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(cb.isPressed){
            adapterOnClick(obj)
         }
    }

    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionEditTodo(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}