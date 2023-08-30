package fr.aperikub.projetNFA006.graphics;



import fr.aperikub.projetNFA006.graphics.menus.MenuPrincipal;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class GraphicClient {

    public static void main(String[] args)  {



        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel()); // application du "skin" Nimbus à l'interface graphique
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        MyWindow myWindow = null; // classe héritant de JFrame, la fenêtre de l'UI, qui contient tous les éléments graphiques affichés
        try {
            myWindow = new MyWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MenuPrincipal menuPrincipal = new MenuPrincipal(myWindow);
        menuPrincipal.init(); //initialisation du menu principal

        /*ouverture du menu principal*/
        myWindow.setVisible(true);
        myWindow.pack();

        myWindow.setContentPane(menuPrincipal.getMenuPrincipalPane()); // aplication du menu principal dans la fenêtre
        myWindow.setMinimumSize(new Dimension(600,600));
    }
}
