
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<incidencia> listaIncidencias = new ArrayList<>();
        int opcion;
        int contadorId = 1;

        do {

            System.out.println("\n     SISTEMA DE INCIDENCIAS   ");
            System.out.println("1. Registrar incidencia");
            System.out.println("2. Listar incidencias");
            System.out.println("3. Buscar incidencias por palabra clave");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion:  ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {

                case 1:
                    try {

                        System.out.print("Descripcion: ");
                        String descripcion = scanner.nextLine();

                        System.out.print("Fecha (dd/MM/yyyy): ");
                        String fecha = scanner.nextLine();

                        System.out.print("Nivel de Prioridad (ALTA, MEDIA, BAJA): ");
                        String prioridad = scanner.nextLine();

                        incidencia nueva = new incidencia(
                                contadorId,
                                descripcion,
                                fecha,
                                prioridad
                        );

                        listaIncidencias.add(nueva);
                        contadorId++;

                        System.out.println("Incidencia registrada correctamente.");

                    } catch (DescripcionInvalidaException |
                             PrioridadInvalidaException |
                             FechaInvalidaException e) {

                        System.out.println("Error: " + e.getMessage());

                    } catch (NumberFormatException e) {
                        System.out.println("Entrada invalida.");
                    }
                    break;

                case 2:
                    if (listaIncidencias.isEmpty()) {
                        System.out.println("No hay incidencias registradas.");
                    } else {
                        for (incidencia incidencia : listaIncidencias) {
                            System.out.println(incidencia);
                        }
                    }
                    break;

                case 3:
                    System.out.print("digite la palabra clave: ");
                    String palabraClave = scanner.nextLine().trim().toLowerCase();

                    boolean encontrada = false;

                    for (incidencia incidencia : listaIncidencias) {
                        if (incidencia.getDescripcion()
                                .toLowerCase()
                                .contains(palabraClave)) {

                            System.out.println(incidencia);
                            encontrada = true;
                        }
                    }

                    if (!encontrada) {
                        System.out.println("No se encontraron coincidencias.");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo  ");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}
