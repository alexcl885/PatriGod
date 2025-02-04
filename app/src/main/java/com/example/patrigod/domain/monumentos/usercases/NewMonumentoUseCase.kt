package com.example.patrigod.domain.monumentos.usercases

import com.example.patrigod.data.monumentos.repository.MonumentoRepository
import com.example.patrigod.domain.monumentos.models.Monumento
import javax.inject.Inject

class NewMonumentoUseCase @Inject constructor(private val monumentoRepository: MonumentoRepository) {
    suspend operator fun invoke(newMonumento: Monumento):Monumento?{
        return if (!monumentoRepository.exisMonumento(newMonumento)){
            return monumentoRepository.addMonumento(newMonumento)
        }else
            null
    }
}