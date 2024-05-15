package br.org.acal

enum class SceneUI(val value: String) {

    LOGIN("/br/org/acal/login-view.fxml"),
    DASHBOARD("/ui/dashboard-view.fxml"),

    INVOICE_PAYMENT("/ui/invoice/invoice-payment-view.fxml"),
    INVOICE_SEARCH("/ui/invoice/invoice-search-view.fxml"),
    CATEGORY("/ui/category/category-view.fxml"),
    CATEGORY_SEARCH("/ui/category/category-search-view.fxml"),

    CUSTOMER("/ui/customer/customer-view.fxml"),
    CUSTOMER_SEARCH("/ui/customer/customer-search-view.fxml"),


}