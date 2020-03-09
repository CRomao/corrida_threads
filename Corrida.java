package corrida_threads;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Corrida extends Thread{

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
        for (int i = 0; i < competidores.size(); i++) {
            competidores.get(i).start();
        }
        fimCorrida();

    }
    
    public void fimCorrida(){
        int i=0,cont = 0, contTotal = 0;
        do{
            if(competidores.get(i).isTerminou() == false){
                i++;
            }else{
                cont++;
                i++;
            }
            if(i >= competidores.size()){
                i = 0;
                for(int j=0; j<competidores.size(); j++){
                    if(competidores.get(j).isTerminou() == true){
                        contTotal++;
                    }
                }
                if(competidores.size() == contTotal){
                    cont = competidores.size();
                    break;
                }else{
                    cont = 0;
                    contTotal = 0;
                }
            }
        }while(cont != competidores.size());
    }
    
    public void rankingCompetidor(ArrayList<Competidor> lista) {
        for (int i = 0; i<competidores.size(); i++) {
            System.out.println(competidores.get(i).getNome() + " | " + competidores.get(i).getHoraFinalizouCorrida() + " segundos");
        }
    }


}
