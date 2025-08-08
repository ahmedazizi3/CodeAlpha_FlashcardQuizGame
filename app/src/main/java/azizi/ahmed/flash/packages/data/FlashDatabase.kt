package azizi.ahmed.flash.packages.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import azizi.ahmed.flash.packages.model.Flashcard
import azizi.ahmed.flash.packages.utils.IdConverter


@Database(entities = [Flashcard::class], version = 1, exportSchema = false)
@TypeConverters(IdConverter::class)
abstract class FlashDatabase: RoomDatabase() {
    abstract fun flashDao(): FlashDao
}
