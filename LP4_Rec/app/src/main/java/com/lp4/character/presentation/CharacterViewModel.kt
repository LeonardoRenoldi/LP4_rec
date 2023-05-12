package com.lp4.character.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lp4.character.domain.GetCharacterUseCase
import com.lp4.character.presentation.model.CharacterViewState
import com.lp4.home.domain.CategoryType
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private val getCharacterUseCase: GetCharacterUseCase by lazy {
        GetCharacterUseCase()
    }


    private val state = MutableLiveData<CharacterViewState>()
    val viewState: LiveData<CharacterViewState> = state

    fun listCharacter(categoryType: CategoryType) {
        state.value = CharacterViewState.ShowLoadingState

        viewModelScope.launch {
            val list = getCharacterUseCase.getCharacters()
            state.value = CharacterViewState.HideLoading

            if (list.isEmpty()) {
                state.value = CharacterViewState.EmptyState
            } else {
                state.value = CharacterViewState.ShowListState(
                    list.filter { it.characterType.lowercase() == categoryType.name.lowercase() }
                )
            }
        }
    }


}