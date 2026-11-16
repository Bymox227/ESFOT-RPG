package personajes;

public class Mago extends Personaje{
    private int mana;

    public Mago(String nombre, int nivel, int vidaMaxima, int ataque, int defensa, int mana){
        super(nombre, nivel, vidaMaxima, ataque, defensa);
        setMana(mana);
    }

    public int getMana(){
        return mana;
    }

    public void setMana(int mana){
        if(mana < 0){
            throw new IllegalArgumentException("Error. El mana no puede ser negativo");
        }
            this.mana = mana;
    }

    public void lanzarHechizo(Personaje enemigo){
        if(!this.estaVivo()){
            System.out.println(getNombre() + " ha sido derrotado y no puede lanzar hechizos");
            return;
        }
        if(!enemigo.estaVivo()){
            System.out.println("El objetivo " + enemigo.getNombre() + " ya ha sido derrotado");
            return;
        }
        if(mana < 10){
            System.out.println("Aviso. Maná insuficiente (" + mana + "/10 necesarios)");
            return;
        }
        mana -= 10;
        int potenciaHechizo = 20;
        System.out.println(getNombre() + " (Mago) lanza un hechizo arcano a " + enemigo.getNombre() + ". Maná restante: " + mana);
        enemigo.recibirDanio(potenciaHechizo);
    }

    @Override
    public void mostrarInformacion(){
        super.mostrarInformacion();
        System.out.println("Maná: " + mana);
        System.out.println("-----------------------------------");
    }
}
