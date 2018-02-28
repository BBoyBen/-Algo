package model.Graphe;

public class Sommet {
    private int num;

    public Sommet(int n) {
        num = n;
    }

    public int getNum() { return num; }

    @Override
    public String toString() {
        return "" + num;
    }
}
