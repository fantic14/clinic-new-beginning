module org.savetovaliste {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires java.sql;
    requires mysql.connector.j;

    opens org.savetovaliste to javafx.fxml;
    exports org.savetovaliste;
    opens org.savetovaliste.controller;
    exports org.savetovaliste.controller;
    opens org.savetovaliste.model.dao;
    exports org.savetovaliste.model.dao;
    opens org.savetovaliste.model.entity;
    exports org.savetovaliste.model.entity;
    //opens org.savetovaliste.model;
    //exports org.savetovaliste.model;
    opens org.savetovaliste.view;
    exports org.savetovaliste.view;
    opens org.savetovaliste.controller.otvaranjeProzora;
    exports org.savetovaliste.controller.otvaranjeProzora;
    opens org.savetovaliste.view.prikazi;
    exports org.savetovaliste.view.prikazi;
}