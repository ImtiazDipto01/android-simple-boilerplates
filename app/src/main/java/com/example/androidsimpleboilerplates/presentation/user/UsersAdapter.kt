package com.example.androidsimpleboilerplates.presentation.user

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidsimpleboilerplates.databinding.ItemUsersBinding

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class MyViewHolder(binding: ItemUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        private val _binding: ItemUsersBinding = binding
    }
}
