package corrida_threads;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Corrida extends Thread {

    ArrayList<Competidor> competidores = new ArrayList<>();

    public ArrayList<Competidor> getCompetidores() {
        return competidores;
    }

    public void setCompetidores(ArrayList<Competidor> competidores) {
        this.competidores = competidores;
    }

    public void adicionarCompetidor(Competidor a) {
        competidores.add(a);
    }

    public void run() {
        System.out.println("A corrida começou!!!\n");
        for (int i = 0; i < competidores.size(); i++) {
            competidores.get(i).start();
        }
        fimCorrida();
    }

    public void fimCorrida() {
        int contTotal;
        do {
            contTotal = 0;
            for (int i = 0; i < competidores.size(); i++) {
                if (competidores.get(i).isTerminou() == true) {
                    contTotal++;
                }
            }
        } while (contTotal != competidores.size());
    }

    public void rankingCompetidor(ArrayList<Competidor> lista) {
        System.out.println("\nA corrida terminou!\n");
        System.out.println("\t\t-=-=--=-=-Resultados da corrida-=-=--=-=-\n");
        System.out.println("LUGAR\t|\tCOMPETIDOR\t|\tTEMPO DECORRIDO\t|\tIMPREVISTOS");
        for (int i = 0; i < competidores.size(); i++) {
            System.out.printf("%dº\t|\t%s    \t|\t%d segundo(s)\t|\t%d imprevisto(s)\n", i+1, competidores.get(i).getNome(), 
            competidores.get(i).getHoraFinalizouCorrida(), competidores.get(i).getQtdImprevistos());
        }
    }

}
