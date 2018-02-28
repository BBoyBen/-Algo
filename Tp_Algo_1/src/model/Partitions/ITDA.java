package model.Partitions;

import model.Graphe.Sommet;

public interface ITDA {
    int trouverClasse(Sommet s);
    void union(int c1, int c2);
}
