import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patrigod.MainActivity
import com.example.patrigod.adapter.AdapterMonumento
import com.example.patrigod.dao.MonumentoDAO
import com.example.patrigod.dialogues.DialogAddMonumento
import com.example.patrigod.dialogues.DialogEditMonumento
import com.example.patrigod.models.Monumento

class Controler(val context: Context) {
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
        val myActivity = context as MainActivity
        adapter = AdapterMonumento(
            listMonumentos,
            { pos -> deleteMonumento(pos) },
            { pos -> updateMonumento(pos) }
        )
        myActivity.binding.myRecyclerView.layoutManager = layoutManager
        myActivity.binding.myRecyclerView.adapter = adapter
    }

    fun deleteMonumento(pos: Int) {
        val myActivity = context as MainActivity
        if (pos in listMonumentos.indices) {
            listMonumentos.removeAt(pos)
            myActivity.binding.myRecyclerView.adapter?.apply {
                notifyItemRemoved(pos)
                notifyItemRangeChanged(pos, listMonumentos.size - pos)
            }
            Toast.makeText(context, "Se eliminó el monumento en la posición $pos", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Índice fuera de rango: $pos", Toast.LENGTH_LONG).show()
        }
    }

    private fun initOnClickListener() {
        val myActivity = context as MainActivity
        myActivity.binding.btnAdd.setOnClickListener {
            addMonumento()
        }
    }
    /*
    * Metodos que actualiza un item a nuestra lista de elementos
    * */
    fun updateMonumento(pos: Int) {
        val editDialog = DialogEditMonumento( listMonumentos.get(pos)){
                editMonumento -> okOnEditMonumento(editMonumento, pos)
        }
        val myActivity = context as MainActivity
        editDialog.show(myActivity. supportFragmentManager, "Editamos un hotel")
    }

    private fun okOnEditMonumento(editMonumento: Monumento, pos: Int) {
        listMonumentos.removeAt(pos)
        adapter.notifyItemRemoved(pos) //Notificamos sólo a esa posición
        listMonumentos.add(pos, editMonumento)
        adapter.notifyItemInserted(pos)

        val myActivity = context as MainActivity
        myActivity.binding.myRecyclerView.post {
            layoutManager.scrollToPositionWithOffset(pos, 20)

        }
    }

    /*
    * Metodos que añade un nuevo item a nuestra lista de elementos
    * */
    fun addMonumento() {
        Toast.makeText(context, "Añadiremos un nuevo monumento", Toast.LENGTH_LONG).show()
        val dialog = DialogAddMonumento { monumento -> okOnNewMonumento(monumento) }
        val myActivity = context as MainActivity
        dialog.show(myActivity.supportFragmentManager, "Añadimos un nuevo monumento")
    }
    private fun okOnNewMonumento(monumento: Monumento) {
        Log.d("Controler", "Añadiendo monumento: $monumento")
        listMonumentos.add(listMonumentos.size, monumento)
        adapter.notifyItemInserted(listMonumentos.lastIndex)

        val myActivity = context as MainActivity
        myActivity.binding.myRecyclerView.post {
            layoutManager.scrollToPositionWithOffset(listMonumentos.lastIndex, 34)

        }
    }
}
