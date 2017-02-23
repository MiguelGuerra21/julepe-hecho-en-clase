import java.util.Random;
import java.util.ArrayList;
/**
 * Write a description of class Jugador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Jugador
{
    private String nombre;
    private Carta[] cartasQueTieneEnLaMano;
    private int numeroCartasEnLaMano;
    private ArrayList<Baza> bazasGanadas;

    /**
     * Constructor for objects of class Jugador
     */
    public Jugador(String nombreJugador)
    {
        nombre = nombreJugador;
        cartasQueTieneEnLaMano = new Carta[5];
        numeroCartasEnLaMano = 0;   
        bazasGanadas = new ArrayList<Baza>();
    }

    /**
     * Metodo que hace que el jugador reciba una carta
     */
    public void recibirCarta(Carta cartaRecibida)
    {
        if (numeroCartasEnLaMano < 5) {
            cartasQueTieneEnLaMano[numeroCartasEnLaMano] = cartaRecibida;
            numeroCartasEnLaMano++;
        }
    }

    /**
     * Metodo que muestra las cartas del jugador por pantalla
     */
    public void verCartasJugador()
    {
        for (Carta cartaActual : cartasQueTieneEnLaMano) {
            if (cartaActual != null) {
                System.out.println(cartaActual);
            }
        }
    }

    /**
     * Metodo que devuelve el nombre del jugador
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Metodo que devuelve la carta especificada como parametro si
     * el jugador dispone de ella y simula que se lanza a la mesa
     */    
    public Carta tirarCarta(String nombreCarta)
    {
        Carta cartaTirada = null;

        if (numeroCartasEnLaMano > 0) {

            int cartaActual = 0;
            boolean buscando = true;
            while (cartaActual < cartasQueTieneEnLaMano.length && buscando) {
                if (cartasQueTieneEnLaMano[cartaActual] != null) {                                 
                    if (nombreCarta.equals(cartasQueTieneEnLaMano[cartaActual].toString())) {
                        buscando = false;
                        cartaTirada = cartasQueTieneEnLaMano[cartaActual];
                        cartasQueTieneEnLaMano[cartaActual] = null;
                        numeroCartasEnLaMano--;
                        System.out.println(nombre + " ha tirado " + cartaTirada);
                    }
                }
                cartaActual++;
            }

        }
        return cartaTirada;
    }

    /**
     * Método que tira una carta aleatoria 
     */
    public Carta tirarCartaAleatoria() 
    {
        Carta cartaTirada = null;

        if (numeroCartasEnLaMano > 0) {
            Random aleatorio = new Random();

            boolean elJugadorHaTiradoUnaCarta = false;
            while (elJugadorHaTiradoUnaCarta == false) {
                int posicionAleatoria = aleatorio.nextInt(5);
                if (cartasQueTieneEnLaMano[posicionAleatoria] != null) {
                    cartaTirada = cartasQueTieneEnLaMano[posicionAleatoria];
                    cartasQueTieneEnLaMano[posicionAleatoria] = null;
                    numeroCartasEnLaMano--;
                    System.out.println(nombre + " ha tirado " + cartaTirada);
                    elJugadorHaTiradoUnaCarta = true;
                }
            }

        }

        return cartaTirada;
    }

    /**
     * Metodo que tira una carta "inteligentemente"(con un poco de retraso mental pero beh...)
     */
    public Carta tirarCartaInteligentemente(Palo paloPrimeraCartaDeLaBaza, 
    Carta cartaQueVaGanando,
    Palo paloQuePinta){
        Carta cartaATirar = null;
        Random aleatorio = new Random();
        int posicionAleatoria = aleatorio.nextInt(5);
        //Si le quedan cartas en la mano
        if(numeroCartasEnLaMano > 0){
            //mira el palo que hay en la baza como primero y asiste intentando ganar, si no puede ganar solo asiste y si no tiene ninguna de ambas tira una aleatoria.
            for(Carta carta : cartasQueTieneEnLaMano){
                //lee el palo de la carta a la que asistir y lo compara con las cartas de su mano encuentra la carta mas alta para asistir o ganar y si no puede tira una cualquiera.
                if(carta != null){
                    if(carta.getPalo() == paloPrimeraCartaDeLaBaza && carta.ganaA(cartaQueVaGanando, paloQuePinta)){
                        cartaATirar = carta;
                    }
                    else if(carta.getPalo() == paloPrimeraCartaDeLaBaza){
                        cartaATirar = carta;
                    }
                    else{
                        cartaATirar = cartasQueTieneEnLaMano[posicionAleatoria];
                    }
                }
            }
            return cartaATirar;
        }
        tirarCarta(cartaATirar.toString());
        return cartaATirar;
    }

    /**
     * Metodo que hace que jugador recoja una baza ganada
     */
    public void addBaza(Baza bazaGanada){
        bazasGanadas.add(bazaGanada);

    }

    /**
     * Metodo que devuelve el numero de bazas ganadas por el jugador hasta
     * el momento
     */
    public int getNumeroBazasGanadas() 
    {
        return bazasGanadas.size();
    }

}

