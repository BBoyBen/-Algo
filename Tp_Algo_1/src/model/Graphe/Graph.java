package model.Graphe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    protected List<Sommet> sommets;
    protected List<Arete> aretes;

    public Graph() {
        sommets = new ArrayList<>();
        aretes = new ArrayList<>();
    }

    public List<Sommet> getSommets() { return sommets; }

    public List<Arete> getAretes() { return aretes; }

    public Sommet getSommetByNum(int num) {
        Sommet so = null;
        for(Sommet s : sommets) {
            if(s.getNum() == num)
                so = s;
        }
        return so;
    }

    public void ajouterSommet(int num) {
        sommets.add(new Sommet(num));
    }

    public void ajouterArete(int o, int e) {
        Sommet so = getSommetByNum(o), se = getSommetByNum(e);

        if(so != null && se != null)
            aretes.add(new Arete(so, se));
    }

    public boolean contientArete(int o, int e) {
        for (Arete a : aretes) {
            if(a.isSame(o, e))
                return true;
        }
        return false;
    }

    public void afficher() {
        System.out.println("Nombre de sommets : " + sommets.size());
        System.out.println("Nombre de aretes : " + aretes.size());
        for(Arete a : aretes) {
            System.out.println(a);
        }
    }

    public void afficherGraphe () {
        File listAretes = new File("src/texte/aretes.txt");
        File listSommets = new File("src/texte/sommets.txt");
        FileWriter arete = null;
        FileWriter sommet = null;

        try {
            arete = new FileWriter(listAretes);
            sommet = new FileWriter(listSommets);

        } catch (IOException e) {
            System.out.println(e);
        }


        int nbsommet = this.getSommets().size();
        int nbAretes = this.getAretes().size();
        try {
            sommet.write(String.valueOf(nbsommet)+"\r\n");
            sommet.write(String.valueOf(nbAretes)+"\r\n");
        } catch (IOException e) {
            System.out.println(e);
        }


        for (int i=0; i<this.getAretes().size(); i++) {
            int or = this.getAretes().get(i).getOrigine().getNum();
            int ext = this.getAretes().get(i).getExtremite().getNum();
            try {
                arete.write(String.valueOf(or)+"\r\n");
                arete.write(String.valueOf(ext)+"\r\n");
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        try {
            arete.close();
            sommet.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        String pythonScriptPath = "\"src\\scriptPy\\affGraph.py\"";
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
