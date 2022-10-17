package com.example.androidsimpleboilerplates.presentation.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions
import com.example.androidsimpleboilerplates.R
import com.example.androidsimpleboilerplates.databinding.ItemUsersBinding
import com.example.androidsimpleboilerplates.data.local.db.entity.GithubUser

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GithubUser>() {
        override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
            return oldItem.login == newItem.login
        }

        override fun areContentsTheSame(
            oldItem: GithubUser,
            newItem: GithubUser
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            ItemUsersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<GithubUser>) = differ.submitList(list)

    inner class MyViewHolder(binding: ItemUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        private val _binding: ItemUsersBinding = binding

        fun bind(user: GithubUser) {
            _binding.apply {
                textUserName.text = user.login ?: ""

                val options: RequestOptions = RequestOptions()
                    .transform(FitCenter())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .priority(Priority.HIGH)

                Glide.with(imgUser)
                    .load(user.avatarUrl)
                    .apply(options)
                    .into(imgUser)
            }
        }
    }
}
