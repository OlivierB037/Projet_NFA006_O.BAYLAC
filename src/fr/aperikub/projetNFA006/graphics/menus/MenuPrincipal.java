/*
 * Nom de classe : MenuPrincipal
 *
 * Description   : pilote le menu principal
 *
 * Auteurs       : Steven Besnard, Agnes Laurencon, Olivier Baylac, Benjamin Launay
 *
 * Version       : 1.0
 *
 * Date          : 09/01/2022
 *
 * Copyright     : CC-BY-SA
 */

package fr.aperikub.projetNFA006.graphics.menus;


import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import fr.aperikub.projetNFA006.*;
import fr.aperikub.projetNFA006.graphics.MenuBar;
import fr.aperikub.projetNFA006.graphics.MyWindow;
import fr.aperikub.projetNFA006.graphics.ResultsTableModel;


import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

public class MenuPrincipal implements OnCompleteListener { // implémente ActionListener pour être à l'écoute de certains évènements
    private JPanel menuPrincipalPane;
    private JTextArea title;
    private JButton validerButton;

    private JButton quitterButton;
    private JComboBox livreComboBox;
    private JTable resultsTable;
    private JButton backButton;
    private JComboBox comboBox1;
    private String bookPath;
    private int activeItem;
    MenuBar menuBar;
    MyWindow myWindow;
    private static ListeChainee liste;

    public MenuPrincipal(MyWindow myWindow) {
        this.myWindow = myWindow;

    }


