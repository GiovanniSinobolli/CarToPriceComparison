module com.example.pricetohpGraph {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.pricetohpGraph to javafx.fxml;
    exports com.example.pricetohpGraph;
}