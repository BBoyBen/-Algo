package model.Graphe;

public class AretePond extends Arete {
    private int poids;

    public AretePond(Sommet o, Sommet e) {
        this(o, e, 0);
    }

    public AretePond(Sommet o, Sommet e, int pds) {
        super(o, e);
        poids = pds;
    }

    public int getPoids() {
        return poids;
    }

    @Override
    public String toString() {
        return super.toString() + " -- " + poids;
    }
}
