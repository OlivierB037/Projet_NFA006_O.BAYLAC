package fr.aperikub.projetNFA006;

import java.util.Comparator;

public class ListeDePages implements Comparable<ListeDePages> {
    private int numeroPage;
    private ListeDePages next;
    public ListeDePages(int numero){
        numeroPage = numero;
        next = null;
    }
    public ListeDePages(int numero, ListeDePages _next){
        numeroPage = numero;
        next = _next;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ListeDePages)) {
            return false;
        }
        else if (obj == this){
            return true;
        }
        else{ return ((ListeDePages) obj).getNumeroPage() != numeroPage; }
    }

    @Override
    public String toString() {
        return "page "+numeroPage;
    }

    public int getNumeroPage() {
        return numeroPage;
    }

    public void setNumeroPage(int numeroPage) {
        this.numeroPage = numeroPage;
    }

    public ListeDePages getNext() {
        return next;
    }

    public void setNext(ListeDePages next) {
        this.next = next;
    }


    @Override
    public int compareTo(ListeDePages o) {
        return Integer.compare(numeroPage,o.getNumeroPage());
    }
}
