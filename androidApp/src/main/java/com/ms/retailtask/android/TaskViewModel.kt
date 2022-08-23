package com.ms.retailtask.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ms.retailtask.RetailsTaskRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import model.RetailTaskModel

class TaskViewModel :ViewModel(){
    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow<TaskState>(TaskState.Empty)
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<TaskState> = _uiState
    val repo = RetailsTaskRepo
    init {
        _uiState.value = TaskState.Process
        viewModelScope.launch(Dispatchers.IO) {
            val toDayTaskList = repo.getToDayTaskList()
            delay(100)
            viewModelScope.launch(Dispatchers.Main){
                _uiState.value = TaskState.Success(toDayTaskList)
            }
        }

    }


}

sealed class TaskState{
    object Process:TaskState()
    object Empty:TaskState()
    class Success(val list: List<RetailTaskModel>):TaskState()
}

