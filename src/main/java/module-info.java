module org.savetovaliste.savetovaliste {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.savetovaliste.savetovaliste to javafx.fxml;
    exports org.savetovaliste.savetovaliste;
}