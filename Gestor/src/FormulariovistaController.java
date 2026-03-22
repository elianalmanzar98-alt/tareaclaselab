import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormulariovistaController {
    @FXML private TextField txtNombre, txtCategoria, txtPrecio, txtCantidad;
    private ObservableList<Producto> listaCompartida;

    public void setListaCompartida(ObservableList<Producto> lista) {
        this.listaCompartida = lista;
    }

    @FXML
    private void guardar() {
        
        if (txtNombre.getText().trim().isEmpty() || txtCategoria.getText().trim().isEmpty() || 
            txtPrecio.getText().isEmpty() || txtCantidad.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Por favor llene todo lo solicitado").show();
            return;
        }

        try {
        
            double precio = Double.parseDouble(txtPrecio.getText());
            int cantidad = Integer.parseInt(txtCantidad.getText());

            Producto p = new Producto(txtNombre.getText(), txtCategoria.getText(), precio, cantidad);
            listaCompartida.add(p);
            
            
            new Alert(Alert.AlertType.INFORMATION, "Producto guardado correctamente").showAndWait();
            cerrar();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Precio debe ser decimal y la cantidad Entero").show();
        }
    }

    @FXML
    private void cerrar() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
}