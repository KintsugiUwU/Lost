import java.util.Random;

public class Personaggio {
    private String nome;
    private int vita;
    private int attacco;
    Random random = new Random();


    public Personaggio(String nome, int vita, int attacco) { // Costruttore della classe Personaggio
        this.nome = nome;
        this.vita = vita;
        this.attacco = attacco;
    }
    // Metodi getter e setter per le variabili di istanza
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVita() {
        return vita;
    }
    public void setVita(int vita) {
        this.vita = vita;
    }

    public int getAttacco() {
        return attacco;
    }
    public void setAttacco(int attacco) {
        this.attacco = attacco;
    }

    public void attacca(Personaggio nemico) { // Metodo che gestisce l'attacco del personaggio nemico
        nemico.setVita(nemico.getVita() - this.attacco);
    }

    public void cura() { // Metodo che gestisce la cura del personaggio
        setVita(getVita()+ random.nextInt(20 - 5 + 1)+5);
    }
}
