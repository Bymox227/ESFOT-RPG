package personajes;

public class Guerrero extends Personaje {
    private int fuerza;

    public Guerrero(String nombre, int nivel, int vidaMaxima, int ataque, int defensa, int fuerza){
        super(nombre, nivel, vidaMaxima, ataque, defensa);
        setFuerza(fuerza);
    }

    public int getFuerza(){
        return fuerza;
    }

    public void setFuerza(int fuerza){
        if(fuerza < 0){
            throw new IllegalArgumentException("Error. La fuerza no puede ser negativa");
        }
            this.fuerza = fuerza;
    }

    @Override
    public void atacar(Personaje enemigo){
        if(!this.estaVivo()){
            System.out.println(getNombre() + " ha sido derrotado y no puede atacar");
            return;
        }
        if(!enemigo.estaVivo()){
            System.out.println("El objetivo " + enemigo.getNombre() + " ya ha sido derrotado");
            return;
        }
        int potenciaGuerrero = getAtaque() + fuerza;
        System.out.println(getNombre() + " (Guerrero) ataca con espada a " + enemigo.getNombre() + " sumando su fuerza (" + fuerza + "). Potencia: " + potenciaGuerrero);
        enemigo.recibirDanio(potenciaGuerrero);
    }

    @Override
    public void mostrarInformacion(){
        super.mostrarInformacion();
        System.out.println("Fuerza: " + fuerza);
        System.out.println("-----------------------------------");
    }
}
