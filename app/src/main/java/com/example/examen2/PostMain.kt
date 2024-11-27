package com.example.examen2

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2.entities.PostEntity
import com.example.examen2.repositories.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostMain : AppCompatActivity() {

    private lateinit var postsAdapter: PostsAdapter
    private val posts = mutableListOf<PostEntity>()
    private val apiRepository = ApiRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_post)

        val userId = intent.getLongExtra("userId", -1)
        val userInfoText = findViewById<TextView>(R.id.tvUserInfo)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPosts)


        val username = intent.getStringExtra("username")
        userInfoText.text = "Publicado por $username"


        recyclerView.layoutManager = LinearLayoutManager(this)
        postsAdapter = PostsAdapter(this, posts) { post ->
            val intent = Intent(this, CommentMain::class.java).apply {
                putExtra("postId", post.id)
                putExtra("postTitle", post.title)
                putExtra("username", username)
            }
            startActivity(intent)
        }
        recyclerView.adapter = postsAdapter

        if (userId != -1L) {
            cargarPostsDeUsuario(userId)
        } else {
            Toast.makeText(this, "Error: Invalid user ID", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cargarPostsDeUsuario(userId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userPosts = apiRepository.getPostsByUserId(userId)
                withContext(Dispatchers.Main) {
                    posts.clear()
                    posts.addAll(userPosts)
                    postsAdapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@PostMain, "Error loading posts: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
