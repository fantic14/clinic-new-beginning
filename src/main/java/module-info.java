module org.savetovaliste {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires static lombok;

    opens org.savetovaliste to javafx.fxml;
    exports org.savetovaliste;
    //exports org.savetovaliste.controller;
    //exports org.savetovaliste.model;
    opens org.savetovaliste.view to javafx.fxml;
    exports org.savetovaliste.view;
}