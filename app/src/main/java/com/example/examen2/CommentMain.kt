package com.example.examen2

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2.entities.CommentEntity
import com.example.examen2.repositories.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommentMain : AppCompatActivity() {

    private lateinit var commentAdapter: CommentAdapter
    private val comments = mutableListOf<CommentEntity>()
    private val apiRepository = ApiRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewComments)
        val postUsername = findViewById<TextView>(R.id.tvcomentName)
        val username = intent.getStringExtra("username")
        recyclerView.layoutManager = LinearLayoutManager(this)
        commentAdapter = CommentAdapter(this, comments)
        recyclerView.adapter = commentAdapter

        val postId = intent.getLongExtra("postId", 0L)
        postUsername.text = "Post de $username"

        cargarComentarios(postId)
    }

    private fun cargarComentarios(postId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val obtenerComentarios = apiRepository.getCommentsByPostId(postId)
                withContext(Dispatchers.Main) {
                    comments.clear()
                    comments.addAll(obtenerComentarios)
                    commentAdapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@CommentMain, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
