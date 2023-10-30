module com.emsi.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;


    opens com.emsi.javafx to javafx.fxml;
    exports com.emsi.javafx;
    exports com.emsi.javafx.entitites;
    opens com.emsi.javafx.entitites to javafx.fxml;
}