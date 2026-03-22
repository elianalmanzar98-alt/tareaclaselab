

import javafx.beans.property.*;

public class Producto {
    private final StringProperty nombre;
    private final StringProperty categoria;
    private final DoubleProperty precio;
    private final IntegerProperty cantidad;

    public Producto(String nombre, String categoria, double precio, int cantidad) {
        this.nombre = new SimpleStringProperty(nombre);
        this.categoria = new SimpleStringProperty(categoria);
        this.precio = new SimpleDoubleProperty(precio);
        this.cantidad = new SimpleIntegerProperty(cantidad);
    }

    
    public String getNombre() { 
        return nombre.get();
    }
    public String getCategoria() { 
    
        return categoria.get(); 
    }
    public double getPrecio() { 
        return precio.get(); 
    }
    public int getCantidad() { 
        return cantidad.get(); 
    }

    @Override
    public String toString() {
        return getNombre() + "," + getCategoria() + "," + getPrecio() + "," + getCantidad();
    }
}