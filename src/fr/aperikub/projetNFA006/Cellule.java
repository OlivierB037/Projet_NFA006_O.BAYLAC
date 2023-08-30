package fr.aperikub.projetNFA006;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Cellule implements Comparable<Cellule>, PageList,Iterable<Integer>{
    private Cellule nextName;
    private String nom;
    private ListeDePages first;
    private ListeDePages last;
    private int length;
    public Cellule(String _nom,int firstPage){
        nom = _nom;
        first = new ListeDePages(firstPage);
        last = first;
        nextName = null;
        length = 1;

    }
    public Cellule(String _nom){
        nom = _nom;
        first = null;
        last = null;
        nextName = null;
        length = 0;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Cellule)){
            return false;
        }
        else{
            return nom.equals(((Cellule) obj).getNom());
        }
    }

    @Override
    public String toString() {
        return nom;
    }
    public int getLength() {
        return length;
    }


    public Cellule getNextName() {
        return nextName;
    }

    protected void setNextName(Cellule nextName) {
        this.nextName = nextName;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ListeDePages getFirst() {
        return first;
    }

    protected void setFirst(ListeDePages first) {
        this.first = first;
    }

    public ListeDePages getLast() {
        return last;
    }

    protected void setLast(ListeDePages last) {
        this.last = last;
    }

    @Override
    public int compareTo(Cellule o) {
        return nom.compareTo(o.getNom());
    }

    @Override
    public boolean insertPage(int numeroPage) {
        System.out.println("insertPage called for page " + numeroPage);
        //TODO : rendre dichotomique
        ListeDePages page = first;
        ListeDePages previous = null;
        if (page == null){
            System.out.println("insertPage : inserting initial entry");
            ListeDePages newPage = new ListeDePages(numeroPage);
            setFirst(newPage);
            setLast(newPage);
            length++;
            return true;
        }

        do {

            if (page.getNumeroPage() == numeroPage){
                System.out.println("page already put");
                return false;
            }
            if (page.getNumeroPage() < numeroPage){
                if (page.getNumeroPage() == last.getNumeroPage()){
                    System.out.println("page insert : inserting at the end of cell");
                    ListeDePages newPage = new ListeDePages(numeroPage);
                    page.setNext(newPage);
                    setLast(newPage);
                    length++;
                    return true;
                }
                else {
                    System.out.printf("page insert : current page = %d < added page = %d , checking next page\n",page.getNumeroPage(),numeroPage );
                    previous = page;
                    page = page.getNext();
                }
            }
            else{
                System.out.printf("page insert : current page = %d > added page = %d , inserting page\n",page.getNumeroPage(),numeroPage );
                ListeDePages newPage = new ListeDePages(numeroPage);
                if (previous == null){
                    System.out.println("page insert : insert in first position");
                    newPage.setNext(page);
                    setFirst(newPage);
                }
                else {

                    newPage.setNext(page);
                    previous.setNext(newPage);
                    newPage.setNext(page);
                }
                length++;
                return true;
            }

        }while (page != null);

        return false;
    }

    @Override
    public ListeDePages createPage(int numeroPage) {
        return new ListeDePages(numeroPage);
    }

    @Override
    public void showPages() {
        System.out.println("pages contenant "+nom+" :");
        for (Integer i: this){
            System.out.println(i);
        }
    }

    @Override
    public boolean removePage(int numeroDePage) {
        ListeDePages tmp = first;
        ListeDePages previous = null;

        do{
            if (tmp.getNumeroPage() == numeroDePage){
                if (previous == null){
                    System.out.println("removing first page");
                    first = tmp.getNext();
                    length--;
                    return true;
                }
                else{
                    System.out.printf("removing page %d ,  between %d and %d\n",numeroDePage,previous.getNumeroPage(),tmp.getNext().getNumeroPage());
                    previous.setNext(tmp.getNext());
                    length--;
                    return true;
                }
            }
            else{
                System.out.println("current page : "+ tmp.getNumeroPage()+" checking next");
                previous = tmp;
                tmp = tmp.getNext();


            }
        }while(tmp != null);
        return false;
    }

    @Override
    public ListeDePages getPage(int numeroDePage) {
        ListeDePages tmp = first;

        do{
            if (tmp.getNumeroPage() == numeroDePage){
                return tmp;
            }
            else{

               tmp = tmp.getNext();

            }
        }while(tmp != null);
        return null;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new PageIterator();
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return Iterable.super.spliterator();
    }
    class PageIterator implements Iterator<Integer>{
        ListeDePages listeDePages = first;
        public PageIterator(){

        }
        @Override
        public boolean hasNext() {

            return (listeDePages != null);
        }

        @Override
        public Integer next() {
            ListeDePages tmp = listeDePages;
            listeDePages = listeDePages.getNext();
            return tmp.getNumeroPage();
        }
    }



}
