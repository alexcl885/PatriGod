package com.example.patrigod.domain.monumentos.usercases

import com.example.patrigod.data.monumentos.repository.MonumentoRepository
import com.example.patrigod.domain.monumentos.models.Monumento
import javax.inject.Inject

class GetMonumentosUseCase @Inject constructor(private val monumentoRepository: MonumentoRepository ){
    suspend operator fun invoke(): MutableList<Monumento> = monumentoRepository.getMonumentos().toMutableList()
}