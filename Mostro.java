public class Mostro extends Personaggio {
    public Mostro(String nome, int vita, int attacco) {
        super(nome, vita, attacco);
    }
    @Override
    public void attacca(Personaggio nemico) {
        nemico.setVita(nemico.getVita() - (random.nextInt(10 - 1 + 1) + 1 + Dungeon.livello));
    }
}