package corrida_threads;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Competidor extends Thread implements Comparable<Competidor> {

    private String nome;
    private int numeroRaia;
    private double velocidade;
    private double metrosPercorridos;
    private double cansaco;
    private int sorte;
    private int qtdImprevistos;
    private double tamCorrida;
    private long horaFinalizouCorrida = 0;
    private boolean terminou = false;

    public Competidor(String nome, int numeroRaia, double velocidade,
            int sorte, double tamCorrida) {
        setNome(nome);
        setNumeroRaia(numeroRaia);
        setVelocidade(velocidade);
        setSorte(sorte);
        setTamCorrida(tamCorrida);
        calcularCansaco();
    }

    public void calcularCansaco() {
        if (getVelocidade() <= 5) {
            setCansaco(getVelocidade() * 0.01);
        } else if (getVelocidade() > 5 && getVelocidade() <= 10) {
            setCansaco(getVelocidade() * 0.02);
        } else if (getVelocidade() > 10 && getVelocidade() <= 15) {
            setCansaco(getVelocidade() * 0.03);
        } else {
            setCansaco(getVelocidade() * 0.05);
        }
    }

    public void tirarCansacoVelocidade() {
        setVelocidade(getVelocidade() - getCansaco());
    }

    public void gerarImprevisto() {
        int imprevisto;
        Random gerador = new Random();
        imprevisto = gerador.nextInt(getSorte());
        if (imprevisto == 0) {
            setVelocidade(getVelocidade() - (getVelocidade() * 0.03));
            setQtdImprevistos(getQtdImprevistos() + 1);
        }
    }

    public void run() {
        do {
            metrosPercorridos();
            gerarImprevisto();
            tirarCansacoVelocidade();
            try {
                Thread.sleep(1000);
                setHoraFinalizouCorrida(getHoraFinalizouCorrida() + 1);
                System.out.printf("Competidor: %s | Velocidade: %.2f | Cansaço: %.2f | Metros Percorridos: %.2f | QTD Imprevistos: %d \n", getNome(), getVelocidade(), getCansaco(),
                        getMetrosPercorridos(), getQtdImprevistos());
            } catch (InterruptedException ex) {
                Logger.getLogger(Competidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (!finalizouCorrida());

    }

    public boolean finalizouCorrida() {
        boolean verificador = (getMetrosPercorridos() >= getTamCorrida()|| getVelocidade() <= 0) ? true : false;
        setTerminou(verificador);
        return verificador;
    }

    public void metrosPercorridos() {
        setMetrosPercorridos(getVelocidade() + getMetrosPercorridos());
    }

    @Override
    public int compareTo(Competidor outroCompetidor) {
        if (getHoraFinalizouCorrida() < outroCompetidor.getHoraFinalizouCorrida()) {
            return -1;
        } else if (getHoraFinalizouCorrida() > outroCompetidor.getHoraFinalizouCorrida()) {
            return 1;
        }
        return 0;
    }

    public boolean isTerminou() {
        return terminou;
    }

    public void setTerminou(boolean terminou) {
        this.terminou = terminou;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroRaia() {
        return numeroRaia;
    }

    public void setNumeroRaia(int numeroRaia) {
        this.numeroRaia = numeroRaia;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public double getMetrosPercorridos() {
        return metrosPercorridos;
    }

    public void setMetrosPercorridos(double metrosPercorridos) {
        this.metrosPercorridos = metrosPercorridos;
    }

    public double getCansaco() {
        return cansaco;
    }

    public void setCansaco(double cansaco) {
        this.cansaco = cansaco;
    }

    public int getSorte() {
        return sorte;
    }

    public void setSorte(int sorte) {
        this.sorte = sorte;
    }

    public int getQtdImprevistos() {
        return qtdImprevistos;
    }

    public void setQtdImprevistos(int qtdImprevistos) {
        this.qtdImprevistos = qtdImprevistos;
    }

    public double getTamCorrida() {
        return tamCorrida;
    }

    public void setTamCorrida(double tamCorrida) {
        this.tamCorrida = tamCorrida;
    }

    public long getHoraFinalizouCorrida() {
        return horaFinalizouCorrida;
    }

    public void setHoraFinalizouCorrida(long horaFinalizouCorrida) {
        this.horaFinalizouCorrida = horaFinalizouCorrida;
    }
}
