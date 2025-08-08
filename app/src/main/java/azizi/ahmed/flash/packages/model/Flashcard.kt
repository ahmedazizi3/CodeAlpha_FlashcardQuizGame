package azizi.ahmed.flash.packages.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "FlashcardsTable")
data class Flashcard (
    @PrimaryKey
    val flashcardId: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "FlashcardQuestion")
    val flashcardQuestion: String,

    @ColumnInfo(name = "FlashcardAnswer")
    val flashcardAnswer: String,
)