package com.example.taskmodemoapp.ui.frag.post

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmodemoapp.R
import com.example.taskmodemoapp.databinding.CardPostsBinding
import com.example.taskmodemoapp.model.Post

class PostAdapter(val context: Context, val list: ArrayList<Post>, val navController: NavController) : RecyclerView.Adapter<PostAdapter.ViewHold>() {
    class ViewHold(val bind: CardPostsBinding) : RecyclerView.ViewHolder(bind.root) {
        fun bindInfo(post: Post, navController: NavController) {
            bind.txtInfo.text = post.title
            bind.txtInfo.setOnClickListener { navController.navigate(R.id.action_postFragment_to_commentFragment) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val binder : CardPostsBinding = CardPostsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHold(binder)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.bindInfo(list[position], navController)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}