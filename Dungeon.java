import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Dungeon {
    private Personaggio giocatore;
    private Mostro mostro;
    static int livello; // livello è una variabile statica che viene condivisa tra tutte le istanze della classe Dungeon

    public Dungeon(Personaggio giocatore) { // Costruttore della classe Dungeon, che riceve un oggetto Personaggio come parametro
        this.giocatore = giocatore;
        this.livello = 1;
        this.mostro = generaMostro();
    }

    public Mostro generaMostro() { // Metodo che genera un nuovo Mostro per il livello corrente
        return new Mostro("Mostro", 20 + 5 * livello,5);
    }

    public void combatti() { // Metodo che gestisce il combattimento tra il giocatore e il mostro
        giocatore.attacca(mostro); // il giocatore attacca il mostro
        if (mostro.getVita() <= 0) { // se il mostro è stato sconfitto
            System.out.println("Hai sconfitto il mostro!");
            System.out.println("Ma uno più forte si sta avvicinando!");
            giocatore.setAttacco(giocatore.getAttacco()+1); // il giocatore aumenta il suo attacco
            mostro = generaMostro(); // si genera un nuovo mostro per il livello successivo
            livello++; // si aumenta il livello
        } else {  // se il mostro non è stato sconfitto
            System.out.println(giocatore.getNome() + " sta combattendo duramente!");
            Graphics.drawSword(); // si disegna una spada
            mostro.attacca(giocatore); // il mostro attacca il giocatore
            if (giocatore.getVita() <= 0) { // se il giocatore è stato sconfitto
                System.out.println("Sei stato sconfitto...");
                System.exit(0); // il programma termina
            }
        }
        try{
            Thread.sleep(2000); // si mette in pausa il programma per 2 secondi
        } catch(InterruptedException e) {
        }
        Status.status(livello, giocatore, mostro); // si mostra lo stato del livello e del personaggio
    }

    public void cura() { // Metodo che gestisce la cura del personaggio
        giocatore.cura(); // il personaggio si cura
        mostro.attacca(giocatore); // il mostro attacca il personaggio
        Graphics.drawPotion(); // si disegna una pozione
        System.out.println(giocatore.getNome() + " sta bevendo una pozione magica!");
        try {
            Thread.sleep(2000); // si mette in pausa il programma per 2 secondi
        } catch(InterruptedException e) {}
        Status.status(livello, giocatore, mostro); // si mostra lo stato del livello e del personaggio
    }

    public void salvaLivello(String file) { // Metodo che salva lo stato del livello corrente su file
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println(livello);
            writer.println(giocatore.getVita());
            writer.println(giocatore.getAttacco());
            writer.println(mostro.getVita());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void caricaLivello(String file) {   // Metodo che carica lo stato del livello corrente da file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            livello = Integer.parseInt(reader.readLine());
            giocatore.setVita(Integer.parseInt(reader.readLine()));
            giocatore.setAttacco(Integer.parseInt(reader.readLine()));
            mostro.setVita(Integer.parseInt(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) { // Metodo principale del programma
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci il nome del tuo personaggio: ");
        String nomeGiocatore = input.nextLine();
        Personaggio giocatore = new Personaggio(nomeGiocatore, 100, 10); // si crea un nuovo personaggio
        Dungeon dungeon = new Dungeon(giocatore); // si crea un nuovo dungeon

        while (true) { // il programma gira in loop finché non viene terminato
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
