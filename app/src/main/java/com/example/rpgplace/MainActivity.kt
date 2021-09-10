package com.example.rpgplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.rpgplace.activities.Edit_Perfil
import com.example.rpgplace.activities.HomeActivity
import com.example.rpgplace.activities.Register
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var user: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        user = FirebaseAuth.getInstance()

        if (user.currentUser != null) {
            startActivity(Intent(this, Edit_Perfil::class.java))
        }

        registrar.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
        entrar.setOnClickListener {
           signIn(login.text.toString(), senha_login.text.toString())
        }
    }

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        user.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(Register.TAG, "signInWithEmail:success")
                    val user = user.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(Register.TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {

        if (user != null){
            startActivity(Intent(this, Edit_Perfil::class.java))
        }

    }
}