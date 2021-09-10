package com.example.rpgplace.fragments.Contatos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rpgplace.R
import com.example.rpgplace.fragments.Contatos.Model.ContactsModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.contatos_fragment.*
import kotlinx.android.synthetic.main.conversas_view.view.*

class Contatos : Fragment() {

    companion object {
        fun newInstance() = Contatos()
    }

    var Reference = FirebaseFirestore.getInstance()
    var uID = FirebaseAuth.getInstance().currentUser

    private lateinit var viewModel: ContatosViewModel
    lateinit var adapter: GroupAdapter<ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.contatos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContatosViewModel::class.java)
        adapter = GroupAdapter()
        contatos_recycler.adapter = adapter

        SearchContacts()
    }

    private inner class ContatosItem(internal val adContatos: ContactsModel) : Item<ViewHolder>() {
        override fun getLayout(): Int {
            return R.layout.conversas_view
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.Nome.text = adContatos.nome
            viewHolder.itemView.ultima_msg.text = adContatos.stats
            Picasso.get().load(adContatos.photo).into(viewHolder.itemView.perfil_img)
        }

    }

    private fun SearchContacts() {
        Reference.collection(uID.toString()).addSnapshotListener { value, error ->
            error?.let {
                return@addSnapshotListener
            }
            value?.let {
                for (doc in value) {
                    val amigos = doc.toObject(ContactsModel::class.java)
                    adapter.add(ContatosItem(amigos))
                }
            }
        }
    }

}