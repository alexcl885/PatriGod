package com.example.patrigod.domain.monumentos.usercases

import com.example.patrigod.data.monumentos.repository.MonumentoRepository
import com.example.patrigod.domain.monumentos.models.Monumento
import javax.inject.Inject

class DeleteMonumentoUseCase @Inject constructor(private val monumentoRepository: MonumentoRepository) {
    suspend operator fun invoke(pos:Int): Boolean = monumentoRepository.deleteMonumento(pos)
}