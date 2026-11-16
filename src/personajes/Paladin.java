package personajes;

public class Paladin extends Guerrero{
    private int energiaSagrada;
    private boolean escudoActivo;

    public Paladin(String nombre, int nivel, int vidaMaxima, int ataque, int defensa, int fuerza, int energiaSagrada){
        super(nombre, nivel, vidaMaxima, ataque, defensa, fuerza);
        setEnergiaSagrada(energiaSagrada);
        this.escudoActivo = false;
    }

    public int getEnergiaSagrada() {
        return energiaSagrada;
    }

    public void setEnergiaSagrada(int energiaSagrada){
        if(energiaSagrada < 0) {
            throw new IllegalArgumentException("Error. La energia sagrada no puede ser negativa");
        }
            this.energiaSagrada = energiaSagrada;
    }

    public boolean isEscudoActivo() {
        return escudoActivo;
    }
    @Override
    public void recibirDanio(int cantidad) {
        int cantidadEfectiva = cantidad;
        if (escudoActivo) {
            cantidadEfectiva = Math.max(0, cantidad - 5);
            escudoActivo = false;
            System.out.println("El escudo sagrado de " + getNombre() + " absorbió 5 puntos de daño");
        }
        super.recibirDanio(cantidadEfectiva);
    }

    public void activarEscudo() {
        if (!this.estaVivo()) {
            System.out.println(getNombre() + " ha sido derrotado y no puede activar habilidades");
            return;
        }
        if (energiaSagrada < 5) {
            System.out.println("Aviso. La energía sagrada es insuficiente para activar el escudo (" + energiaSagrada + "/5)");
            return;
        }
        if (escudoActivo) {
            System.out.println("El escudo ya se encuentra activo");
            return;
        }
        energiaSagrada -= 5;
        escudoActivo = true;
        System.out.println(getNombre() + " (Paladín) ha activado su Escudo Sagrado");
    }

    public void curar(Personaje objetivo) {
        if (!this.estaVivo()) {
            System.out.println(getNombre() + " ha sido derrotado y no puede curar");
            return;
        }
        if (!objetivo.estaVivo()) {
            System.out.println("Error. No se puede curar a un personaje derrotado");
            return;
        }
        if (energiaSagrada < 10) {
            System.out.println("Aviso. La energía sagrada es insuficiente para curar (" + energiaSagrada + "/10)");
            return;
        }
        energiaSagrada -= 10;
        int vidaAnterior = objetivo.getVida();
        int nuevaVida = Math.min(objetivo.getVidaMaxima(), vidaAnterior + 20);
        objetivo.setVida(nuevaVida);
        int curadoEfectivo = objetivo.getVida() - vidaAnterior;
        System.out.println(getNombre() + " (Paladín) utiliza Luz Sagrada para curar a " + objetivo.getNombre() + " en " + curadoEfectivo + " HP.");
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Energía Sagrada: " + energiaSagrada);
        System.out.println("Escudo Activo: " + (escudoActivo ? "Sí" : "No"));
        System.out.println("-----------------------------------");
    }
}
