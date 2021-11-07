package com.fluid.smartcrops

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface APIService {
    @Headers("Content-Type: application/json")
    @POST("score")
    suspend fun predictCrops(
        @Body data: InputParams
    ): Response<OutputParam>
}