package demo.amjadkhan.dtosample.data.remote.api

import demo.amjadkhan.dtosample.data.remote.dto.QuoteDto
import retrofit2.http.GET
import retrofit2.http.Path

// Api interface to get the quotes from the server
interface QuoteApi {

    @GET("/quotes/{id}")
    suspend fun getQuote(
        @Path("id") id: Int
    ): QuoteDto
}