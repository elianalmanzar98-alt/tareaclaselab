import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainController {
    @FXML
    private TableView<Producto> tablaProductos;
    @FXML 
    private TableColumn<Producto, String> colNombre, colCategoria;
    @FXML 
    private TableColumn<Producto, Double> colPrecio;
    @FXML 
    private TableColumn<Producto, Integer> colCantidad;
    @FXML 
    private ProgressBar progressBar;
    @FXML 
    private Label labelEstado;

    private ObservableList<Producto> listaProductos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tablaProductos.setItems(listaProductos);
        
        // Carga automática al iniciar la App
        onCargar(); 
    }

@FXML
private void onNuevoProducto() throws IOException {
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Formulariovista.fxml")); 
    Parent root = loader.load();
    
    
    FormulariovistaController formCtrl = loader.getController();
    formCtrl.setListaCompartida(listaProductos);

    Stage stage = new Stage();
    stage.setTitle("Nuevo Producto");
    stage.setScene(new Scene(root));
    stage.show();
}

    @FXML
    private void onCargar() {
        
        Thread hilo = new Thread(() -> {
            try {
                File file = new File("inventario.txt");
                if (!file.exists()) {
                    Platform.runLater(() -> labelEstado.setText("No se encontro inventario.txt"));
                    return;
                }

                List<String> lineas = Files.readAllLines(Paths.get("inventario.txt"));
                Platform.runLater(() -> {
                    listaProductos.clear();
                    progressBar.setProgress(0);
                });
                

                for (int i = 0; i < lineas.size(); i++) {
                    Thread.sleep(200); // Simulación de carga pesada requerida
                    String[] d = lineas.get(i).split(",");
                    Producto p = new Producto(d[0], d[1], Double.parseDouble(d[2]), Integer.parseInt(d[3]));
                    
                    final double progreso = (double) (i + 1) / lineas.size();
                    
                
                    Platform.runLater(() -> {
                        listaProductos.add(p);
                        labelEstado.setText("Cargando: " + p.getNombre());
                        progressBar.setProgress(progreso);
                    });
                }
                Platform.runLater(() -> labelEstado.setText("cargado exitosamente."));
            } catch (Exception e) {
                Platform.runLater(() -> labelEstado.setText("Error al cargar archivo."));
            }
        });
        hilo.setDaemon(true);
        hilo.start();
    }

    @FXML
    private void onGuardar() {
        if (listaProductos.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "No hay productos para guardar.").show();
            return;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter("inventario.txt"))) {
            for (Producto p : listaProductos) pw.println(p.toString());
            labelEstado.setText("Guardado correctamente.");
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Error al escribir en el archivo.").show();
        }
    }

    @FXML
    private void onEliminar() {
        Producto sel = tablaProductos.getSelectionModel().getSelectedItem();
        if (sel != null) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Desea eliminar " + sel.getNombre() + "?");
            if (a.showAndWait().get() == ButtonType.OK) {
                listaProductos.remove(sel);
                labelEstado.setText("Producto eliminado.");
            }
        }
    }

    @FXML
    private void onLimpiarLista() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Vaciar toda la lista?");
        if (a.showAndWait().get() == ButtonType.OK) {
            listaProductos.clear();
            labelEstado.setText("Lista vaciada.");
        }
    }

    @FXML
    private void onAcercaDe() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Acerca de");
        info.setHeaderText("Gestion de Inventario ");
        info.setContentText("Desarrollado para la gestión de tiendas pequenas.");
        info.show();
    }

    @FXML private void onSalir() { Platform.exit(); }
}