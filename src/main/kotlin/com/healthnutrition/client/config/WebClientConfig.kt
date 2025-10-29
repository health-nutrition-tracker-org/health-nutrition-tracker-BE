package com.healthnutrition.client.config

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit

@Configuration
class WebClientConfig(
    @Value("\${webclient.connection-timeout-millis}") private val connectionTimeoutMillis: Int,
    @Value("\${webclient.connection-read-timeout-millis}") private val connectionReadTimeoutMillis: Int
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Bean(name = ["dataGoWebClient"])
    fun dataGoWebClientConfig(): WebClient {
        return WebClient.builder()
            .filter(logRequest())
            .clientConnector(createConnector())
            .build()
    }

    private fun createConnector(): ReactorClientHttpConnector {
        val httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeoutMillis)
            .responseTimeout(Duration.ofMillis(connectionReadTimeoutMillis.toLong()))
            .doOnConnected { connection -> connection.addHandlerFirst(ReadTimeoutHandler(connectionReadTimeoutMillis.toLong(), TimeUnit.MILLISECONDS)) }
        return ReactorClientHttpConnector(httpClient)
    }

    private fun logRequest(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofRequestProcessor { clientRequest ->
            log.info("Request: ${clientRequest.method()} ${clientRequest.url()}")
            Mono.just(clientRequest)
        }
    }
}
