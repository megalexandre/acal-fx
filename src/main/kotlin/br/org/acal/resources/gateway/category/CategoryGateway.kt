package br.org.acal.resources.gateway.category

import br.org.acal.resources.gateway.category.data.input.CategoryCreateResponse
import br.org.acal.resources.gateway.category.data.input.CategoryPaginateGatewayResponse
import br.org.acal.resources.gateway.category.data.input.CategoryResponse
import br.org.acal.resources.gateway.category.data.output.CategoryCreateRequest
import br.org.acal.resources.gateway.category.data.output.CategoryUpdateRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping

@FeignClient(name = "category", url = "\${feign.client.config.category.url}/category")
interface CategoryGateway {
    @GetMapping("/paginate")
    fun paginate(): Page<CategoryPaginateGatewayResponse>
    @GetMapping
    fun find(): Collection<CategoryPaginateGatewayResponse>
    @PostMapping
    fun save(categoryCreateRequest: CategoryCreateRequest): CategoryCreateResponse
    @PutMapping
    fun update(categoryCreateRequest: CategoryUpdateRequest): CategoryCreateResponse
    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): CategoryResponse
}
