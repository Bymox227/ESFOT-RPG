package juego;

import personajes.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GestorJuego {
    private List<Personaje> personajes;
    private Map<String, Personaje> registro;
    private Scanner scanner;

    public GestorJuego() {
        this.personajes = new ArrayList<>();
        this.registro = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void registrarPersonaje(Personaje p) {
        String nombreClave = p.getNombre().toLowerCase();
        if (registro.containsKey(nombreClave)) {
            System.out.println("Error. Ya existe un personaje con el nombre: " + p.getNombre());
            return;
        }
        personajes.add(p);
        registro.put(nombreClave, p);
        System.out.println("Personaje " + p.getNombre() + " (" + p.getClass().getSimpleName() + ") creado y registrado exitosamente");
    }

    public void mostrarPersonajes() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados en el sistema");
            return;
        }
        System.out.println("\n===== LISTA DE PERSONAJES =====");
        for (Personaje p : personajes) {
            p.mostrarInformacion();
        }
    }

    public Personaje buscarPorNombre(String nombre) {
        return registro.get(nombre.toLowerCase());
    }

    public void menuBuscarPersonaje() {
        System.out.print("Ingrese el nombre del personaje a buscar: ");
        String nombre = scanner.nextLine();
        Personaje p = buscarPorNombre(nombre);
        if (p != null) {
            System.out.println("\nPersonaje encontrado");
            p.mostrarInformacion();
        } else {
            System.out.println("Error. No se encontró ningún personaje con el nombre: " + nombre);
        }
    }

    public void realizarAtaque() {
        if (personajes.size() < 2) {
            System.out.println("Se necesitan al menos 2 personajes para realizar un ataque");
            return;
        }
        System.out.print("Nombre del atacante: ");
        Personaje atacante = buscarPorNombre(scanner.nextLine());
        if (atacante == null || !atacante.estaVivo()) {
            System.out.println("Error. El atacante no fue encontrado o está derrotado");
            return;
        }

        System.out.print("Nombre del objetivo: ");
        Personaje objetivo = buscarPorNombre(scanner.nextLine());
        if (objetivo == null || !objetivo.estaVivo()) {
            System.out.println("Error. El objetivo no fue encontrado o está derrotado");
            return;
        }

        if (atacante == objetivo) {
            System.out.println("Error. Un personaje no puede atacarse a sí mismo");
            return;
        }

        System.out.println("1. Ataque normal");
        System.out.println("2. Ataque con bonificación");
        System.out.print("Seleccione el tipo de ataque: ");
        String op = scanner.nextLine();
        try {
            if (op.equals("1")) {
                atacante.atacar(objetivo);
            } else if (op.equals("2")) {
                System.out.print("Ingrese la bonificación de ataque: ");
                int bonif = Integer.parseInt(scanner.nextLine());
                atacante.atacar(objetivo, bonif);
            } else {
                System.out.println("Opción inválida");
            }
            System.out.println("Estado actual de " + objetivo.getNombre() + " -> Vida: " + objetivo.getVida() + "/" + objetivo.getVidaMaxima());
        } catch (NumberFormatException e) {
            System.out.println("Error. Entrada numérica inválida");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void utilizarHabilidadEspecial() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados");
            return;
        }
        System.out.print("Nombre del personaje que usará la habilidad: ");
        Personaje p = buscarPorNombre(scanner.nextLine());
        if (p == null || !p.estaVivo()) {
            System.out.println("Error. El personaje no fue encontrado o está derrotado");
            return;
        }

        if (p instanceof Mago) {
            Mago mago = (Mago) p;
            System.out.print("Nombre del enemigo objetivo del hechizo: ");
            Personaje enemigo = buscarPorNombre(scanner.nextLine());
            if (enemigo != null) {
                mago.lanzarHechizo(enemigo);
                System.out.println("Vida restante de " + enemigo.getNombre() + ": " + enemigo.getVida() + "/" + enemigo.getVidaMaxima());
            } else {
                System.out.println("Error. Enemigo no encontrado");
            }
        } else if (p instanceof Arquero) {
            Arquero arquero = (Arquero) p;
            System.out.print("Nombre del enemigo objetivo del disparo: ");
            Personaje enemigo = buscarPorNombre(scanner.nextLine());
            if (enemigo != null) {
                arquero.dispararFlecha(enemigo);
                System.out.println("Vida restante de " + enemigo.getNombre() + ": " + enemigo.getVida() + "/" + enemigo.getVidaMaxima());
            } else {
                System.out.println("Error. Enemigo no encontrado");
            }
        } else if (p instanceof Paladin) {
            Paladin paladin = (Paladin) p;
            System.out.println("1. Curar objetivo");
            System.out.println("2. Activar Escudo Sagrado");
            System.out.print("Seleccione la habilidad del Paladín: ");
            String subOp = scanner.nextLine();
            if (subOp.equals("1")) {
                System.out.print("Nombre del objetivo a curar: ");
                Personaje objetivo = buscarPorNombre(scanner.nextLine());
                if (objetivo != null) {
                    paladin.curar(objetivo);
                } else {
                    System.out.println("Error. Objetivo no encontrado");
                }
            } else if (subOp.equals("2")) {
                paladin.activarEscudo();
            } else {
                System.out.println("Opción inválida.");
            }
        } else if (p instanceof Guerrero) {
            System.out.println("Información: El Guerrero utiliza su fuerza pasivamente en ataques normales");
        } else {
            System.out.println("Información: Este tipo de personaje no cuenta con habilidades especiales activas");
        }
    }

    public void simularCombate() {
        if (personajes.size() < 2) {
            System.out.println("Error. Se necesitan al menos 2 personajes registrados para un combate");
            return;
        }
        System.out.print("Nombre del primer combatiente: ");
        Personaje p1 = buscarPorNombre(scanner.nextLine());
        System.out.print("Nombre del segundo combatiente: ");
        Personaje p2 = buscarPorNombre(scanner.nextLine());

        if (p1 == null || p2 == null) {
            System.out.println("Error. Uno o ambos personajes no fueron encontrados");
            return;
        }
        if (p1 == p2) {
            System.out.println("Error. Un personaje no puede combatir contra sí mismo.");
            return;
        }
        if (!p1.estaVivo() || !p2.estaVivo()) {
            System.out.println("Error. No se puede iniciar combate con personajes derrotados.");
            return;
        }

        System.out.println("\n===== COMBATE RPG: " + p1.getNombre() + " (" + p1.getClass().getSimpleName() + ") vs " + p2.getNombre() + " (" + p2.getClass().getSimpleName() + ") =====");

        int turno = 1;
        Personaje atacante = p1;
        Personaje defensor = p2;

        while (p1.estaVivo() && p2.estaVivo()) {
            System.out.println("\n--- Turno " + turno + " ---");
            System.out.println("Ataca " + atacante.getNombre() + " a " + defensor.getNombre());

            int vidaAntes = defensor.getVida();
            atacante.atacar(defensor);
            int danioEfectivo = vidaAntes - defensor.getVida();

            System.out.println("Daño efectivo infligido: " + danioEfectivo);
            System.out.println("Vida de " + defensor.getNombre() + ": " + defensor.getVida() + " / " + defensor.getVidaMaxima());

            if (!defensor.estaVivo()) {
                break;
            }

            Personaje temp = atacante;
            atacante = defensor;
            defensor = temp;
            turno++;
        }

        Personaje ganador = p1.estaVivo() ? p1 : p2;
        System.out.println("\n--- COMBATE FINALIZADO ---");
        System.out.println("EL GANADOR ES: " + ganador.getNombre() + " (" + ganador.getClass().getSimpleName() + ")");
    }

    public void mostrarPersonajesVivos() {
        long vivosCount = personajes.stream().filter(Personaje::estaVivo).count();
        if (vivosCount == 0) {
            System.out.println("No hay personajes vivos actualmente.");
            return;
        }
        System.out.println("\n===== PERSONAJES VIVOS (" + vivosCount + ") =====");
        for (Personaje p : personajes) {
            if (p.estaVivo()) {
                p.mostrarInformacion();
            }
        }
    }

    public void crearPersonajeDesdeMenu(int tipo) {
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            if (buscarPorNombre(nombre) != null) {
                System.out.println("Error. Ya existe un personaje con ese nombre.");
                return;
            }
                System.out.print("Nivel: ");
                int nivel = Integer.parseInt(scanner.nextLine());
                System.out.print("Vida Máxima: ");
                int vidaMax = Integer.parseInt(scanner.nextLine());
                System.out.print("Ataque: ");
                int ataque = Integer.parseInt(scanner.nextLine());
                System.out.print("Defensa: ");
                int defensa = Integer.parseInt(scanner.nextLine());

            switch (tipo) {
                case 1:
                    System.out.print("Fuerza: ");
                    int fuerza = Integer.parseInt(scanner.nextLine());
                    registrarPersonaje(new Guerrero(nombre, nivel, vidaMax, ataque, defensa, fuerza));
                    break;
                case 2:
                    System.out.print("Maná: ");
                    int mana = Integer.parseInt(scanner.nextLine());
                    registrarPersonaje(new Mago(nombre, nivel, vidaMax, ataque, defensa, mana));
                    break;
                case 3:
                    System.out.print("Flechas: ");
                    int flechas = Integer.parseInt(scanner.nextLine());
                    registrarPersonaje(new Arquero(nombre, nivel, vidaMax, ataque, defensa, flechas));
                    break;
                case 4:
                    System.out.print("Fuerza: ");
                    int fuerzaPal = Integer.parseInt(scanner.nextLine());
                    System.out.print("Energía Sagrada: ");
                    int energia = Integer.parseInt(scanner.nextLine());
                    registrarPersonaje(new Paladin(nombre, nivel, vidaMax, ataque, defensa, fuerzaPal, energia));
                    break;
                default:
                    System.out.println("Tipo desconocido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error. Debe ingresar valores numéricos válidos.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        }
    }
}