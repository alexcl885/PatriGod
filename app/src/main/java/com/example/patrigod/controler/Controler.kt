import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patrigod.FragmentoCardview
import com.example.patrigod.MainActivity
import com.example.patrigod.adapter.AdapterMonumento
import com.example.patrigod.dao.MonumentoDAO
import com.example.patrigod.dialogues.DialogAddMonumento
import com.example.patrigod.dialogues.DialogDeleteMonumento
import com.example.patrigod.dialogues.DialogEditMonumento
import com.example.patrigod.models.Monumento

class Controler(val fragment: FragmentoCardview) {
    private val context = fragment.requireContext()
    lateinit var listMonumentos: MutableList<Monumento>
    private lateinit var adapter: AdapterMonumento
    private var layoutManager: LinearLayoutManager = LinearLayoutManager(context)

    init {
        initData()
        setAdapter()
        loggOut()
        initOnClickListener()
    }

    fun initData() {
        listMonumentos = MonumentoDAO.myDao.getDataMonuments().toMutableList()
    }

    fun loggOut() {
        Toast.makeText(context, "He mostrado los datos en pantalla", Toast.LENGTH_LONG).show()
        listMonumentos.forEach { println(it) }
    }

    fun setAdapter() {
        adapter = AdapterMonumento(
            listMonumentos,
            { pos -> deleteMonumento(pos) },
            { pos -> updateMonumento(pos) }
        )
        fragment.binding.myRecyclerView.layoutManager = layoutManager
        fragment.binding.myRecyclerView.adapter = adapter
    }

    fun deleteMonumento(pos: Int) {
        val dialogDelete = DialogDeleteMonumento(pos) {
            if (pos in listMonumentos.indices) {
                listMonumentos.removeAt(pos)
                fragment.binding.myRecyclerView.adapter?.apply {
                    notifyItemRemoved(pos)
                    notifyItemRangeChanged(pos, listMonumentos.size - pos)
                }
                Toast.makeText(context, "Se eliminó el monumento en la posición $pos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Índice fuera de rango: $pos", Toast.LENGTH_LONG).show()
            }
        }
        dialogDelete.show(fragment.parentFragmentManager, "Borramos un hotel")
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
        editDialog.show(fragment.parentFragmentManager, "Editamos un hotel")
    }

    private fun okOnEditMonumento(editMonumento: Monumento, pos: Int) {
        listMonumentos.removeAt(pos)
        adapter.notifyItemRemoved(pos)
        listMonumentos.add(pos, editMonumento)
        adapter.notifyItemInserted(pos)

        fragment.binding.myRecyclerView.post {
            layoutManager.scrollToPositionWithOffset(pos, 20)
        }
    }

    fun addMonumento() {
        Toast.makeText(context, "Añadiremos un nuevo monumento", Toast.LENGTH_LONG).show()
        val dialog = DialogAddMonumento { monumento ->
            okOnNewMonumento(monumento)
        }
        dialog.show(fragment.parentFragmentManager, "Añadimos un nuevo monumento")
    }

    private fun okOnNewMonumento(monumento: Monumento) {
        listMonumentos.add(listMonumentos.size, monumento)
        adapter.notifyItemInserted(listMonumentos.lastIndex)

        fragment.binding.myRecyclerView.post {
            layoutManager.scrollToPositionWithOffset(listMonumentos.lastIndex, 34)
        }
    }
}
