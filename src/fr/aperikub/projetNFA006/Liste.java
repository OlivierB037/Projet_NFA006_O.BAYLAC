package fr.aperikub.projetNFA006;

public interface Liste {

    public void add(Cellule obj);
    Cellule get(int index);
    void replace(int index,Cellule obj);
    void remove(int index);
    boolean isEmpty();
    int length();

}