    /*
     * initialisation du menu principal*/
    public void init() {


        menuBar = new MenuBar();
        myWindow.setJMenuBar(menuBar);
        myWindow.setVisible(true);
        myWindow.pack();
        myWindow.setVisible(true);
        myWindow.pack();
        myWindow.setContentPane(menuPrincipalPane);
        myWindow.setMinimumSize(new Dimension(600, 600));

        quitterButton.addActionListener(e -> System.exit(0));
        menuBar.getQuit().addActionListener(e -> System.exit(0));

        validerButton.addActionListener(e -> {
            /*bloquage du bouton valider pendant le chargement*/
            validerButton.setEnabled(false);
            validerButton.setText("Chargement en cours...");
            resultsTable.setModel(new ResultsTableModel(null));

            /*débloquage du bouton en sélectionnant un autre livre*/
            activeItem = livreComboBox.getSelectedIndex();
            livreComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    System.out.println("itemStateChanged called");
                    System.out.println("item : " + e.getItem().toString());
                    if (livreComboBox.getSelectedIndex() != activeItem) {
                        validerButton.setEnabled(true);
                    }
                }
            });
            try {
                switch (activeItem) {
                    case 0 -> bookPath = Client.ALICE_PATH;
                    case 1 -> bookPath = Client.TREASURE_PATH;
                    case 2 -> bookPath = Client.TARTUFFE_PATH;
                }
                int pageSize = (comboBox1.getSelectedIndex() + 1);

                Client.analyseBook(bookPath, pageSize, this);


            } catch (Exception err) {
                JOptionPane.showMessageDialog(myWindow, err.getMessage());
            }
        });


    }


    public JPanel getMenuPrincipalPane() {
        return menuPrincipalPane;
    }


    public JTextArea getTitle() {
        return title;
    }

    public JButton getModifierButton() {
        return validerButton;
    }


    public JButton getQuitterButton() {
        return quitterButton;
    }


    /* callback de fin de la recherche */
    @Override
    public void onComplete(ListeChainee _liste) {
        liste = _liste;
        if (liste != null && !liste.isEmpty()) {
            validerButton.setText("Valider");
            ResultsTableModel mainModel = new ResultsTableModel(liste);
            resultsTable.setModel(mainModel);
            resultsTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);


                    System.out.println("mouse clicked on result");
                    try {
                        if (resultsTable.getSelectedRow() != -1) {
                            int row = resultsTable.getSelectedRow();
                            Cellule name = liste.get(row);
                            if (name != null) {
                                resultsTable.setModel(new ResultsTableModel(name));
                                menuPrincipalPane.updateUI();
                                backButton.setEnabled(true);
                                if (backButton.getActionListeners().length == 0) {
                                    System.out.println("back button doesn't have listeners");
                                    backButton.addActionListener(item -> {
                                        resultsTable.setModel(mainModel);
                                        backButton.setEnabled(false);
                                        menuPrincipalPane.updateUI();
                                    });
                                } else {
                                    System.out.println("back button already have a listener");
                                }
                            } else System.out.println("selected row produced null cellule");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            });
            menuPrincipalPane.updateUI();
        } else {
            System.out.println("no result");
            validerButton.setText("Valider");
            validerButton.setEnabled(true);
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        menuPrincipalPane = new JPanel();
        menuPrincipalPane.setLayout(new GridLayoutManager(10, 6, new Insets(0, 0, 0, 0), -1, -1));
        menuPrincipalPane.setMaximumSize(new Dimension(1000, 1000));
        menuPrincipalPane.setMinimumSize(new Dimension(500, 500));
        menuPrincipalPane.setPreferredSize(new Dimension(700, 500));
        final Spacer spacer1 = new Spacer();
        menuPrincipalPane.add(spacer1, new GridConstraints(9, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        menuPrincipalPane.add(spacer2, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        title = new JTextArea();
        title.setEditable(false);
        Font titleFont = this.$$$getFont$$$("Comic Sans MS", -1, 42, title.getFont());
        if (titleFont != null) title.setFont(titleFont);
        title.setText("Annuaire des noms propres");
        menuPrincipalPane.add(title, new GridConstraints(0, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        quitterButton = new JButton();
        quitterButton.setFocusable(false);
        Font quitterButtonFont = this.$$$getFont$$$("Comic Sans MS", -1, 20, quitterButton.getFont());
        if (quitterButtonFont != null) quitterButton.setFont(quitterButtonFont);
        quitterButton.setRolloverEnabled(false);
        quitterButton.setText("Quitter");
        menuPrincipalPane.add(quitterButton, new GridConstraints(8, 1, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        menuPrincipalPane.add(spacer3, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        menuPrincipalPane.add(spacer4, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        menuPrincipalPane.add(spacer5, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        livreComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Alice au pays des merveilles");
        defaultComboBoxModel1.addElement("L'île au trésor");
        defaultComboBoxModel1.addElement("Tartuffe");
        livreComboBox.setModel(defaultComboBoxModel1);
        menuPrincipalPane.add(livreComboBox, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        menuPrincipalPane.add(scrollPane1, new GridConstraints(6, 1, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        resultsTable = new JTable();
        scrollPane1.setViewportView(resultsTable);
        validerButton = new JButton();
        validerButton.setFocusable(false);
        Font validerButtonFont = this.$$$getFont$$$("Comic Sans MS", -1, 20, validerButton.getFont());
        if (validerButtonFont != null) validerButton.setFont(validerButtonFont);
        validerButton.setRolloverEnabled(false);
        validerButton.setText("Analyser le livre");
        menuPrincipalPane.add(validerButton, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        menuPrincipalPane.add(spacer6, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        backButton = new JButton();
        backButton.setEnabled(false);
        backButton.setText("Retour");
        menuPrincipalPane.add(backButton, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox1 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("1");
        defaultComboBoxModel2.addElement("2");
        defaultComboBoxModel2.addElement("3");
        defaultComboBoxModel2.addElement("4");
        defaultComboBoxModel2.addElement("5");
        defaultComboBoxModel2.addElement("6");
        defaultComboBoxModel2.addElement("7");
        defaultComboBoxModel2.addElement("8");
        defaultComboBoxModel2.addElement("9");
        defaultComboBoxModel2.addElement("10");
        defaultComboBoxModel2.addElement("11");
        defaultComboBoxModel2.addElement("12");
        defaultComboBoxModel2.addElement("13");
        defaultComboBoxModel2.addElement("14");
        defaultComboBoxModel2.addElement("15");
        defaultComboBoxModel2.addElement("16");
        defaultComboBoxModel2.addElement("17");
        defaultComboBoxModel2.addElement("18");
        defaultComboBoxModel2.addElement("19");
        defaultComboBoxModel2.addElement("20");
        defaultComboBoxModel2.addElement("21");
        defaultComboBoxModel2.addElement("22");
        defaultComboBoxModel2.addElement("23");
        defaultComboBoxModel2.addElement("24");
        defaultComboBoxModel2.addElement("25");
        defaultComboBoxModel2.addElement("26");
        defaultComboBoxModel2.addElement("27");
        defaultComboBoxModel2.addElement("28");
        defaultComboBoxModel2.addElement("29");
        defaultComboBoxModel2.addElement("30");
        defaultComboBoxModel2.addElement("31");
        defaultComboBoxModel2.addElement("32");
        defaultComboBoxModel2.addElement("33");
        defaultComboBoxModel2.addElement("34");
        defaultComboBoxModel2.addElement("35");
        defaultComboBoxModel2.addElement("36");
        defaultComboBoxModel2.addElement("37");
        defaultComboBoxModel2.addElement("38");
        defaultComboBoxModel2.addElement("39");
        defaultComboBoxModel2.addElement("40");
        defaultComboBoxModel2.addElement("40");
        defaultComboBoxModel2.addElement("42");
        defaultComboBoxModel2.addElement("43");
        defaultComboBoxModel2.addElement("44");
        defaultComboBoxModel2.addElement("45");
        defaultComboBoxModel2.addElement("46");
        defaultComboBoxModel2.addElement("47");
        defaultComboBoxModel2.addElement("48");
        defaultComboBoxModel2.addElement("49");
        defaultComboBoxModel2.addElement("50");
        comboBox1.setModel(defaultComboBoxModel2);
        menuPrincipalPane.add(comboBox1, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Lignes par page :");
        menuPrincipalPane.add(label1, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return menuPrincipalPane;
    }
}
