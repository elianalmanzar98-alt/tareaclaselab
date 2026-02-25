import java.io.FileWriter;
import java.util.Map;



public class Main {
    public static void main(String[] args) {
        
        long inicio = System.currentTimeMillis();

        Estadisticas estadisticas = new Estadisticas();

        ContadorPalabras hilo1 = new ContadorPalabras("texto1.txt", estadisticas);
        ContadorPalabras hilo2 = new ContadorPalabras("texto2.txt", estadisticas);
        ContadorPalabras hilo3 = new ContadorPalabras("texto3.txt", estadisticas);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        hilo1.join();
        hilo2.join();
        hilo3.join();

        long fin = System.currentTimeMillis();

        long tiempo = fin - inicio;

        generarReporte(estadisticas, tiempo);

    }

    public static void generarReporte(Estadisticas estadisticas, long tiempo){

        try (FileWriter writer = new FileWriter("estadisticas.txt")) {

        writer.write("""
                     ========================================
                     REPORTE DE PROCESAMIENTO DE ARCHIVOS
                     ========================================
                     """);

                     for (Map.Entry<String, Integer> entry : estadisticas.getResultados().entrySet()){
                        writer.write("Archivo: " + entry.getKey() + " - Cantidad de palabras: " + entry.getValue() + "\n");
                     }
                     writer.write("Total de palabras: " + estadisticas.getTotal() + "\n");
                     writer.write("Tiempo total de procesamiento: " + tiempo + " ms\n");

                     writer.close();

                     System.out.println("Reporte creado");
                    }catch (Exception e){
                        System.out.println("Error al crear reporte");
                    }
        }
    }

