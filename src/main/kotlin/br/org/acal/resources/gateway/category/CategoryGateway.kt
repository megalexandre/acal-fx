package br.org.acal.resources.gateway.category

import br.org.acal.resources.gateway.category.data.input.CategoryCreateResponse
import br.org.acal.resources.gateway.category.data.input.CategoryPaginateGatewayResponse
import br.org.acal.resources.gateway.category.data.input.CategoryResponse
import br.org.acal.resources.gateway.category.data.output.CategoryCreateRequest
import br.org.acal.resources.gateway.category.data.output.CategoryUpdateRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "category", url = "\${feign.client.config.category.url}/category")
interface CategoryGateway {
    @GetMapping("/paginate")
    fun paginate(): Page<CategoryPaginateGatewayResponse>
    @GetMapping
    fun find(): Collection<CategoryPaginateGatewayResponse>
    @GetMapping("/find")
    fun findByFilter(
        @RequestParam("id") id: String?,
        @RequestParam("name") name: String?,
        @RequestParam("type") type: String?
    ): List<CategoryPaginateGatewayResponse>
    @PostMapping
    fun save(categoryCreateRequest: CategoryCreateRequest): CategoryCreateResponse
    @PutMapping
    fun update(categoryCreateRequest: CategoryUpdateRequest): CategoryCreateResponse
    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): CategoryResponse
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String)


}
