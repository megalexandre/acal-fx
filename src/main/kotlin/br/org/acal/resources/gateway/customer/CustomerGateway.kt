package br.org.acal.resources.gateway.customer

import br.org.acal.resources.gateway.customer.data.CustomerPaginateGatewayResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.Direction
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "customer", url = "\${feign.client.config.customer.url}/customer")
interface CustomerGateway {
    @GetMapping("customer")
    fun find(): Collection<CustomerPaginateGatewayResponse>

    @GetMapping("/paginate")
    fun paginate(
        @RequestParam("id", required = false) id: String?,
        @RequestParam("name", required = false) name: String?,
        @RequestParam("documentNumber", required = false) documentNumber: String?,
        @RequestParam(required = false) offset: Int?,
        @RequestParam(required = false) size: Int?,
        @RequestParam(required = false) field: String?,
        @RequestParam(required = false) direction: Direction?,
    ): Page<CustomerPaginateGatewayResponse>

}
