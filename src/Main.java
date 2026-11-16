import juego.GestorJuego;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorJuego gestor = new GestorJuego();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n====================================");
            System.out.println("       ESFOT RPG - JAVA POO         ");
            System.out.println("====================================");
            System.out.println("1. Crear Guerrero");
            System.out.println("2. Crear Mago");
            System.out.println("3. Crear Arquero");
            System.out.println("4. Crear Paladín");
            System.out.println("5. Mostrar personajes");
            System.out.println("6. Buscar personaje por nombre");
            System.out.println("7. Atacar a otro personaje");
            System.out.println("8. Utilizar habilidad especial");
            System.out.println("9. Simular combate por turnos");
            System.out.println("10. Mostrar personajes vivos");
            System.out.println("11. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                String entrada = sc.nextLine();
                opcion = Integer.parseInt(entrada);

                switch (opcion) {
                    case 1:
                        gestor.crearPersonajeDesdeMenu(1);
                        break;
                    case 2:
                        gestor.crearPersonajeDesdeMenu(2);
                        break;
                    case 3:
                        gestor.crearPersonajeDesdeMenu(3);
                        break;
                    case 4:
                        gestor.crearPersonajeDesdeMenu(4);
                        break;
                    case 5:
                        gestor.mostrarPersonajes();
                        break;
                    case 6:
                        gestor.menuBuscarPersonaje();
                        break;
                    case 7:
                        gestor.realizarAtaque();
                        break;
                    case 8:
                        gestor.utilizarHabilidadEspecial();
                        break;
                    case 9:
                        gestor.simularCombate();
                        break;
                    case 10:
                        gestor.mostrarPersonajesVivos();
                        break;
                    case 11:
                        System.out.println("¡Gracias por jugar ESFOT RPG! Saliendo...");
                        break;
                    default:
                        System.out.println("Error. Opción inválida. Elija entre 1 y 11.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error. Entrada inválida. Ingrese un número entero.");
            }
        } while (opcion != 11);

        sc.close();
    }
}