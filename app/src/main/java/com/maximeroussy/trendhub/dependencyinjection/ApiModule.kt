package com.maximeroussy.trendhub.dependencyinjection

import com.maximeroussy.trendhub.BuildConfig
import com.maximeroussy.trendhub.data.api.GithubEndpoint
import com.maximeroussy.trendhub.util.FileManager
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
  @Singleton
  @Provides
  fun okHttp(fileManager: FileManager): OkHttpClient {
    val cacheSize = 10 * 1024 * 1024 // 10 MB
    val cache = Cache(fileManager.getCacheDirectory(), cacheSize.toLong())
    val okHttpClient = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
      val logging = HttpLoggingInterceptor()
      logging.level = Level.BODY

      okHttpClient.addInterceptor(logging)
    }
    return okHttpClient
        .cache(cache)
        .addInterceptor(HttpLoggingInterceptor().setLevel(BODY))
        .build()
  }

  @Provides
  fun githubEndpoint(okHttpClient: OkHttpClient): GithubEndpoint {
    val baseUrl = "https://api.github.com/"
    return Retrofit.Builder()
        .baseUrl(HttpUrl.parse(baseUrl)!!)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(GithubEndpoint::class.java)
  }
}
