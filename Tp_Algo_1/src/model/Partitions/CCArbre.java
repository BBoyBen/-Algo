package model.Partitions;

import model.Graphe.Arete;
import model.Graphe.Graph;
import model.Graphe.Sommet;

import java.util.ArrayList;
import java.util.List;

public class CCArbre implements ITDA, IPartition {
    protected Graph graph;
    protected List<Partition> partitions;

    protected int pere[];
    protected int hauteur[];

    private void init() {
        int i = 0;

        for(Sommet s : graph.getSommets()) {
            pere[i] = s.getNum();
            hauteur[i] = 0;
            partitions.add(new Partition(s));
            i++;
        }
    }

    public CCArbre(Graph g) {
        graph = g;
        partitions = new ArrayList<>();
        pere = new int[g.getSommets().size()];
        hauteur = new int[g.getSommets().size()];

        init();
    }

    @Override
    public Partition getPartitionByNum(int num) {
        Partition c = null;
        for(Partition cs : partitions) {
            if(cs.getNum() == num)
                c = cs;
        }
        return c;
    }

    @Override
    public int trouverClasse(Sommet s) {
        int num = s.getNum();
        while(num != pere[num]) {
            num = pere[num];
        }
        return num;
    }

    @Override
    public void union(int c1, int c2) {
        if(c1 == c2) return;

        int h1 = hauteur[c1], h2 = hauteur[c2];
        Partition p1 = getPartitionByNum(c1), p2 = getPartitionByNum(c2);

        if(h1 < h2) {
            hauteur[c2] += h1;
            hauteur[c1] = -1;
            pere[c1] = c2;
            p2.mergeClasse(p1);
            partitions.remove(p1);
        }
        else {
            if(h1 == h2)
                hauteur[c1] += 1;
            else
                hauteur[c1] += h2;
            hauteur[c2] = -1;
            pere[c2] = c1;
            p1.mergeClasse(p2);
            partitions.remove(p2);
        }
    }

    @Override
    public void calculPartitions() {
        for (Arete a : graph.getAretes())
            union(trouverClasse(a.getOrigine()), trouverClasse(a.getExtremite()));
    }

    @Override
    public List<Partition> getPartitions() { return partitions; }

    @Override
    public void afficherPartition() {
        System.out.println("model.Graphe.Partitions algo arbre :: " + partitions.size());
        /*for(int i = 0; i < partitions.size(); i++) {
            partitions.get(i).affichePartition();
            System.out.println();
        }*/
    }
}
