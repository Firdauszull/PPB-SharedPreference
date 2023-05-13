package com.example.pertemuan7

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pertemuan7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var dbHelper: UserDbHelper
    lateinit var sharedPrefs : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding. root)

        sharedPrefs = getSharedPreferences("DataUser", Context.MODE_PRIVATE)

        dbHelper = UserDbHelper(this)
        binding.btnLogin.setOnClickListener{
            val intent = Intent(this,DetailActivity::class.java)
            startActivity(intent)
        }

    binding.btnRegister.setOnClickListener{
    val intent = Intent(this, RegisterActivity::class.java)
    startActivity(intent)

        }
    }
}