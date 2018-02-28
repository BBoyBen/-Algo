package model.Graphe;

public class Arete {
    private Sommet origine, extremite;

    public Arete(Sommet o, Sommet e) {
        origine = o;
        extremite = e;
    }

    public Sommet getOrigine() {
        return origine;
    }

    public Sommet getExtremite() {
        return extremite;
    }

    public boolean isSame(int o, int e) {
        return origine.getNum() == o && extremite.getNum() == e || origine.getNum() == e && extremite.getNum() == o;
    }

    @Override
    public String toString() {
        return origine.getNum() + "->" + extremite.getNum();
    }
}
