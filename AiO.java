import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Dungeon {
    private Personaggio giocatore;
    private Mostro mostro;
    static int livello;

    public Dungeon(Personaggio giocatore) {
        this.giocatore = giocatore;
        this.livello = 1;
        this.mostro = generaMostro();
    }

    public Mostro generaMostro() {
        return new Mostro("Mostro", 20 + 5 * livello,5);
    }

    public void combatti() {
        giocatore.attacca(mostro);
        if (mostro.getVita() <= 0) {
            System.out.println("Hai sconfitto il mostro!");
            System.out.println("Ma uno più forte si sta avvicinando!");
            giocatore.setAttacco(giocatore.getAttacco()+1);
            mostro = generaMostro();
            livello++;
        } else {
            System.out.println(giocatore.getNome() + " sta combattendo duramente!");
            Graphics.drawSword();
            mostro.attacca(giocatore);
            if (giocatore.getVita() <= 0) {
                System.out.println("Sei stato sconfitto...");
                System.exit(0);
            }
        }
        try{
            Thread.sleep(2000);
        } catch(InterruptedException e) {
        }
        Status.status(livello, giocatore, mostro);
    }

    public void cura() {
        giocatore.cura();
        mostro.attacca(giocatore);
        Graphics.drawPotion();
        System.out.println(giocatore.getNome() + " sta bevendo una pozione magica!");
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {}
        Status.status(livello, giocatore, mostro);
    }

    public void salvaLivello(String file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println(livello);
            writer.println(giocatore.getVita());
            writer.println(giocatore.getAttacco());
            writer.println(mostro.getVita());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void caricaLivello(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            livello = Integer.parseInt(reader.readLine());
            giocatore.setVita(Integer.parseInt(reader.readLine()));
            giocatore.setAttacco(Integer.parseInt(reader.readLine()));
            mostro.setVita(Integer.parseInt(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci il nome del tuo personaggio: ");
        String nomeGiocatore = input.nextLine();
        Personaggio giocatore = new Personaggio(nomeGiocatore, 100, 10);
        Dungeon dungeon = new Dungeon(giocatore);

        while (true) {
            Graphics.drawLostArt();
            System.out.println("");
            System.out.println("Scegli un'azione:");
            System.out.println("1. Combatti");
            System.out.println("2. Cura");
            System.out.println("3. Salva");
            System.out.println("4. Carica");
            System.out.println("5. Esci");
            System.out.println("6. Check Carica");

            int scelta = input.nextInt();

            switch (scelta) {
                case 1:
                    dungeon.combatti();
                    break;
                case 2:
                    dungeon.cura();
                    break;
                case 3:
                    dungeon.salvaLivello("livello.csv");
                    break;
                case 4:
                    dungeon.caricaLivello("livello.csv");
                    break;
                case 5:
                    System.exit(0);
                    break;
                case 6:
                    Status.status(dungeon.livello, giocatore, dungeon.mostro);
                    break;
            }
        }
    }
}
public class Graphics {
    public static void drawLostArt() {
        String[] lostArt;
        lostArt = new String[]{
                "*       *****  *****  *****",
                "*       *   *  *        *  ",
                "*       *   *  *****    *  ",
                "*       *   *      *    *  ",
                "******  *****  *****    *  ",
        };
        for (String line : lostArt) {
            System.out.println(line);
        }
    }

    public static void drawPotion() {
        String[] potionArt;
        potionArt = new String[]{
                "   ***   ",
                "  *   *  ",
                "   * *  ",
                " *     * ",
                " *******",
        };
        for (String line : potionArt) {
            System.out.println(line);
        }
    }
    public static void drawSword() {
        String[] swordArt;
        swordArt = new String[]{
                "*",
                "* *",
                " * *",
                "  ***",
                "    *",
        };
        for (String line : swordArt) {
            System.out.println(line);
        }
    }
}
public class Mostro extends Personaggio {
    public Mostro(String nome, int vita, int attacco) {
        super(nome, vita, attacco);
    }
    @Override
    public void attacca(Personaggio nemico) {
        nemico.setVita(nemico.getVita() - (random.nextInt(10 - 1 + 1) + 1 + Dungeon.livello));
    }
}
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
