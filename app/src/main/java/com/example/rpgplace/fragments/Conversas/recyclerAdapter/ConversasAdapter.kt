import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.rpgplace.R
import com.example.rpgplace.activities.Chat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import de.hdodenhof.circleimageview.CircleImageView

class ConversasAdapter : RecyclerView.Adapter<ConversasAdapter.ViewHolder>() {

    val storageRef = FirebaseStorage.getInstance().reference
    val uid = FirebaseAuth.getInstance().currentUser

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nome: TextView
        val ultima_msg: TextView
        val data: TextView
        val img_perfil: CircleImageView
        val layout: ConstraintLayout

        init {
            nome = itemView.findViewById(R.id.Nome)
            ultima_msg = itemView.findViewById(R.id.ultima_msg)
            data = itemView.findViewById(R.id.Data)
            img_perfil = itemView.findViewById(R.id.perfil_img)
            layout = itemView.findViewById(R.id.layoutConst)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.conversas_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nome.text = nomes[position]
        holder.ultima_msg.text = ultimaMensagem[position]

        holder.layout.setOnClickListener {
            val intent =
                Intent(holder.itemView.context, Chat::class.java).putExtra("nome", nomes[position])
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int = nomes.size

    val nomes = listOf<String>("Felippe", "Marcelo", "Bruna", "Caio")

    val ultimaMensagem = listOf<String>("Iae cara", "Fala comigo", "oi?", "fala")

    val imgUrl = storageRef.child("$uid/PerfilImg/PerfilImg.jpg").downloadUrl

    data class conversas(
        var nickname: String = "",
        var mensagem: String = "",
    )

}