package com.example.examen2

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2.data.AppDatabase
import com.example.examen2.entities.UserEntity
import com.example.examen2.repositories.ApiRepository
import com.example.examen2.utils.toEntidadUsuario
import com.example.examen2.utils.toUserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserMain : AppCompatActivity() {

    private lateinit var recyclerAdapter: UserAdapter
    private val users = mutableListOf<UserEntity>()
    private val apiRepository = ApiRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_recycler_view)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewUsers)

        recyclerView.layoutManager = LinearLayoutManager(this)


        recyclerAdapter = UserAdapter(
            context = this,
            userList = users,
            onUserClick = { selectedUser -> // Clic corto
                val intent = Intent(this, PostMain::class.java).apply {
                    putExtra("userId", selectedUser.id) // Enviar ID del usuario
                    putExtra("username", selectedUser.username) // Enviar nombre de usuario
                }
                startActivity(intent)
            },
            onUserLongClick = { selectedUser -> // Clic largo
                guardarUsuarioEnBaseDeDatos(selectedUser)
            }
        )

        recyclerView.adapter = recyclerAdapter


        cargarDatosIniciales()
    }

    private fun cargarDatosIniciales() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (isInternetAvailable(this@UserMain)) {
                    // Si hay conexión a internet, obtener los usuarios de la API
                    val obtenerUsers = apiRepository.getAllUsers()
                    withContext(Dispatchers.Main) {
                        users.clear()
                        users.addAll(obtenerUsers)
                        recyclerAdapter.notifyDataSetChanged()
                    }
                } else {
                    // Si no hay conexión, cargar los usuarios desde Room
                    val userDao = AppDatabase.getDatabase(this@UserMain).userDao()
                    val usuariosGuardados = userDao.getAllUsers() // Recuperar todos los usuarios guardados
                    withContext(Dispatchers.Main) {
                        users.clear()
                        users.addAll(usuariosGuardados.map { it.toUserEntity() }) // Convertir a UserEntity
                        recyclerAdapter.notifyDataSetChanged()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@UserMain, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun guardarUsuarioEnBaseDeDatos(user: UserEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Conversión de UserEntity a EntidadUsuario
                val usuarioRoom = user.toEntidadUsuario()
                val userDao = AppDatabase.getDatabase(this@UserMain).userDao()
                userDao.insertUser(usuarioRoom)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@UserMain, "Usuario guardado: ${user.username}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@UserMain, "Error al guardar usuario: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork?.let {
                connectivityManager.getNetworkCapabilities(it)
            }
        } else {
            null
        }
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}
