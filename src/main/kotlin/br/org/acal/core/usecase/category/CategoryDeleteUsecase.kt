package br.org.acal.core.usecase.category

import br.org.acal.core.datasource.CategoryDataSource
import br.org.acal.core.entity.Category
import br.org.acal.core.usecase.Usecase
import org.springframework.stereotype.Service


@Service
class CategoryDeleteUsecase(
    private val dataSource: CategoryDataSource
) : Usecase<String, Unit> {
    override fun execute(input: String) = dataSource.delete(input)

}
