import android.content.Context
import com.example.patrigod.ui.views.fragments.monumentos.FragmentoMonumento

class Controller(val contextActivity: Context, val fragment: FragmentoMonumento) {
    /*private val context = fragment.requireContext()
    lateinit var listMonumentos: MutableList<Monumento>
    private lateinit var adapterMonumento: AdapterMonumento
    private var layoutManager: LinearLayoutManager = LinearLayoutManager(context)



    fun initData() {

        listMonumentos = MonumentoDAO.myDao.getDataMonuments().toMutableList()
        setAdapter()
        initOnClickListener()
    }

    fun setAdapter() {
        adapterMonumento = AdapterMonumento(
            listMonumentos,
            { pos -> deleteMonumento(pos) },  // eliminar un monumento
            { pos -> updateMonumento(pos) },  // actualizar un monumento
            { pos -> navigateToDetails(pos) } // navegar a detalles al hacer clic
        )

        // Configurar RecyclerView con LayoutManager y Adapter
        fragment.binding.myRecyclerView.layoutManager = layoutManager
        fragment.binding.myRecyclerView.adapter = adapterMonumento
    }


    private fun navigateToDetails(pos: Int) {

        fragment.findNavController().navigate(
            FragmentoMonumentoDirections.actionFragmentoCardviewToDetallesFragment(
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
    }*/
}
