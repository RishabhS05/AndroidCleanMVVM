package com.example.newapp.view.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newapp.data.models.CharacterList
import com.example.newapp.domain.Result.Success
import com.example.newapp.domain.repository.BaseRepoImplementation
import com.example.newapp.domain.repository.BaseRepoWithLiveData
import com.example.newapp.domain.repository.IRepository
import com.example.newapp.domain.repository.IRepositoryLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {
    val repo: IRepositoryLiveData by lazy {
        BaseRepoWithLiveData()
    }
    val repo2 : IRepository by lazy {
        BaseRepoImplementation()
    }
    private val _getCharacterLiveData = MutableLiveData<CharacterList>()
    val characterLiveData: LiveData<CharacterList> get() = _getCharacterLiveData

    fun getCharacters() {
        viewModelScope.launch {
            try {
                val response = repo.getCharactersListLiveData()
                _getCharacterLiveData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
                print("${e.message}")
            }
        }
    }

    private val _homeState = MutableStateFlow<HomeState>(HomeState())
    val state = _homeState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        _homeState.value
    )

    init {
       // getCharApiCall()
        getCharacters()
    }

    fun getCharApiCall() {
        viewModelScope.launch {
            repo2.getCharactersList().collect { result ->
                when (result) {
                    is Error -> {}
                    is Success -> {
                        _homeState.update {
                            it.copy(characterList = result.data)
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}