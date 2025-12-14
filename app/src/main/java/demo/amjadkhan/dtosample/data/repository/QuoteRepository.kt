package demo.amjadkhan.dtosample.data.repository

import demo.amjadkhan.dtosample.data.mapper.toDomain
import demo.amjadkhan.dtosample.data.remote.api.QuoteApi
import demo.amjadkhan.dtosample.domain.model.Quote

class QuoteRepository(private val quoteApi: QuoteApi) {

    // function to get the quote from the server
    suspend fun getQuote(id: Int): Quote{
        return quoteApi.getQuote(id).toDomain()
    }
}