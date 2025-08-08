package azizi.ahmed.flash.packages.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import azizi.ahmed.flash.packages.model.Flashcard
import azizi.ahmed.flash.packages.repository.FlashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlashViewModel @Inject constructor(private val repository: FlashRepository): ViewModel() {
    private val _flashcardsList = MutableStateFlow<List<Flashcard>>(emptyList())
    val flashcardsList = _flashcardsList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFlashcards().collect {listOfFlashcards ->
                _flashcardsList.value = listOfFlashcards
            }
        }
    }

//    Create a new Flashcard
    fun addFlashcard(flashcard: Flashcard) {
        viewModelScope.launch {
            repository.addFlashcard(flashcard)
        }
    }

//    Update an existing Flashcard
    fun updateFlashcard(flashcard: Flashcard) {
        viewModelScope.launch {
            repository.updateFlashcard(flashcard)
        }
    }

//    Delete a Flashcard
    fun deleteFlashcard(flashcard: Flashcard) {
        viewModelScope.launch {
            repository.deleteFlashcard(flashcard)
        }
    }

//    Get a Flashcard by ID
    fun getFlashcard(flashcardId: String): Flashcard? {
        return _flashcardsList.value.find {
            it.flashcardId.toString() == flashcardId
        }
    }
}