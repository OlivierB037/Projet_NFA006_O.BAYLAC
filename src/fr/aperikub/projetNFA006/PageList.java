package fr.aperikub.projetNFA006;

public interface PageList {
    public ListeDePages createPage(int numeroPage);
    public boolean insertPage( int numeroPage);
    public void showPages();
    public boolean removePage(int numeroDePage);
    public ListeDePages getPage(int numeroDePage);


}
