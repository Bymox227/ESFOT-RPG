package personajes;

public class Personaje {
    private String nombre;
    private int nivel;
    private int vida;
    private int vidaMaxima;
    private int ataque;
    private int defensa;

    public Personaje(String nombre, int nivel, int vidaMaxima, int ataque, int defensa){
        setNombre(nombre);
        setNivel(nivel);
        setVidaMaxima(vidaMaxima);
        this.vida = this.vidaMaxima;
        setAtaque(ataque);
        setDefensa(defensa);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("Error. El nombre no puede estar vacio");
        }
            this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        if(nivel <= 0){
            throw new IllegalArgumentException("Error. El nivel debe ser mayor a 0");
        }
            this.nivel = nivel;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if(vida < 0){
            this.vida = 0;
        } else if (vida > vidaMaxima) {
            this.vida = vidaMaxima;
        }else {
            this.vida = vida;
        }
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        if(vidaMaxima <= 0){
            throw new IllegalArgumentException("Error. La vida maxima debe ser mayor a 0");
        }
        this.vidaMaxima = vidaMaxima;
        if(this.vida > this.vidaMaxima){
            this.vida = this.vidaMaxima;
        }
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        if(ataque < 0){
            throw new IllegalArgumentException("Error. El ataque no puede ser negativo");
        }
            this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        if(defensa < 0){
            throw new IllegalArgumentException("Error. La defensa no puede ser negativa");
        }
            this.defensa = defensa;
    }

    public boolean estaVivo(){
        return vida > 0;
    }

    public void recibirDanio(int cantidad){
        int danioReal = Math.max(1, cantidad - defensa);
        vida = Math.max(0, vida - danioReal);
    }

    public void atacar(Personaje enemigo){
        if(!this.estaVivo()){
            System.out.println(nombre + " ha sido derrotado y no puede atacar");
            return;
        }
        if(!enemigo.estaVivo()){
            System.out.println("El objetivo " + enemigo.getNombre() + " ya ha sido derrotado");
            return;
        }
        System.out.println(nombre + " ataca normalmente a " + enemigo.getNombre() + " con potencia " + ataque);
        enemigo.recibirDanio(ataque);

    }

    public void atacar(Personaje enemigo, int bonificacion){
        if(!this.estaVivo()){
            System.out.println(nombre + " ha sido derrotado y no puede atacar");
            return;
        }
        if(!enemigo.estaVivo()){
            System.out.println("El objetivo " + enemigo.getNombre() + " ya ha sido derrotado");
            return;
        }
        if(bonificacion < 0){
            throw new IllegalArgumentException("Error. La bonificacion no puede ser negativa");
        }
        int potenciaTotal = ataque + bonificacion;
        System.out.println(nombre + " realiza un ataque con bonificacion (" + bonificacion + ") a " + enemigo.getNombre() + " (Potencia total: " + potenciaTotal + ")");
        enemigo.recibirDanio(potenciaTotal);
    }

    public void mostrarInformacion(){
        System.out.println("-----------------------------------");
        System.out.println("Nombre: " + nombre );
        System.out.println("Nivel " + nivel);
        System.out.println("Vida: " + vida + "/" + vidaMaxima);
        System.out.println("Ataque: " + ataque);
        System.out.println("Defensa: " + defensa);
    }
}


