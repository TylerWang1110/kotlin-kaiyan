package com.tyler.app.kotlinkaiyan.net

import com.tyler.app.kotlinkaiyan.BaseApp
import com.tyler.app.kotlinkaiyan.net.api.ApiService
import com.tyler.app.kotlinkaiyan.net.api.UrlConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/5  14:22.
 * @描述  ${Retrofit管理类}.
 */
object RetrofitManager {
    //超时时间, 秒
    val TIME_OUT = 20L

    val service: ApiService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        getRetrofit().create(ApiService::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(UrlConfig.BASE_URL)
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {

        //设置 请求的缓存的大小跟位置
        val cacheFile = File(BaseApp.context.cacheDir, "cache")
        val cache = Cache(cacheFile, 1024 * 1024 * 50) //50Mb 缓存的大小
        return OkHttpClient.Builder()
            //可设置公共参数
            .addInterceptor(addQueryParameterInterceptor())
            .addInterceptor(LogInterceptor())//日志
            .cache(cache)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    /**
     * 设置公共参数
     */
    private fun addQueryParameterInterceptor(): Interceptor? {
        return Interceptor { chain ->
            val request = chain.request()
            val url = request.url().newBuilder()
                .addQueryParameter("udid", "d2807c895f0348a180148c9dfa6f2feeac0781b5")
                .build()
            chain.proceed(request.newBuilder().url(url).build())
        }
    }
}