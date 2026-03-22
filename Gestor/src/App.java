

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainController.fxml"));
            Parent root = loader.load();

            
            Scene scene = new Scene(root, 800, 600);
            
            
            stage.setTitle("Gestion de Inventario ");
            stage.setScene(scene);

            
            stage.setOnCloseRequest(event -> {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmar Salida");
                alerta.setHeaderText(null);
                alerta.setContentText("Esta seguro de que deseas cerrar la aplicacion?");

                
                if (alerta.showAndWait().get() != ButtonType.OK) {
                    event.consume(); 
                }
            });

            
            stage.show();

        } catch (IOException e) {
            System.err.println("Error: No se pudo cargar MainController.fxml");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
        launch(args);
    }
}