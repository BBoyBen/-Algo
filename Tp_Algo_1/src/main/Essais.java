package main;

import model.Graphe.FabriqueGraph;
import model.Graphe.Graph;
import model.Partitions.CCArbre;
import model.Partitions.CCBourrin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Essais {

    private ArrayList tpsArbre = new ArrayList();
    private ArrayList tpsBourrin = new ArrayList();

    public Essais () {

        System.out.println("Essais pour des graphes de 10 à 150 sommets par cap de 10 sommets");
        System.out.println();

        for (int i = 10; i<=150; i+=10) {
            Graph gTest = FabriqueGraph.generateGraph(i, (2*i));

            CCArbre CCArbreTest = new CCArbre(gTest);
            CCBourrin CCBourrinTest = new CCBourrin(gTest);

            long startTimeArbretest = System.nanoTime();
            CCArbreTest.calculPartitions();
            long endTimeArbretest = System.nanoTime();
            CCBourrinTest.calculPartitions();
            long endTimeBourintest = System.nanoTime();

            tpsArbre.add((int)(endTimeArbretest - startTimeArbretest));
            tpsBourrin.add((int)(endTimeBourintest - endTimeArbretest));
        }

        System.out.println("Temps d'execution TDA arbre :");
        for (int i=0; i<tpsArbre.size(); i++) {
            System.out.print("| "+((int)tpsArbre.get(i)/ 1000000.0)+"ms |");
        }
        System.out.println();

        System.out.println("Temps d'execution TDA bourrin :");
        for (int i=0; i<tpsBourrin.size(); i++) {
            System.out.print("| "+((int)tpsBourrin.get(i)/ 1000000.0)+"ms |");
        }
        System.out.println();
    }


    public void courbes () {
        File tpsArbre = new File("src/texte/tpsExecArbre.txt");
        File tpsBourrin = new File("src/texte/tpsExecBourrin.txt");
        FileWriter arbreW = null;
        FileWriter bourrinW = null;

        try {
            arbreW = new FileWriter(tpsArbre);
            bourrinW = new FileWriter(tpsBourrin);

        } catch (IOException e) {
            System.out.println(e);
        }

        for (int i = 0; i<this.tpsArbre.size(); i++) {
            int strA = (int) this.tpsArbre.get(i);
            int strB = (int) this.tpsBourrin.get(i);

            try {
                arbreW.write(String.valueOf(strA)+"\r\n");
                bourrinW.write(String.valueOf(strB)+"\r\n");

            } catch (IOException e) {
                System.out.println(e);
            }
        }
        try {
            arbreW.close();
            bourrinW.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        String pythonScriptPath = "\"src\\scriptPy\\affCourbe.py\"";

        String[] cmd = new String[2];
        cmd[0] = "python.exe";
        cmd[1] = pythonScriptPath;
        Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec(cmd);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public ArrayList getTpsArbre() {
        return tpsArbre;
    }

    public ArrayList getTpsBourrin() {
        return tpsBourrin;
    }

    public void calculAlpha () {
        DecimalFormat df = new DecimalFormat("0.000");
        double n1 = 20;
        double n2 = 130;
        double tps1 = ((int) this.getTpsArbre().get(1))/1000000.0;
        double tps2 = ((int) this.getTpsArbre().get(12))/1000000.0;

        double alphaAbre = (Math.log(tps1)-Math.log(tps2))/(Math.log(n1)-Math.log(n2));

        tps1 = ((int) this.getTpsBourrin().get(1))/1000000.0;
        tps2 = ((int) this.getTpsBourrin().get(12))/1000000.0;

        double alphaBourrin = (Math.log(tps1)-Math.log(tps2))/(Math.log(n1)-Math.log(n2));

        System.out.println("Pour n sommets le temps d'exécution peut s'approximer par k*n^alpha");
        System.out.println("On exprime la compléxité de l'algorithme utilisée avec alpha");
        System.out.println("Compléxité alpha pour l'algo Arbre : " + df.format(alphaAbre));
        System.out.println("Compléxité alpha pour l'algo Bourrin : " + df.format(alphaBourrin));
    }
}
