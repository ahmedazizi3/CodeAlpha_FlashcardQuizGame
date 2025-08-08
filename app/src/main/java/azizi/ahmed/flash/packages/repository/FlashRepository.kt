package azizi.ahmed.flash.packages.repository

import azizi.ahmed.flash.packages.data.FlashDao
import azizi.ahmed.flash.packages.model.Flashcard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FlashRepository @Inject constructor(private val flashDao: FlashDao) {

    suspend fun addFlashcard(flashcard: Flashcard) = flashDao.addFlashcard(flashcard)

    suspend fun updateFlashcard(flashcard: Flashcard) = flashDao.updateFlashcard(flashcard)

    suspend fun deleteFlashcard(flashcard: Flashcard) = flashDao.deleteFlashcard(flashcard)

    fun getAllFlashcards(): Flow<List<Flashcard>> =
        flashDao
            .getAllFlashcards()
            .flowOn(Dispatchers.IO)
            .conflate()
}