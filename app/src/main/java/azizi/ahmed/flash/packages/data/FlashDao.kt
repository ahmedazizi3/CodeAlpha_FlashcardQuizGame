package azizi.ahmed.flash.packages.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import azizi.ahmed.flash.packages.model.Flashcard
import kotlinx.coroutines.flow.Flow
import java.util.UUID


@Dao
interface FlashDao {

//    Add a Flashcard to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFlashcard(flashcard: Flashcard)

//    Read all Flashcards from the database
    @Query("SELECT * FROM FlashcardsTable")
    fun getAllFlashcards(): Flow<List<Flashcard>>

//    Update a Flashcard in the database
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFlashcard(flashcard: Flashcard)

//    Delete a Flashcard from the database
    @Delete
    suspend fun deleteFlashcard(flashcard: Flashcard)
}