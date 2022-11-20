module com.ui.fmapwm {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.jfoenix;
    requires javafx.media;

    opens com.ui.fmapwm to javafx.fxml;
    exports com.ui.fmapwm;
}