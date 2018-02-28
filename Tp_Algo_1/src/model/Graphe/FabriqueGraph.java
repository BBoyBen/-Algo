package model.Graphe;

import java.util.Random;

public class FabriqueGraph {
    public static Graph generateGraph(int nbSommet) throws IllegalArgumentException{
        Random r = new Random();

        return generateGraph(nbSommet, r.nextInt(nbSommet * (nbSommet - 1) / 2) + 1);
    }

    public static Graph generateGraph(int nbSommets, int nbAretes) throws IllegalArgumentException{
        if(nbSommets < 2)
            throw new IllegalArgumentException("Erreur:: nombres de sommets inférieur à 2");
        if(nbAretes > nbSommets * (nbSommets - 1) / 2)
            throw new IllegalArgumentException("Erreur:: nombres d'arêtes supérieur au nombres de sommets");

        Graph nouv = new Graph();
        for(int i = 0; i < nbSommets; i++) {
            nouv.ajouterSommet(i);
        }
        for(int j = 0; j < nbAretes; j++) {
            int e = (int) (Math.random() * ((nbSommets - 1) + 1));
            int o = (int) (Math.random() * ((nbSommets - 1) + 1));
            if (e != o && !nouv.contientArete(o, e))
                nouv.ajouterArete(e, o);
            else
                j--;
        }
        return nouv;
    }

    public static GraphPond generateGraphPond(int nbSommet) throws IllegalArgumentException{
        Random r = new Random();

        return generateGraphPond(nbSommet, r.nextInt(nbSommet * (nbSommet - 1) / 2) + 1, 100);
    }

    public static GraphPond generateGraphPond(int nbSommets, int nbAretes, int maxPoids) throws IllegalArgumentException {
        if (nbSommets < 2)
            throw new IllegalArgumentException("Erreur:: nombres de sommets inférieur à 2");
        if (nbAretes > nbSommets * (nbSommets - 1) / 2)
            throw new IllegalArgumentException("Erreur:: nombres d'arêtes supérieur au nombres de sommets");
        if (maxPoids < 1)
            throw new IllegalArgumentException("Erreur:: poids maximal inférieur à 1");

        GraphPond nouv = new GraphPond();
        for(int i = 0; i < nbSommets; i++) {
            nouv.ajouterSommet(i);
        }
        for(int j = 0; j < nbAretes; j++) {
            int e = (int) (Math.random() * ((nbSommets - 1) + 1));
            int o = (int) (Math.random() * ((nbSommets - 1) + 1));
            if (e != o && !nouv.contientArete(o, e))
                nouv.ajouterArete(e, o, (int) (Math.random() * ((maxPoids) + 1)));
            else
                j--;
        }
        return nouv;
    }
}
