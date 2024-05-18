package br.org.acal.resources.gateway.customer

import br.org.acal.resources.gateway.customer.data.CustomerPaginateGatewayResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "customer", url = "\${feign.client.config.customer.url}")
interface CustomerGateway {
    @GetMapping("customer")
    fun find(): Collection<CustomerPaginateGatewayResponse>

}
