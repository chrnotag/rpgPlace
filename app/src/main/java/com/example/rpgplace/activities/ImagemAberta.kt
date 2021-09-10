package com.example.rpgplace.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import com.example.rpgplace.R
import kotlinx.android.synthetic.main.activity_imagem_aberta.*

class ImagemAberta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagem_aberta)
        supportActionBar?.hide()

        toolbar_imagem_aberta.setNavigationOnClickListener {
            this.finish()
        }
    }
}