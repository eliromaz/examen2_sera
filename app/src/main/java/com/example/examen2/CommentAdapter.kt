package com.example.examen2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2.entities.CommentEntity

class CommentAdapter(
    private val context: Context,
    private val commentList: List<CommentEntity>
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvCommentTitle)
        val tvUsername: TextView = itemView.findViewById(R.id.tvUser)
        val tvBody: TextView = itemView.findViewById(R.id.tvCommentBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_comment_adapter, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = commentList[position]
        holder.tvTitle.text = comment.name
        holder.tvUsername.text = comment.email
        holder.tvBody.text = comment.body
    }

    override fun getItemCount(): Int {
        return commentList.size
    }
}
