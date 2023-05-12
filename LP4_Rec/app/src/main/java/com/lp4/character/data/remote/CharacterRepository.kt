package com.lp4.character.data.remote

import android.content.Context
import android.content.SharedPreferences
import com.lp4.App
import com.lp4.api.CharacterClient
import com.lp4.model.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository {

    private val sharedPreferences: SharedPreferences by lazy {
        App.context.getSharedPreferences("user", Context.MODE_PRIVATE)
    }

    private val characterClient by lazy {
        CharacterClient()
    }

    suspend fun fetchCharacters(): List<Character> {
        val id = sharedPreferences.getInt("id", 0)
       return withContext(Dispatchers.IO) {
             characterClient.getCharacter(id.toString()).orEmpty()
        }

    }


}