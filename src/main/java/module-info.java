module es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.rmi;
    requires com.google.gson;
    requires commons.logging;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;

    opens es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian to javafx.fxml;
    exports es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian;
    exports es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;
    opens es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador to javafx.fxml;
    exports es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo;
    opens es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo to javafx.fxml;
    exports es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras;
    opens es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.estructuras to javafx.fxml;
    exports es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos;
    opens es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.individuos to javafx.fxml;
}