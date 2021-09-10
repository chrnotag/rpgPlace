package com.example.rpgplace.fragments.Contatos.Model

import android.os.Parcelable
import com.example.rpgplace.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactsModel(
    var uID: String = "",
    var nome: String = "",
    var photo: String = "",
    var stats: String = "",
): Parcelable