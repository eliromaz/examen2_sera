package com.example.examen2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2.entities.UserEntity

class UserAdapter(
    private val context: Context,
    private val userList: List<UserEntity>,
    private val onUserClick: (UserEntity) -> Unit, // Callback para clic corto
    private val onUserLongClick: (UserEntity) -> Unit // Callback para clic largo
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // ViewHolder interno para manejar la vista de cada item
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNameUsername: TextView = itemView.findViewById(R.id.tvNameUsername)
        val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // Infla el layout del item
        val view = LayoutInflater.from(context).inflate(R.layout.activity_main_adapter_rv, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.tvNameUsername.text = "${user.name} (@${user.username})"
        holder.tvEmail.text = user.email

        // Listener para clic corto
        holder.itemView.setOnClickListener {
            onUserClick(user)
        }

        // Listener para clic largo
        holder.itemView.setOnLongClickListener {
            onUserLongClick(user)
            true
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
