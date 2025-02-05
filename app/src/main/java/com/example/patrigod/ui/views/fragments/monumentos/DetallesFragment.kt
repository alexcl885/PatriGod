    package com.example.patrigod.ui.views.fragments.monumentos

    import android.os.Bundle
    import androidx.fragment.app.Fragment
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.navigation.fragment.navArgs
    import com.bumptech.glide.Glide
    import com.example.patrigod.databinding.FragmentDetallesBinding

    class DetallesFragment : Fragment() {
        private lateinit var binding: FragmentDetallesBinding
        private val args: DetallesFragmentArgs by navArgs() //argumento seguro


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            binding = FragmentDetallesBinding.inflate(inflater, container, false)
            return binding.root
        }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val idItem = args.idItem

            val monumento = MonumentoDAO.myDao.getDataMonuments()[idItem]

            binding.nameMonument.text = monumento.nombre
            binding.descriptionMonument.text = monumento.descripcionPlus
            Glide.with(this)//con glide incrusto la imagen mediante una url
                .load(monumento.imagen)
                .centerCrop()
                .into(binding.imageMonument)

        }


    }