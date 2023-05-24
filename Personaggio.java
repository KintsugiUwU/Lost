import java.util.Random;

public class Personaggio {
    private String nome;
    private int vita;
    private int attacco;
    Random random = new Random();


    public Personaggio(String nome, int vita, int attacco) {
        this.nome = nome;
        this.vita = vita;
        this.attacco = attacco;
    }

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

    public void attacca(Personaggio nemico) {
        nemico.setVita(nemico.getVita() - this.attacco);
    }

    public void cura() {
        setVita(getVita()+ random.nextInt(20 - 5 + 1)+5);
    }
}