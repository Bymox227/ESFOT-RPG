package personajes;

public class Arquero extends Personaje{
    private int flechas;

    public Arquero(String nombre, int nivel, int vidaMaxima, int ataque, int defensa, int flechas){
        super(nombre, nivel, vidaMaxima, ataque, defensa);
        setFlechas(flechas);
    }

    public int getFlechas(){
        return flechas;
    }

    public void setFlechas(int flechas){
        if(flechas < 0){
            this.flechas = 0;
        }else {
            this.flechas = flechas;
        }
    }

    public void dispararFlecha(Personaje enemigo) {
        if (!this.estaVivo()) {
            System.out.println(getNombre() + " ha sido derrotado y no puede disparar");
            return;
        }
        if (!enemigo.estaVivo()) {
            System.out.println("El objetivo " + enemigo.getNombre() + " ha sido derrotado");
            return;
        }
        if(this.flechas <= 0){
            System.out.println("Aviso. No quedan flechas en el carcaj");
            return;
        }
        this.flechas --;
        int potenciaDisparo = getAtaque() + 5;
        System.out.println(getNombre() + " (Arquero) dispara una flecha a " + enemigo.getNombre() + ". Flechas restantes: " + flechas );
        enemigo.recibirDanio(potenciaDisparo);
    }

    @Override
    public void mostrarInformacion(){
        super.mostrarInformacion();
        System.out.println("Flechas: " + flechas);
        System.out.println("-----------------------------------");
    }
}
