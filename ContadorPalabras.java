import java.io.BufferedReader;
import java.io.FileReader;

public class ContadorPalabras implements Runnable{

    private final String archivo;
    private final Estadisticas estadisticas;

    public ContadorPalabras(String archivo, Estadisticas estadisticas){

        this.archivo = archivo;
        this.estadisticas = estadisticas;
        
    }

    @Override
    public void run(){

        int contador = 0;
        
        String nombreArchivo = archivo;
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split(" ");
                contador += palabras.length;
                
            }

            estadisticas.agregarResultados(nombreArchivo, contador);

        } catch (Exception e){

            System.out.println("Error al leer archivo " + nombreArchivo);
        }
    }

    public void start() {
        
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }

    public void join() {
        
        throw new UnsupportedOperationException("Unimplemented method 'join'");
    }
}
