package br.org.acal.infrastructure.feing

import feign.RequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfig {
    @Bean
    fun contentTypeInterceptor(): RequestInterceptor =
        RequestInterceptor { template ->
            template.header("Content-Type", "application/json")
            template
        }
}