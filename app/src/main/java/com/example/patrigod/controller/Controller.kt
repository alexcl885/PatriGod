import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patrigod.FragmentoCardview
import com.example.patrigod.FragmentoCardviewDirections
import com.example.patrigod.MainActivity
import com.example.patrigod.adapter.AdapterMonumento
import com.example.patrigod.dao.MonumentoDAO
import com.example.patrigod.dialogues.DialogAddMonumento
import com.example.patrigod.dialogues.DialogDeleteMonumento
import com.example.patrigod.dialogues.DialogEditMonumento
import com.example.patrigod.models.Monumento

class Controller(val contextActivity : Context, val fragment: FragmentoCardview) {
    private val context = fragment.requireContext()
    lateinit var listMonumentos: MutableList<Monumento>
    private lateinit var adapterMonumento: AdapterMonumento
    private var layoutManager: LinearLayoutManager = LinearLayoutManager(context)



    fun initData() {

        listMonumentos = MonumentoDAO.myDao.getDataMonuments().toMutableList()
        setAdapter()
        initOnClickListener()
    }

    fun loggOut() {
        Toast.makeText(context, "He mostrado los datos en pantalla", Toast.LENGTH_LONG).show()
        listMonumentos.forEach { println(it) }
    }

    fun setAdapter() {
        adapterMonumento = AdapterMonumento(
            listMonumentos,
            { pos -> deleteMonumento(pos) }, // Eliminar un monumento
            { pos -> updateMonumento(pos) }, // Actualizar un monumento
            { pos ->
                fragment.findNavController().navigate(FragmentoCardviewDirections.actionFragmentoCardviewToDetallesFragment(idItem = pos))
            }
        )

        // Establecer el layoutManager y el adapter en el RecyclerView
        fragment.binding.myRecyclerView.layoutManager = layoutManager
        fragment.binding.myRecyclerView.adapter = adapterMonumento
    }


    fun deleteMonumento(pos: Int) {
        val myActivity = context as MainActivity
        val dialogDelete = DialogDeleteMonumento(pos){
            /*Logica para el dialogo para borrar un monumento*/
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
        dialogDelete.show(myActivity. supportFragmentManager, "Borramos un hotel")
    }

    private fun initOnClickListener() {
        fragment.binding.btnAdd.setOnClickListener {
            addMonumento()
        }
    }

    fun updateMonumento(pos: Int) {
        val editDialog = DialogEditMonumento( listMonumentos.get(pos)){
                editMonumento -> okOnEditMonumento(editMonumento, pos)
        }
        val myActivity = context as MainActivity
        editDialog.show(myActivity. supportFragmentManager, "Editamos un hotel")
    }

    private fun okOnEditMonumento(editMonumento: Monumento, pos: Int) {
        listMonumentos.removeAt(pos)
        adapterMonumento.notifyItemRemoved(pos) //Notificamos sólo a esa posición
        listMonumentos.add(pos, editMonumento)
        adapterMonumento.notifyItemInserted(pos)


        fragment.binding.myRecyclerView.post {
            layoutManager.scrollToPositionWithOffset(pos, 20)

        }
    }

    fun addMonumento() {
        Toast.makeText(context, "Añadiremos un nuevo monumento", Toast.LENGTH_LONG).show()
        val dialog = DialogAddMonumento { monumento -> okOnNewMonumento(monumento) }
        val myActivity = context as MainActivity
        dialog.show(myActivity.supportFragmentManager, "Añadimos un nuevo monumento")
    }

    private fun okOnNewMonumento(monumento: Monumento) {
        Log.d("Controler", "Añadiendo monumento: $monumento")
        listMonumentos.add(listMonumentos.size, monumento)
        adapterMonumento.notifyItemInserted(listMonumentos.lastIndex)


        fragment.binding.myRecyclerView.post {
            layoutManager.scrollToPositionWithOffset(listMonumentos.lastIndex, 34)

        }
    }
}
