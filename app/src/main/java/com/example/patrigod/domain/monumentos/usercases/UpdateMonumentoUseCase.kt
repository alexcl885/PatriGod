package com.example.patrigod.domain.monumentos.usercases

import com.example.patrigod.data.monumentos.repository.MonumentoRepository
import com.example.patrigod.domain.monumentos.models.Monumento
import javax.inject.Inject

class UpdateMonumentoUseCase @Inject constructor(private val monumentoRepository: MonumentoRepository) {
    suspend operator fun invoke(id:Int, monumentoUpdate: Monumento): Boolean =
        monumentoRepository.update(id, monumentoUpdate)
}