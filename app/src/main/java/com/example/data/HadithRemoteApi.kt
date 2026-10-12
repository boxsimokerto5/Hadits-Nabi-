package com.example.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

@JsonClass(generateAdapter = true)
data class GadingSingleHadithResponse(
    @Json(name = "code") val code: Int?,
    @Json(name = "message") val message: String?,
    @Json(name = "data") val data: GadingSingleData?
)

@JsonClass(generateAdapter = true)
data class GadingSingleData(
    @Json(name = "name") val name: String?,
    @Json(name = "id") val id: String?,
    @Json(name = "available") val available: Int?,
    @Json(name = "contents") val contents: GadingHadithContent?
)

@JsonClass(generateAdapter = true)
data class GadingRangeHadithResponse(
    @Json(name = "code") val code: Int?,
    @Json(name = "message") val message: String?,
    @Json(name = "data") val data: GadingRangeData?
)

@JsonClass(generateAdapter = true)
data class GadingRangeData(
    @Json(name = "name") val name: String?,
    @Json(name = "id") val id: String?,
    @Json(name = "available") val available: Int?,
    @Json(name = "hadiths") val hadiths: List<GadingHadithContent>?
)

@JsonClass(generateAdapter = true)
data class GadingHadithContent(
    @Json(name = "number") val number: Int?,
    @Json(name = "arab") val arab: String?,
    @Json(name = "id") val translationId: String?
)

interface HadithRemoteApi {
    @GET("books/{bookSlug}/{number}")
    suspend fun getHadithByNumber(
        @Path("bookSlug") bookSlug: String,
        @Path("number") number: Int
    ): GadingSingleHadithResponse

    @GET("books/{bookSlug}")
    suspend fun getHadithRange(
        @Path("bookSlug") bookSlug: String,
        @Query("range") range: String // e.g. "1-20"
    ): GadingRangeHadithResponse

    companion object {
        private const val BASE_URL = "https://api.hadith.gading.dev/"

        fun create(): HadithRemoteApi {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(12, TimeUnit.SECONDS)
                .readTimeout(12, TimeUnit.SECONDS)
                .build()

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(HadithRemoteApi::class.java)
        }
    }
}
