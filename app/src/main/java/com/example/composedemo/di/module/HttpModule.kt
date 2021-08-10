package com.example.composedemo.di.module


import com.example.composedemo.http.Api
import com.example.composedemo.http.HttpService
import com.example.composedemo.http.LogInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)//这里使用了 ApplicationComponent，因此 HttpModule 绑定到 Application 的生命周期
class HttpModule {

    @Singleton
    @Provides
    fun provideApiService(): HttpService = Api.default

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient = Api.okHttp

    @Provides
    fun provideLogInterceptor(): Interceptor {
        return LogInterceptor()
    }

//    @Singleton
//    @Provides
//    fun provideRepo(apiService: HttpService): HttpRepo {
//        return HttpRepo(apiService)
//    }



}