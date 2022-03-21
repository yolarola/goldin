package com.example.goldin

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    @GET("/products/")
    suspend fun getPosts(): Response<ResponseBody>

    @POST("/products/")
    suspend fun createEmployee(@Body requestBody: RequestBody): Response<ResponseBody>

    @PUT("/products/\${id}/")
    suspend fun updateEmployee(@Path("id") id: String, @Body requestBody: RequestBody): Response<ResponseBody>



    @DELETE("/products/\${id}/")
    suspend fun deleteEmployee(@Path("id") id: String): Response<ResponseBody>





}