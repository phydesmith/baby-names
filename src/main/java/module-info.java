module baby.name {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports io.javasmithy to javafx.base, javafx.fxml, javafx.controls, javafx.graphics;
    exports io.javasmithy.controller to javafx.base, javafx.fxml, javafx.controls, javafx.graphics;
    exports io.javasmithy.data to javafx.base, javafx.fxml, javafx.controls, javafx.graphics;

    opens io.javasmithy to javafx.base, javafx.fxml, javafx.controls, javafx.graphics;
    opens io.javasmithy.controller to javafx.base, javafx.fxml, javafx.controls, javafx.graphics;
    opens io.javasmithy.data to javafx.base, javafx.fxml, javafx.controls, javafx.graphics;

}