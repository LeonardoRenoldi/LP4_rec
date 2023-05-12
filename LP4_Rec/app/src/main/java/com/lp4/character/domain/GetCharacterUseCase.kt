package com.lp4.character.domain

import com.lp4.character.data.remote.CharacterRepository
import com.lp4.model.Character

class GetCharacterUseCase {

    private val repository:CharacterRepository by lazy {
        CharacterRepository()
    }

    suspend fun getCharacters():List<Character>{
        return repository.fetchCharacters()
    }
}