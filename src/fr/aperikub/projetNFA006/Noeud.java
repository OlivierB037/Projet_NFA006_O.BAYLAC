package fr.aperikub.projetNFA006;

public class Noeud<T> {
    private T val;
    private Noeud<T> next;
    private Noeud<T> previous;
    public Noeud(T _val){
        val = _val;
        next = null;
        previous = null;
    }
    public Noeud(T _val,Noeud<T> _previous, Noeud<T> _next){
        val = _val;
        next = _next;
//        next.setPrevious(this);
        previous = _previous;
//        previous.setNext(this);
    }
    public Noeud(T _val,Noeud<T> _next){
        val = _val;
        previous = null;
        next = _next;
    }

    public T getVal(){
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public Noeud<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Noeud<T> previous) {
        this.previous = previous;
    }

    public Noeud<T> getNext() {
        return next;
    }
    public void setNext(Noeud<T> _next){
        next = _next;
    }
}
