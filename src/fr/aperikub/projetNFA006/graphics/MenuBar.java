/*
 * Nom de classe : MenuBar
 *
 * Description   : classe de la barre d'outil
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

import javax.swing.*;

public class MenuBar extends JMenuBar {

        private JMenu rollMenu;
        private JMenuItem quit;

        public MenuBar() {
            super();
            rollMenu = new JMenu("fichier");
            quit = new JMenuItem("quitter");
            rollMenu.setOpaque(true);
            rollMenu.add(quit);
            this.add(rollMenu);
        }

        public void mainMenu(){
            rollMenu.updateUI();
        }
        public void quitMain(){
            rollMenu.updateUI();
        }

    public JMenuItem getQuit() {
        return quit;
    }

    public JMenu getRollMenu() {
            return rollMenu;
        }


}
