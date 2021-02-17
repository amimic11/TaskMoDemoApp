package com.example.taskmodemoapp.ui.frag.comment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmodemoapp.model.Comment
import com.example.taskmodemoapp.model.Post
import com.example.taskmodemoapp.repository.MainRepository
import com.example.taskmodemoapp.ui.frag.MainStateEvent
import com.example.taskmodemoapp.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CommentViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _dataState : MutableLiveData<DataState<List<Comment>>> = MutableLiveData()

    val dataState : LiveData<DataState<List<Comment>>> get() = _dataState

    fun setStateEvent(mainStateEvent : MainStateEvent) {
        viewModelScope.launch {
            when(mainStateEvent) {
                is MainStateEvent.GetPostEvent -> {
                    mainRepository.getComments().onEach {
                        dataState -> _dataState.value = dataState
                    }.launchIn(viewModelScope)
                }
                MainStateEvent.None -> {

                }
            }
        }
    }

}