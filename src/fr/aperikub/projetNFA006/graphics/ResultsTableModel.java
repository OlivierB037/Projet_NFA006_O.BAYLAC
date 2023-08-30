/*
 * Nom de classe : ResultsTableModel
 *
 * Description   : contient le modèle à appliquer à la JTable permettant d'afficher les utilisateurs
 *                 (inspirée du code permettant d'afficher des résultats de requêtes MySQL proposée par Dominique Liard sur koor.fr)
 *
 * Auteurs       : Steven Besnard, Agnes Laurencon, Olivier Baylac, Benjamin Launay
 *
 * Version       : 1.0
 *
 * Date          : 09/01/2022
 *
 * Copyright     : CC-BY-SA
 */

package fr.aperikub.projetNFA006.graphics;




import fr.aperikub.projetNFA006.Cellule;
import fr.aperikub.projetNFA006.ListeChainee;
import fr.aperikub.projetNFA006.ListeDePages;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;


public class ResultsTableModel extends AbstractTableModel {
    private final ArrayList<String> columnsNames = new ArrayList<>();
    private final ArrayList<String> columnsTypes = new ArrayList<>();
    private final ArrayList< ArrayList<String> > values = new ArrayList<>();

    public <T> ResultsTableModel(T resultArray ) throws IllegalArgumentException {

        if (resultArray == null){ // vide le JTable

        }
        else {
            if (resultArray instanceof ListeChainee) {
                System.out.println("result is a listeChainee");
                columnsNames.add("mot");
                columnsNames.add("nombre de pages");
                columnsTypes.add(String.class.getName());
                columnsTypes.add(String.class.getName());

                System.out.println("length of resultArray = " + ((ListeChainee) resultArray).length());
                ListeChainee liste = (ListeChainee) resultArray;
                for (Cellule p : liste) {
                    if (p != null) {
                        ArrayList<String> line = new ArrayList<>();
                        line.add(p.getNom());
                        line.add(String.valueOf(p.getLength()));


                        values.add(line);
                    }
                }
            }
            else if (resultArray instanceof Cellule){
                System.out.println("result is a cellule");
                Cellule cellule = (Cellule) resultArray;
                columnsNames.add(cellule.getNom());
                columnsTypes.add(String.class.getName());


                for (Integer p : cellule) {
                    if (p != null) {
                        ArrayList<String> line = new ArrayList<>();

                        line.add("Page : "+ String.valueOf(p));


                        values.add(line);
                    }
                }
            }
            else{
                throw new IllegalArgumentException("ResultsTableModel only takes  ListeChainee Classes");
            }
        }

    }

    @Override public Class<?> getColumnClass( int column ) {
        String type = this.columnsTypes.get( column );
        try {
            return Class.forName( type );
        } catch( Exception e ) {
            return String.class;
        }
    }


    @Override public String getColumnName(int i) {
        return columnsNames.get( i );
    }

    @Override public int getColumnCount() {
        return columnsNames.size();
    }

    @Override public int getRowCount() {
        return values.size();
    }

    @Override public Object getValueAt( int line, int column ) {
        return values.get( line ).get( column );
    }

}
