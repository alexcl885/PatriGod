package com.example.patrigod.ui.viewModel.monumentos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.patrigod.domain.monumentos.models.ListMonumentos
import com.example.patrigod.domain.monumentos.models.Monumento
import com.example.patrigod.domain.monumentos.usercases.DeleteMonumentoUseCase
import com.example.patrigod.domain.monumentos.usercases.GetMonumentoByPosUseCase
import com.example.patrigod.domain.monumentos.usercases.GetMonumentosNativeUseCase
import com.example.patrigod.domain.monumentos.usercases.GetMonumentosUseCase
import com.example.patrigod.domain.monumentos.usercases.NewMonumentoUseCase
import com.example.patrigod.domain.monumentos.usercases.UpdateMonumentoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MonumentoViewModel @Inject constructor(
    private val getAllMonumentsUseCase: GetMonumentosNativeUseCase,
    private val newMonumentoUseCase: NewMonumentoUseCase,
    private val getMonumentoForPosUseCase: GetMonumentoByPosUseCase,
    private val deleteMonumentoUseCase: DeleteMonumentoUseCase,
    private val updateMonumentoUseCase: UpdateMonumentoUseCase,
    private val getMonumentosUseCase: GetMonumentosUseCase
) : ViewModel() {

    val monumentosLiveData = MutableLiveData<List<Monumento>>()
    val newMonumentoLiveData = MutableLiveData<Monumento>()
    val posDeleteHotelLiveDate = MutableLiveData<Int>()
    val posUpdateMonumentoLiveData = MutableLiveData<Int>()
    val detailMonumentoLiveData = MutableLiveData<Monumento>()

    fun showMonumentos() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = if (ListMonumentos.ciudades.monumentos.isEmpty())
                getAllMonumentsUseCase()
            else
                getMonumentosUseCase()

            data?.let {
                monumentosLiveData.postValue(it)
                ListMonumentos.ciudades.monumentos = it.toMutableList()
            }
        }
    }

    fun addMonumento(monumento: Monumento) {
        viewModelScope.launch(Dispatchers.IO) {
            // Se intenta agregar el nuevo monumento
            val new = newMonumentoUseCase(monumento)
            new?.let {
                showMonumentos()
                newMonumentoLiveData.postValue(monumento)
            }
        }
    }

    fun deleteMonumento(pos: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = deleteMonumentoUseCase(pos)
            result?.let {
                posDeleteHotelLiveDate.postValue(pos)
            }
        }
    }

    fun updateMonumento(id: Int, monumento: Monumento) {
        viewModelScope.launch(Dispatchers.IO) {
            val updated = updateMonumentoUseCase(id, monumento)
            updated?.let {
                val updatedList = monumentosLiveData.value?.toMutableList() ?: mutableListOf()
                val index = updatedList.indexOfFirst { it.id == id }
                if (index != -1) {
                    updatedList[index] = monumento
                    monumentosLiveData.postValue(updatedList)
                }
            }
        }
    }

    suspend fun getMonumentosForPosition(pos: Int) {
        val monumento = getMonumentoForPosUseCase(pos)
        monumento?.let {
            detailMonumentoLiveData.value = it
        }
    }
}
