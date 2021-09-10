package com.example.rpgplace.activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.rpgplace.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_edit_perfil.*
import java.io.ByteArrayOutputStream

class Edit_Perfil : AppCompatActivity() {

    var uid = FirebaseAuth.getInstance().currentUser?.uid //Get User Id
    var storageRef = FirebaseStorage.getInstance().reference //Storage Reference Variable
    var perfilImgRef = storageRef.child("$uid/PerfilImg/PerfilImg.jpg") //Path Img Storage
    var firestoreRef = FirebaseFirestore.getInstance() //FireStore Reference Variable
    var ImgUrl = ""

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
        Perfil_edit_img.setImageBitmap(bitmap)
        tratamento_imagem()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_perfil)

        salvar_perfil.setOnClickListener {
//Start Save Perfil Informations
            firestoreRef.collection(uid.toString())
                .add(perfil_info(nick.text.toString(), ImgUrl))
//End Save Perfil Informations
        }

        button.setOnClickListener {
            getContent.launch("image/*")
        }


    }

    private fun SelecionarFoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)
    }

    fun tratamento_imagem() {
        //Start Save Image Perfil
        Perfil_edit_img.isDrawingCacheEnabled = true
        Perfil_edit_img.buildDrawingCache()
        val bitmap = (Perfil_edit_img.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        var uploadTask = perfilImgRef.putBytes(data)
        uploadTask.addOnProgressListener {
            progressBar.visibility = View.VISIBLE
            val progress = (100.0 * it.bytesTransferred / it.totalByteCount)
            val currentProgress: Int = progress.toInt()
            progressBar.progress = currentProgress
            if (it.bytesTransferred == it.totalByteCount) {
                progressBar.visibility = View.GONE
            }
        }
        //End Save Image Perfil
    }

    //Data Class Perfil Infos
    data class perfil_info(
        var nickname: String = "",
        var imgUrl: String = "",
    )
}