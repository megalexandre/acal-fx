package br.org.acal.core.usecase.category

import br.org.acal.core.datasource.CategoryDataSource
import br.org.acal.core.entity.Category
import br.org.acal.core.usecase.Usecase
import org.springframework.stereotype.Service


@Service
class CategoryFindByIdUsecase(
    private val dataSource: CategoryDataSource
) : Usecase<String, Category?> {
    override fun execute(input: String): Category? = dataSource.findById(input)

}
