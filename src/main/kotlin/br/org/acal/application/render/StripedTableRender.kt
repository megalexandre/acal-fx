package br.org.acal.application.render

import br.org.acal.commons.enums.ColorStatus
import javafx.scene.control.TableRow
import javafx.scene.control.TableView
import javafx.util.Callback

class StripedTableRender<T>: Callback<TableView<T>, TableRow<T>> {
    override fun call(param: TableView<T>): TableRow<T> =
        object : TableRow<T>() {
            override fun updateItem(item: T?, empty: Boolean) {
                super.updateItem(item, empty)

                val borderColor = ColorStatus.NEUTRAL.dark
                style = if (isEmpty || item == null) {
                    ""
                } else {
                    val index = index % 2
                    when(index == 0){
                        true ->
                            "-fx-border-width: 0px;" +
                            "-fx-border-color: transparent transparent $borderColor transparent; " +
                            "-fx-border-width: 1;" +
                            "-fx-background-color: #f2f2f2;" +
                            "-fx-text-fill: #ffffff;"
                        false ->
                            "-fx-border-width: 0px;" +
                            "-fx-border-color: transparent transparent $borderColor transparent; " +
                            "-fx-border-width: 1;" +
                            "-fx-background-color: #ffffff;" +
                            "-fx-text-fill: #f2f2f2;"
                    }
                }




            }
        }
}
