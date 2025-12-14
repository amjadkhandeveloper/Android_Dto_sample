package demo.amjadkhan.dtosample.data.remote

import demo.amjadkhan.dtosample.data.remote.api.QuoteApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://dummyjson.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // call api interface to get the quote using the retrofit object
    val api: QuoteApi by lazy {
        retrofit.create(QuoteApi::class.java)
    }

    // Future APIs 👇
    // val userApi: UserApi by lazy {
    //     retrofit.create(UserApi::class.java)
    // }
}