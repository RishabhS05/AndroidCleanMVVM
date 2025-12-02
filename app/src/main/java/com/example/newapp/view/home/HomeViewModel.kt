package com.example.newapp.view.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapp.domain.Result.Success
import com.example.newapp.domain.repository.BaseRepoImplementation
import com.example.newapp.domain.repository.IRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {
    val repo: IRepository by lazy {
        BaseRepoImplementation()
    }
    private val _homeState = MutableStateFlow<HomeState>(HomeState())
    val state = _homeState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        _homeState.value
    )

    init {
        getCharApiCall()
    }

    fun getCharApiCall() {
        viewModelScope.launch {
            repo.getCharactersList().collect { result ->
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