package model.Partitions;

import model.Graphe.Arete;
import model.Graphe.AretePond;
import model.Graphe.GraphPond;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AcmArbre extends CCArbre {

    private List<AretePond> acm;
    private GraphPond grapheBase;

    public AcmArbre(GraphPond  g) {
       super(g);
       acm = new ArrayList<>();
       grapheBase = g;
    }

    @Override
    public void calculPartitions() {
        for (Arete a : graph.getAretes()) {
            int po = trouverClasse(a.getOrigine()), pe = trouverClasse(a.getExtremite());
            if(pe != po) {
                union(pe, po);
                acm.add((AretePond) a);
            }
        }
    }

    public List<AretePond> getAcm() {
        return acm;
    }

    public GraphPond getGrapheBase() {
        return grapheBase;
    }

    public void afficherAcm() {
        System.out.println("Arbre couvrant :");
        for(AretePond a : acm)
            System.out.println(a);
    }

    public void affAcm () {
        File listAretesAcm = new File("src/texte/aretesAcm.txt");
        File listSommetsAcm = new File("src/texte/sommetsAcm.txt");
        FileWriter areteAcm = null;
        FileWriter sommetAcm = null;

        try {
            areteAcm = new FileWriter(listAretesAcm);
            sommetAcm = new FileWriter(listSommetsAcm);

        } catch (IOException e) {
            System.out.println(e);
        }


        int nbsommet = this.getGrapheBase().getSommets().size();
        int nbAretes = this.getAcm().size();
        try {
            sommetAcm.write(String.valueOf(nbsommet)+"\r\n");
            sommetAcm.write(String.valueOf(nbAretes)+"\r\n");
        } catch (IOException e) {
            System.out.println(e);
        }


        for (int i=0; i<nbAretes; i++) {
            int or = this.getAcm().get(i).getOrigine().getNum();
            int ext = this.getAcm().get(i).getExtremite().getNum();
            AretePond temp = this.getAcm().get(i);
            int poids = temp.getPoids();
            try {
                areteAcm.write(String.valueOf(or)+"\r\n");
                areteAcm.write(String.valueOf(ext)+"\r\n");
                areteAcm.write(String.valueOf(poids)+"\r\n");

            } catch (IOException e) {
                System.out.println(e);
            }
        }

        try {
            areteAcm.close();
            sommetAcm.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        String pythonScriptPath = "\"src\\scriptPy\\affAcm.py\"";
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
}
