package com.lp4.character.presentation.model

import com.lp4.model.Character

sealed class CharacterViewState {

    //vazia
    //loading
    //done

    object EmptyState : CharacterViewState()


    object ShowLoadingState : CharacterViewState()

    object HideLoading: CharacterViewState()

    data class ShowListState(val listCharacter: List<Character>) : CharacterViewState()

}