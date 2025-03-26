package ishan.tutorial.dietplannerapp

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("calculate_macros")
    suspend fun calculateMacros(@Body macroRequest: MacroRequest): Response<MacroResponse>
}
