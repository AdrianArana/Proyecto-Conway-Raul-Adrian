module es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian to javafx.fxml;
    exports es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian;
    exports es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador;
    opens es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.controlador to javafx.fxml;
    exports es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo;
    opens es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian.modelo to javafx.fxml;
}