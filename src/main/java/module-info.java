module es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian to javafx.fxml;
    exports es.uah.matcomp.mped.proyectofinal.proyectoconwayrauladrian;
}