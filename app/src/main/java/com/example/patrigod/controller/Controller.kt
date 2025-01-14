import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patrigod.Fragmentos.Anuncios
import com.example.patrigod.Fragmentos.FragmentoCardview
import com.example.patrigod.Fragmentos.FragmentoCardviewDirections
import com.example.patrigod.MainActivity
import com.example.patrigod.adapter.AdapterMonumento
import com.example.patrigod.adapterAnuncios.AdapterAnuncio
import com.example.patrigod.dao.AnuncioDAO
import com.example.patrigod.dao.MonumentoDAO
import com.example.patrigod.dialogues.DialogAddMonumento
import com.example.patrigod.dialogues.DialogDeleteMonumento
import com.example.patrigod.dialogues.DialogEditMonumento
import com.example.patrigod.models.Anuncio
import com.example.patrigod.models.Monumento

class Controller(val contextActivity: Context, val fragment: FragmentoCardview) {
    private val context = fragment.requireContext() // El contexto del fragmento
    lateinit var listMonumentos: MutableList<Monumento>
    private lateinit var adapterMonumento: AdapterMonumento
    private var layoutManager: LinearLayoutManager = LinearLayoutManager(context)

    lateinit var listAnuncios: MutableList<Anuncio>
    lateinit var adapterAnuncio: AdapterAnuncio


    fun initData() {
        // Cargar datos desde la base de datos o fuente
        listMonumentos = MonumentoDAO.myDao.getDataMonuments().toMutableList()
        listAnuncios = AnuncioDAO.myDao.getDataAnuncios().toMutableList()
        setAdapter()
        setAdapterAnuncio()
        initOnClickListener()
    }

    fun setAdapter() {
        adapterMonumento = AdapterMonumento(
            listMonumentos,
            { pos -> deleteMonumento(pos) },  // Eliminar un monumento
            { pos -> updateMonumento(pos) },  // Actualizar un monumento
            { pos -> navigateToDetails(pos) } // Navegar a detalles al hacer clic
        )

        // Configurar RecyclerView con LayoutManager y Adapter
        fragment.binding.myRecyclerView.layoutManager = layoutManager
        fragment.binding.myRecyclerView.adapter = adapterMonumento
    }

    fun setAdapterAnuncio() {
        adapterAnuncio = AdapterAnuncio(listAnuncios)
    }

    private fun navigateToDetails(pos: Int) {
        // Usar un método para navegar de forma más limpia
        fragment.findNavController().navigate(
            FragmentoCardviewDirections.actionFragmentoCardviewToDetallesFragment(
                idItem = listMonumentos[pos].id
            )
        )
    }

    fun deleteMonumento(pos: Int) {
        val myActivity = context as MainActivity
        val dialogDelete = DialogDeleteMonumento(pos) {
            if (pos in listMonumentos.indices) {
                listMonumentos.removeAt(pos)
                adapterMonumento.notifyItemRemoved(pos)
                adapterMonumento.notifyItemRangeChanged(pos, listMonumentos.size - pos)
                Toast.makeText(context, "Se eliminó el monumento en la posición $pos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Índice fuera de rango: $pos", Toast.LENGTH_LONG).show()
            }
        }
        dialogDelete.show(myActivity.supportFragmentManager, "Borramos un monumento")
    }

    private fun initOnClickListener() {
        fragment.binding.btnAdd.setOnClickListener {
            addMonumento()
        }
    }

    fun updateMonumento(pos: Int) {
        val editDialog = DialogEditMonumento(listMonumentos[pos]) { editMonumento ->
            okOnEditMonumento(editMonumento, pos)
        }
        val myActivity = context as MainActivity
        editDialog.show(myActivity.supportFragmentManager, "Editamos un monumento")
    }

    private fun okOnEditMonumento(editMonumento: Monumento, pos: Int) {
        listMonumentos[pos] = editMonumento
        adapterMonumento.notifyItemChanged(pos)

        fragment.binding.myRecyclerView.post {
            layoutManager.scrollToPositionWithOffset(pos, 20)  // Desplazar a la posición editada
        }
    }

    fun addMonumento() {
        Toast.makeText(context, "Añadiremos un nuevo monumento", Toast.LENGTH_LONG).show()
        val dialog = DialogAddMonumento { monumento -> okOnNewMonumento(monumento) }
        val myActivity = context as MainActivity
        dialog.show(myActivity.supportFragmentManager, "Añadimos un nuevo monumento")
    }

    private fun okOnNewMonumento(monumento: Monumento) {
        listMonumentos.add(monumento)
        adapterMonumento.notifyItemInserted(listMonumentos.lastIndex)

        fragment.binding.myRecyclerView.post {
            layoutManager.scrollToPositionWithOffset(listMonumentos.lastIndex, 34)  // Desplazar al nuevo monumento
        }
    }
}
