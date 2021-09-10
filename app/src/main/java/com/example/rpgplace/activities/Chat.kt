package com.example.rpgplace.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rpgplace.R
import kotlinx.android.synthetic.main.activity_chat.*

class Chat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        supportActionBar?.hide()

        val newString: String?
        newString = if (savedInstanceState == null) {
            val extras = intent.extras
            extras?.getString("nome")
        } else {
            savedInstanceState.getSerializable("nome") as String?
        }

        toolbar_chat.title = newString
    }
}