package com.example.examen2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2.entities.PostEntity
import com.example.examen2.entities.UserEntity

class PostsAdapter(
    private val context: Context,
    private val posts: List<PostEntity>,
    private val onPostClick: (PostEntity) -> Unit
) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPostTitle: TextView = itemView.findViewById(R.id.tvPostTitle)
        val tvPostBody: TextView = itemView.findViewById(R.id.tvPostBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.post_adapter, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.tvPostTitle.text = post.title
        holder.tvPostBody.text = post.body

        holder.itemView.setOnClickListener {
            onPostClick(post)
        }
    }



    override fun getItemCount(): Int {
        return posts.size
    }
}
