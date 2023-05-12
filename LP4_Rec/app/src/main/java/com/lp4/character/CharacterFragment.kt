package com.lp4.character

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.lp4.adapter.PersonagemListAdapter
import com.lp4.character.presentation.CharacterViewModel
import com.lp4.character.presentation.model.CharacterViewState
import com.lp4.databinding.FragmentHeroiBinding
import com.lp4.home.domain.CategoryType
import com.lp4.model.Character

class CharacterFragment : Fragment() {

    private lateinit var binding: FragmentHeroiBinding

    private lateinit var characterViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterViewModel = CharacterViewModel()
        characterViewModel.listCharacter(categoryType)
        characterViewModel.viewState.observe(requireActivity()) { state ->
            when (state) {
                CharacterViewState.EmptyState -> showErrorMessage()
                CharacterViewState.HideLoading -> hideLoading()
                CharacterViewState.ShowLoadingState -> showLoading()
                is CharacterViewState.ShowListState -> showList(state.listCharacter)
            }
        }
    }

    private fun showErrorMessage() {
        Snackbar.make(binding.container, "Ocorreu algum erro!", Snackbar.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showList( charList:List<Character>) {
        val adapter = PersonagemListAdapter()
        binding.userList.adapter = adapter
        adapter.setItems(charList)
    }

    companion object {

        lateinit var categoryType: CategoryType

        fun newInstance(type: CategoryType): Fragment {
            categoryType = type
            return CharacterFragment()
        }

    }
}
