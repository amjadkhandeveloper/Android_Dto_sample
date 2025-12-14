package demo.amjadkhan.dtosample.ui.main

import demo.amjadkhan.dtosample.domain.model.Quote

// we create sealed class to handle the state of the quote
sealed class QuoteUIState {
    object Loading: QuoteUIState()
    data class Success(val quote: Quote): QuoteUIState()
    data class Error(val message: String): QuoteUIState()
}