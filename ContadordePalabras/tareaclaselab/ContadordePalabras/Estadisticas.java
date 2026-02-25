import java.util.Map;

public class Estadisticas{

    private Map<String, Integer> resultados;
    private int total = 0;

    public synchronized void agregarResultados(String archivo, int palabras){
        resultados.put(archivo, palabras);
        total += palabras;
    }

    public Map<String, Integer> getResultados(){
        return resultados;
    }

    public int getTotal(){
        return total;
    }


}