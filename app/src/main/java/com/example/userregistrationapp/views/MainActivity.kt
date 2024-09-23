package com.example.userregistrationapp.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.userregistrationapp.R


class MainActivity : AppCompatActivity() {
    private lateinit var userListBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userListBtn = findViewById(R.id.showUserListBtn)
        userListBtn.setOnClickListener {
            val intent = Intent(this, LoadingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}