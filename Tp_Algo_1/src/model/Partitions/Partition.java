package model.Partitions;

import model.Graphe.Sommet;

import java.util.ArrayList;
import java.util.List;

public class Partition {
    private int num;
    private List<Sommet> elements;

    public Partition(Sommet s) {
        num = s.getNum();
        elements = new ArrayList<>();
        elements.add(s);
    }

    public int getNum() { return  num; }
    public void ajouterElement(Sommet s) { elements.add(s); }
    private List<Sommet> getElements() { return elements; }

    public void mergeClasse(Partition o) {
        elements.addAll(o.getElements());
    }

    public void affichePartition () {
        for (int i=0; i<this.getElements().size(); i++) {
            System.out.print(this.getElements().get(i) + " ");
        }
    }
}


