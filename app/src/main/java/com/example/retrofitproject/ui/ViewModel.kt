package com.example.retrofitproject.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.retrofitproject.model.Blog
import com.example.retrofitproject.repository.MainRepository
import com.example.retrofitproject.util.DataState

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class MainViewModel
@ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _datastate : MutableLiveData<DataState<List<Blog>>> = MutableLiveData()
    val datastate : LiveData<DataState<List<Blog>>>
        get() = _datastate


    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogEvents ->{
                    mainRepository.getBlog()
                        .onEach { dataState ->
                            _datastate.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.None ->{
                    //
                }
            }
        }
    }
}

sealed class MainStateEvent{
    object GetBlogEvents :  MainStateEvent()
    object None : MainStateEvent()
}