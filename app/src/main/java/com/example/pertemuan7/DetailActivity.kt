package com.example.pertemuan7

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.pertemuan7.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding
    lateinit var sharedPrefs : SharedPreferences
    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        sharedPrefs = getSharedPreferences("Data User", Context.MODE_PRIVATE)

        val userEmail = sharedPrefs.getString("user email", "")
        val userPassword = sharedPrefs.getString("user password", "")

        binding.txtEmail.text="Email : '${userEmail.toString()}'"
        binding.txtPassword.text = " Password : ${userPassword.toString()} "

        binding.btnLogout.setOnClickListener {
            with(sharedPrefs.edit()) {
                clear()
                apply()
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                android.R.id.home -> {
                    onBackPressed()
                    true
                }else -> super.onOptionsItemSelected(item)
            }
        }
}