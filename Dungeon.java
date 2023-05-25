import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Dungeon {
    private Personaggio giocatore;
    static Mostro mostro;
    static int livello;

    public Dungeon(Personaggio giocatore) {
        this.giocatore = giocatore;
        this.livello = 1;
        this.mostro = generaMostro();
    }
    public Mostro generaMostro() {
        return new Mostro("Mostro", 20 + 5 * livello, 5);
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
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {}
        System.out.println("***************************");
        System.out.println("Livello: " + livello);
        System.out.println("Vita di " + giocatore.getNome() + ": " + giocatore.getVita());
        System.out.println("Vita del mostro: "+ mostro.getVita());
        System.out.println("Attacco di " + giocatore.getNome() + ": " + giocatore.getAttacco());
        System.out.println("");
    }

    public void cura() {
        giocatore.cura();
        mostro.attacca(giocatore);
        Graphics.drawPotion();
        System.out.println(giocatore.getNome() + " sta bevendo una pozione magica!");
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {}
        System.out.println("***************************");
        System.out.println("Livello: " + livello);
        System.out.println("Vita di " + giocatore.getNome() + ": " + giocatore.getVita());
        System.out.println("Vita del mostro: "+ mostro.getVita());
        System.out.println("Attacco di " + giocatore.getNome() + ": " + giocatore.getAttacco());
        System.out.println("");
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
        Personaggio giocatore = new Personaggio("Test", 100, 10);
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
                    System.out.println("***************************");
                    System.out.println("Livello: " + livello);
                    System.out.println("Vita di " + giocatore.getNome() + ": " + giocatore.getVita());
                    System.out.println("Vita del mostro: "+ mostro.getVita());
                    System.out.println("Attacco di " + giocatore.getNome() + ": " + giocatore.getAttacco());
                    System.out.println("");
                    break;
            }
        }
    }
}