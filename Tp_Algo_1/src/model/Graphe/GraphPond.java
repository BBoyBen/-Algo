package model.Graphe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GraphPond extends Graph {

    private int getIndexToInsert(int poids) {
        for(int i = 0; i < aretes.size(); i++) {
            if(((AretePond) aretes.get(i)).getPoids() >= poids)
                return i;
        }
        return aretes.size();
    }

    public void ajouterArete(int o, int e, int p) {
        Sommet so = getSommetByNum(o), se = getSommetByNum(e);

        if(so != null && se != null)
            aretes.add(getIndexToInsert(p), new AretePond(so, se, p));
    }

    public void afficherGraphePond () {
        File listAretesPond = new File("src/texte/aretesPond.txt");
        File listSommetsPond = new File("src/texte/sommetsPond.txt");
        FileWriter aretePond = null;
        FileWriter sommetPond = null;

        try {
            aretePond = new FileWriter(listAretesPond);
            sommetPond = new FileWriter(listSommetsPond);

        } catch (IOException e) {
            System.out.println(e);
        }


        int nbsommet = this.getSommets().size();
        int nbAretes = this.getAretes().size();
        try {
            sommetPond.write(String.valueOf(nbsommet)+"\r\n");
            sommetPond.write(String.valueOf(nbAretes)+"\r\n");
        } catch (IOException e) {
            System.out.println(e);
        }


        for (int i=0; i<this.getAretes().size(); i++) {
            int or = this.getAretes().get(i).getOrigine().getNum();
            int ext = this.getAretes().get(i).getExtremite().getNum();
            AretePond temp = (AretePond) this.getAretes().get(i);
            int poids = temp.getPoids();
            try {
                aretePond.write(String.valueOf(or)+"\r\n");
                aretePond.write(String.valueOf(ext)+"\r\n");
                aretePond.write(String.valueOf(poids)+"\r\n");

            } catch (IOException e) {
                System.out.println(e);
            }
        }

        try {
            aretePond.close();
            sommetPond.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        String pythonScriptPath = "\"src\\scriptPy\\affGraphPond.py\"";
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
