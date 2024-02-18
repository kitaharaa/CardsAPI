package com.kitahara.cardsapitest.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.PortUnreachableException
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    private val attempts = 4
    private val delay = 5L
    private val connectionTime = 30000L

    @Provides
    @Singleton
    fun provideKtorClient() = HttpClient(Android) {
        // Logging
        install(Logging) {
            level = LogLevel.ALL

            logger = object : io.ktor.client.plugins.logging.Logger {
                override fun log(message: String) {
                    Log.d("Ktor", message)
                }
            }
        }

        // JSON
        install(ContentNegotiation) {
            json()
        }

        install(HttpTimeout) {
            connectTimeoutMillis =connectionTime
            requestTimeoutMillis = connectionTime
        }

        install(HttpRequestRetry) {
            maxRetries = attempts
            retryOnException(attempts)
            retryOnServerErrors(attempts)
            retryIf { _, response ->
                response.status.isSuccess().not()
            }
            retryOnExceptionIf { _, cause ->
                (cause is UnknownHostException) or
                        (cause is SocketTimeoutException) or
                        (cause is ConnectException) or
                        (cause is NoRouteToHostException) or
                        (cause is PortUnreachableException) or
                        (cause is ProtocolException) or
                        (cause is SocketException) or
                        (cause is Exception)
            }

            delayMillis { retry ->
                retry * TimeUnit.SECONDS.toMillis(delay)
            }
        }

        // Apply to all requests
        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }
}