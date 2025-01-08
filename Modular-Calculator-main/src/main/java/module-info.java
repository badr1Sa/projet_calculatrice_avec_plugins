module oussama.it.modular_calculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens oussama.it.modular_calculator to javafx.fxml;
    exports oussama.it.modular_calculator;
}