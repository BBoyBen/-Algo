package main;

import model.Partitions.AcmArbre;
import model.Graphe.FabriqueGraph;
import model.Graphe.GraphPond;


public class Main {

    public static void main(String args[]) {
        Essais test = new Essais();
        test.courbes();
        test.calculAlpha();

        System.out.println("------------------------------------------");

        GraphPond gp = FabriqueGraph.generateGraphPond(6, 10, 20);
        gp.afficherGraphePond();
        AcmArbre acmArbre = new AcmArbre(gp);
        acmArbre.calculPartitions();
        acmArbre.affAcm();

    }
}
