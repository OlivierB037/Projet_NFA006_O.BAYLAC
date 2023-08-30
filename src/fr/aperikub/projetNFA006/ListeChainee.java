package fr.aperikub.projetNFA006;

import java.util.*;
import java.util.function.Consumer;

public class ListeChainee implements Liste, Iterable<Cellule>,Comparator<Cellule>{
    private Cellule head;
    private int length;
    public ListeChainee(){
        head = null;
        length = 0;
    }

    private void insert(int index, Cellule obj) throws IndexOutOfBoundsException  {
        if (index < 0 || index >= length){
            throw new ArrayIndexOutOfBoundsException();
        }
        Cellule cell = head;
        if (index == 0){
            System.out.println("inserting " + obj.getNom()+" at index "+index);
            obj.setNextName(cell);
            head = obj;
            System.out.println("head = "+ head.getNom()+" head.next = "+head.getNextName().getNom());

        }
        else {
            cell = head;
            Cellule previous = null;
            int i = 0;
            do {
                previous = cell;
                cell = cell.getNextName();
                i++;
            } while (i < index);


            previous.setNextName(obj);
            obj.setNextName(cell);
        }

    }


    @Override
    public Cellule get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length){
            throw new ArrayIndexOutOfBoundsException();
        }
        Cellule noeud = head;
        if (index > 0) {
            for (int i = 0; i < index; i++) {
                noeud = noeud.getNextName();
            }
        }

        return noeud;
    }

    @Override
    public void replace(int index, Cellule obj) {
        if (index < 0 || index >= length){
            throw new ArrayIndexOutOfBoundsException();
        }
        Cellule noeud = head;
        Cellule previous = null;
        if (index > 0) {
            for (int i = 0; i < (index-1); i++) {
                noeud = noeud.getNextName();
            }
        }
        previous = noeud;
        noeud = noeud.getNextName();
        obj.setNextName(noeud.getNextName());
        previous.setNextName(obj);

    }

    @Override
    public void remove(int index) throws IndexOutOfBoundsException {

        if (index < 0 || index >= length){
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == 0){
            head = head.getNextName();
        }
        else {
            Cellule cell = head;
            Cellule previous = null;

            for (int i = 0; i < index; i++) {
                previous = cell;
                cell = cell.getNextName();
            }
            previous.setNextName(cell.getNextName());
        }
        length--;
    }

    public void sort(Comparator<? super Cellule> comparator){
        ArrayList<Cellule> list = new ArrayList<>();

         for (int i = 0;i<length;i++){
             for (int j = 0; j < (length-1); j++){
                 if (comparator.compare(get(j),get(i)) >= 0){
                     Cellule tmp = get(i);
                     replace(i,get(j));
                     replace(j, tmp);
                 }
             }
         }
    }

    public boolean containsName(String nom){
        if (head != null) {
            for (Cellule c : this) {
                if (c.getNom().equals(nom)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void add(Cellule obj) {
        Cellule tmp = head;

       if (isEmpty()){
           System.out.println("adding "+obj.getNom()+ " liste is empty adding at index 0");
           head = obj;
           length++;
       }
       else{
           if (compare(tmp,obj) == 0){
               throw new IllegalArgumentException("name already registered");
           }

           int i = 0 ;

           if (compare(tmp,obj) > 0){
               System.out.printf("tmp = %s > inserted name = %s inserting at index 0\n", tmp.getNom(),obj.getNom());
               insert(i,obj);
               length++;
           }
           else {
               while (compare(tmp, obj) < 0) {
//                   System.out.printf("tmp = %s < inserted name = %s\n", tmp.getNom(), obj.getNom());
                   if (tmp.getNextName() != null) {
//                       System.out.println(tmp.getNom() + ".next is not null, checking next : " + tmp.getNextName().getNom());
                       tmp = tmp.getNextName();
                       i++;
                   } else {
//                       System.out.println(tmp.getNom() + ".next is null, ending loop");
                       i++;
                       break;
                   }
               }
               length++;
               insert(i,obj);

           }


       }
    }


    public Integer getIndex(String nom) {
        Cellule cell = head;
        for (int i = 0; i < length; i++){
            if (cell.getNom().equals(nom)){
                return i;
            }
            if (cell.getNom().compareTo(nom) > 0){
                System.out.printf("%s has been passed with  no result for %s",cell,nom);
                break;
            }
            cell = cell.getNextName();
        }
        return  null;
    }
    public Cellule[] searchName(){
        Cellule[] cells = new Cellule[length];

        return cells;
    }




    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public int compare(Cellule o1, Cellule o2) {
        return o1.compareTo(o2);
    }


    class CellIterator implements Iterator<Cellule>{
        ListeChainee liste;
        Cellule cell;
        public CellIterator(ListeChainee _liste){
            liste = _liste;
            cell = liste.head;
        }
        @Override
        public boolean hasNext() {

            return (cell != null);
        }

        @Override
        public Cellule next() {
            Cellule tmp = cell;
            cell = cell.getNextName();
            return tmp;
        }
    }


    @Override
    public Iterator<Cellule> iterator() {
        return new CellIterator(this);
    }

    @Override
    public void forEach(Consumer<? super Cellule> action) {
        Iterable.super.forEach(action);
    }

}
