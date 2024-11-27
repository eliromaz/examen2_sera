package com.example.examen2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<Button>(R.id.btnListaHeroes).setOnClickListener {
            startActivity(Intent(this, UserMain::class.java))
        }

        findViewById<Button>(R.id.btnCreditos).setOnClickListener {
            startActivity(Intent(this, Creditos::class.java))
        }

        findViewById<Button>(R.id.btnSalir).setOnClickListener {
            finish()
        }
    }
}