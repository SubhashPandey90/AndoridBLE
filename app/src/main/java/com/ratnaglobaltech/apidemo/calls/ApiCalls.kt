package com.ratnaglobaltech.apidemo.calls

import com.ratnaglobaltech.models.DemoModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

const val BASE_URL = "http://34.232.69.31:8080/notifications-service/api/v1/"

interface APIService {
    @POST("retrieve-notifications")
    suspend fun getTodos(@Body params: HashMap<String, String>): DemoModel


    companion object {


        var apiService: APIService? = null
        fun getInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}