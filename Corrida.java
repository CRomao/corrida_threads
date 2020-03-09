
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
        ArrayList<Competidor> competidoresOrdenados = new ArrayList<>();
        Competidor maior = new Competidor("null", 0, 0, 0, 0);
        maior.setHoraFinalizouCorrida(0);
        int id = 0;
        do {
            if (lista.size() > 0) {
                for (int i = 0; i < lista.size(); i++) {
                    if (lista.get(i).getHoraFinalizouCorrida() > maior.getHoraFinalizouCorrida()) {
                        maior.setHoraFinalizouCorrida(lista.get(i).getHoraFinalizouCorrida());
                        id = i;
                    }
                }
                competidoresOrdenados.add(lista.get(id));
                lista.remove(id);
                maior.setHoraFinalizouCorrida(0);
            }
        } while (lista.size() != 0);

        for (int i = (competidoresOrdenados.size() - 1); i > -1; i--) {
            System.out.println(competidoresOrdenados.get(i).getNome() + " | " + competidoresOrdenados.get(i).getHoraFinalizouCorrida());
        }
    }
}
