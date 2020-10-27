package com.sh.techtest.hospitals.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

const val NETWORK_TIMEOUT = 20_000L
const val BASE_URL = "https://draysontechnologies.github.io/shtest.github.io/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/** Create an HttpClient so we can view the network logs and set timeouts.*/
val httpClient: OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
 /*   .addInterceptor(run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    })*/
    .readTimeout(NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
    .connectTimeout(NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
    .writeTimeout(NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
    .build()


/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(httpClient)
    .build()

/**
 * Interface that defines the method(s) for interacting with the specified API
 * */
interface HospitalApiService {

    @GET("hospitals.json")
    suspend fun getHospitals(): List<NetworkHospital>

}

/** Create a globally accessible object using retrofit and [HospitalApiService] for method(s) that
 * can communicate with the API*/
object HospitalApi {

    /** thread-safe single initialisation of service.*/
    val service: HospitalApiService by lazy { retrofit.create(HospitalApiService::class.java) }

}