package br.org.acal.commons.enums

import br.org.acal.application.controller.category.CategorySearchController
import br.org.acal.application.controller.category.CategoryViewController
import br.org.acal.application.controller.customer.CustomerSearchController
import br.org.acal.application.controller.customer.CustomerViewController

const val path = "/br/org/acal/application/controller"
enum class SceneUI(val value: String, val clazz: Class<*>) {

    CUSTOMER_SEARCH("$path/customer/CustomerSearchController.fxml",CustomerSearchController::class.java),
    CUSTOMER("$path/customer/CustomerViewController.fxml", CustomerViewController::class.java),

    CATEGORY_SEARCH("$path/category/CategorySearchController.fxml", CategorySearchController::class.java),
    CATEGORY("$path/category/CategoryViewController.fxml", CategoryViewController::class.java),

    LOGIN("/ui/login-view.fxml",CustomerViewController::class.java),
    DASHBOARD("/ui/dashboard-view.fxml",CustomerViewController::class.java ),
    INVOICE_PAYMENT("/ui/invoice/invoice-payment-view.fxml", CustomerViewController::class.java ) ,
    INVOICE_SEARCH("/ui/invoice/invoice-search-view.fxml", CustomerViewController::class.java),

}