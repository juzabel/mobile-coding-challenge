package net.juzabel.data.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import net.juzabel.data.BuildConfig
import net.juzabel.data.local.PostureDatabase
import net.juzabel.data.remote.Api
import net.juzabel.domain.feature.posturelist.model.Posture
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {
    single { provideApi(get()) }
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), get()) }
    single { createMoshiConverter() }
    single { provideDatabase(androidContext())}
    single { get<PostureDatabase>().postureDao()}
    single { get<PostureDatabase>().postureDetailDao()}
}

    //Remote

    fun provideApi(retrofit: Retrofit) = retrofit.create(Api::class.java)

    private fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory).build()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(logging).build()
    }

    private fun createMoshiConverter(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return MoshiConverterFactory.create(moshi)
    }

    //Local

    fun provideDatabase(context: Context) : PostureDatabase {
        return Room.databaseBuilder(
            context,
            PostureDatabase::class.java, "posture_database"
        ).build()
    }