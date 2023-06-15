public class Mostro extends Personaggio {  // Costruttore della classe Mostro, che richiama il costruttore della classe Personaggio
    public Mostro(String nome, int vita, int attacco) {
        super(nome, vita, attacco);
    } // Metodo che gestisce l'attacco del mostro al personaggio nemico
    @Override
    public void attacca(Personaggio nemico) {
        nemico.setVita(nemico.getVita() - (random.nextInt(10 - 1 + 1) + 1 + Dungeon.livello)); // si calcola il danno e si toglie il danno alla vita del personaggio nemico
    }
}
