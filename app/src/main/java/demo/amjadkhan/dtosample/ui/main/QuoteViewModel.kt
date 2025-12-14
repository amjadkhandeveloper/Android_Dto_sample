package demo.amjadkhan.dtosample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import demo.amjadkhan.dtosample.data.remote.RetrofitClient
import demo.amjadkhan.dtosample.data.repository.QuoteRepository
import kotlinx.coroutines.launch

class QuoteViewModel: ViewModel() {

    private val repository = QuoteRepository(RetrofitClient.api)

    private val _state = MutableLiveData<QuoteUIState>()

    val state: LiveData<QuoteUIState> = _state

    fun fetchQuote(id: Int){
        _state.value = QuoteUIState.Loading

        viewModelScope.launch {
            try {
                val quote = repository.getQuote(id)
                _state.value = QuoteUIState.Success(quote)
            }catch (e: Exception){
                _state.value = QuoteUIState.Error("Error getting quote : ${e.message}")
            }
        }
    }

}