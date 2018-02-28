package model.Partitions;

import model.Graphe.Arete;
import model.Graphe.Graph;
import model.Graphe.Sommet;

import java.util.ArrayList;
import java.util.List;

public class CCBourrin implements ITDA, IPartition {
    private Graph graph;
    private int classes[];

    private List<Partition> partitions;

    public CCBourrin(Graph g) {
        graph = g;
        classes = new int[g.getSommets().size()];
        partitions = new ArrayList<>();

        init();
    }

    private void init() {
        int i = 0;
        for (Sommet s : graph.getSommets()) {
            classes[i] = s.getNum();
            partitions.add(new Partition(s));
            i++;
        }
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
        while(num != classes[num]) {
            num = classes[num];
        }
        return num;
    }

    @Override
    public void union(int c1, int c2) {
        int p1 = trouverClasse(graph.getSommetByNum(c1)), p2 = trouverClasse(graph.getSommetByNum(c2));
        for(int i = 0; i< classes.length; i++) {
            if(classes[i] == p2)
                classes[i] = p1;
        }
    }

    @Override
    public void calculPartitions() {
        for (Arete a : graph.getAretes()) {
            union(a.getOrigine().getNum(), a.getExtremite().getNum());
        }

        for(int i = 0; i<classes.length; i++) {
            Partition p1 = getPartitionByNum(classes[i]), p2 = getPartitionByNum(i);
            if(p1 != p2) {
                p1.mergeClasse(p2);
                partitions.remove(p2);
            }
        }
    }

    @Override
    public List<Partition> getPartitions() { return partitions; }

    @Override
    public void afficherPartition() {
        System.out.println("model.Graphe.Partitions algo bourrin :: " + partitions.size());
        /*for(int i = 0; i < partitions.size(); i++) {
            partitions.get(i).affichePartition();
            System.out.println();
        }*/
    }
}
