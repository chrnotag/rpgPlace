package com.example.rpgplace.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rpgplace.R
import com.example.rpgplace.adapters.fragmentAdapter
import com.example.rpgplace.fragments.Camera.CameraFragment
import com.example.rpgplace.fragments.Contatos.Contatos
import com.example.rpgplace.fragments.Conversas.Home
import com.example.rpgplace.fragments.Status.Status
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        toolbar.title = "RPG Place"
        viewContainer.adapter = fragmentAdapter(listOf(CameraFragment(),Home(), Status(), Contatos()), supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, viewContainer) { tab, position ->
            when(position){
                0 -> {
                    tab.icon = getDrawable(R.drawable.ic_baseline_camera_alt_24)
                }

                1 -> {
                    tab.text = "Conversas"
                }

                2 -> {
                    tab.text = "Status"
                }

                3 -> {
                    tab.text = "Contatos"
                }
            }
        }.attach()
    }
}