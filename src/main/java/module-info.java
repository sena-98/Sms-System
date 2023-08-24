module CellCelly.CellCelly {
    requires javafx.controls;
    requires javafx.fxml;
    requires unirest.java;
    requires org.json;

    opens tr.com.cellcelly to javafx.fxml;
    exports tr.com.cellcelly;
}
