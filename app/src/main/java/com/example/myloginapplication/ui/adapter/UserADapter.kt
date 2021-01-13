package com.example.myloginapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myloginapplication.BR
import com.example.myloginapplication.R
import com.example.myloginapplication.data.model.User
import com.example.myloginapplication.databinding.UserRowBinding

class UserADapter(val update:User.()->Unit,val clicks :(User)->Unit):RecyclerView.Adapter<UserADapter.UserHolder>() {

    lateinit var binding:UserRowBinding
    var users:List<User> = arrayListOf()

    inner class UserHolder(binding: UserRowBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(user: User) {
            binding.setVariable(BR.user,user)
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.user_row,parent,false)
   return UserHolder(binding) }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(users[position])
        binding.ivDelete.setOnClickListener {
             clicks(users[position])

        }
        binding.ivEdit.setOnClickListener {
            update(users[position])
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}