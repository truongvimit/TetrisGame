package com.tvdztech.tetris.di

import com.tvdztech.tetris.BuildConfig
import com.tvdztech.tetris.data.repository.IPLocationApi
import com.tvdztech.tetris.data.repository.RemoteConfigRepoImpl
import com.tvdztech.tetris.data.repository.IRepository
import com.tvdztech.tetris.data.repository.RemoteConfigRepo
import com.tvdztech.tetris.domain.interactors.GetIPLocationDetailUseCase
import com.tvdztech.tetris.domain.interactors.GetRemoteConfigUseCase
import com.tvdztech.tetris.ui.main.MainViewModel
import com.truongvim.snakegame.domain.repository.RepositoryImpl
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    single { MainViewModel(get(), get()) }
}

val repositoryModule = module {
    single<RemoteConfigRepo> { RemoteConfigRepoImpl() }
    single<IRepository> { RepositoryImpl(get(), get()) }
}

val useCaseModule = module {
    factory { GetRemoteConfigUseCase(get(), get()) }
    factory { GetIPLocationDetailUseCase(get(), get()) }
}

val networkModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideIPLocationDetail(get()) }
    single { provideRetrofit(get()) }
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
}

fun provideIPLocationDetail(retrofit: Retrofit): IPLocationApi = retrofit.create(IPLocationApi::class.java)
