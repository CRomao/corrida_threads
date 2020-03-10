package corrida_threads;

import java.util.Collections;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Competidor competidor1 = new Competidor("Thread1", 1, 13, 6, 10);
        Competidor competidor2 = new Competidor("Thread2", 2, 15, 1, 10);
        Competidor competidor3 = new Competidor("Thread3", 3, 5, 1, 10);

        Corrida corrida = new Corrida();

        corrida.adicionarCompetidor(competidor1);
        corrida.adicionarCompetidor(competidor2);
        corrida.adicionarCompetidor(competidor3);
        
        corrida.start();

        corrida.join();
        
        Collections.sort(corrida.getCompetidores());
        
        corrida.rankingCompetidor(corrida.getCompetidores());

    }
}
