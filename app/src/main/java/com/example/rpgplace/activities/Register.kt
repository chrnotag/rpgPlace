package com.example.rpgplace.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.rpgplace.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        confirm.setOnClickListener {
            createAccount(email.text.toString(), senha.text.toString())
        }
    }

    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]

        if (senha.text.toString() === confsenha.text.toString()) {

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        } else {
            senha.setBackgroundColor(Color.RED)
            confsenha.setBackgroundColor(Color.RED)
            senha.setText("")
            confsenha.setText("")
            Snackbar.make(constrain, "As senhas não coincidem", Snackbar.LENGTH_SHORT).show()
        }
        // [END create_user_with_email]
    }

    private fun updateUI(user: FirebaseUser?) {

        if (user != null) {
            startActivity(Intent(this, Edit_Perfil::class.java))
        }

    }

    companion object {
        const val TAG = "EmailPassword"
    }
}