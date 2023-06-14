public class Status {

    public static void status(int livello, Personaggio giocatore, Mostro mostro) {
        System.out.println("***************************");
        System.out.println("Livello: " + livello);
        System.out.println("Vita di " + giocatore.getNome() + ": " + giocatore.getVita());
        System.out.println("Vita del mostro: "+ mostro.getVita());
        System.out.println("Attacco di " + giocatore.getNome() + ": " + giocatore.getAttacco());
        System.out.println("");
    }
}