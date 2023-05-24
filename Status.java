public class Status {
    public static void status() {
        System.out.println("");
        System.out.println("Livello: " + Dungeon.livello);
        System.out.println("Vita di " + Dungeon.giocatore.getNome() + ": " + Dungeon.giocatore.getVita());
        System.out.println("Vita del mostro: " + Dungeon.mostro.getVita());
        System.out.println("Attacco di " + Dungeon.giocatore.getNome() + ": " + Dungeon.giocatore.getAttacco());
        System.out.println("");
    }
}