package fr.aperikub.projetNFA006;


import javax.swing.*;
import java.io.*;

public class Client {

    public static final String ALICE_PATH = "/Alice.txt";
    public static final String TREASURE_PATH = "/treasure_island.txt";
    public static final String TARTUFFE_PATH = "/tartuffe.txt";
    private static final int DICTIONARY_SIZE =5390;
//  méthode main alternative sans interface graphique
/*    public static void main(String[] args) {

//        extrait les noms propres et leurs numéros de page présents dans Tartuffe pour des pages de 40 lignes

        analyseBook(TARTUFFE_PATH,40, null);

    }*/



    public static void analyseBook(final String path,int pageSize, final OnCompleteListener listener){

        final ListeChainee liste = new ListeChainee();

        new Thread(() -> {

            InputStream dictionaryStream = Client.class.getResourceAsStream("/dictionary.txt");

            InputStream bookStream = Client.class.getResourceAsStream(path);

            String[] names = null;
            String[][] wordsArray = null;
            int lineCount = 0;
            int pageCount = 0;
            int i = 0;
            try {
                names = new String[DICTIONARY_SIZE];

                String tmp = null;
                BufferedReader nameReader = new BufferedReader(new InputStreamReader(dictionaryStream));
                while((tmp = nameReader.readLine())!= null){
                    names[i] = tmp;
                    i++;
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(bookStream));
                while ((tmp = reader.readLine()) != null){
    //                System.out.println("reading : "+ tmp);
                    lineCount++;

                }
                System.out.println("book has "+ lineCount+ " lines.");
                pageCount = (lineCount / pageSize) + 1;
                System.out.println("book has " + pageCount + " pages.");
                wordsArray = new String[pageCount][pageSize];
                /* reset the bufferedReader */

                reader = new BufferedReader(new InputStreamReader(Client.class.getResourceAsStream(path)));

                lineCount = 1;
                pageCount = 1;
                while ((tmp = reader.readLine()) != null){
                    System.out.println("reading : "+ tmp);
                    if (pageSize == 1){
                        wordsArray[pageCount-1][lineCount-1] = tmp;

                        pageCount++;
                    }
                    else if(lineCount < pageSize){
                        System.out.println("reading line :" + lineCount);
                        wordsArray[pageCount-1][lineCount-1] = tmp;
                        lineCount++;
                    }
                    else {
                        System.out.println("new page : "+ pageCount);
                        lineCount = 1;
                        pageCount++;
                    }
                }

            } catch (IOException e) {
                listener.onComplete(null);
                JOptionPane.showMessageDialog(null,e.getMessage());
                e.printStackTrace();
            }
            System.out.println("pages : "+ pageCount);
            System.out.println("wordsArray : " + wordsArray.length);
            System.out.println("lineCount = "+lineCount);
//            for (String[] arr : wordsArray){
//                for (String s : arr){
//                    System.out.println(s);
//                }
//            }

            boolean newWord;
            int nameIndex = -1;
            for (String name : names) {
                newWord = true;
//                System.out.println("checking name : "+ name);
                for (int j = 0;j < wordsArray.length; j++) {
//                        System.out.println("checking new page : "+ j);
                    if (wordsArray[j] != null) {
                        for (int k = 0; k < wordsArray[j].length; k++) {
                            if (wordsArray[j][k] != null) {
                                String[] words = wordsArray[j][k].split("[ |,|.|\"]");
//                                System.out.println("words: "+ Arrays.toString(words));
                               for (String s : words) {
                                   //TODO : divider par mots et utiliser equalsIgnoreCase
                                   if (s.equalsIgnoreCase(name)) {
                                       System.out.println("name found : " + name + " at page " + j);
                                       if (newWord) {
                                           newWord = false;

                                           liste.add(new Cellule(name, (j + 1)));
                                           nameIndex = liste.getIndex(name);
                                           System.out.println("new word added at index :" + nameIndex+ " with name : "+ name);
                                       } else {
                                           liste.get(nameIndex).insertPage((j + 1));
//                                               System.out.println("name = "+ name);
//                                               System.out.println("new page added : " + (j+1)+ " for name at index :"+nameIndex+" = "+liste.get(nameIndex));

                                       }
                                   }
                                   else{
//                                       System.out.println(name+" != "+ s);
                                   }
                               }
                            }
                        }
                    }
                    else{
                        System.out.println("page "+ j + " is null");
                    }
                }
            }
            System.out.println("writing finished");
            System.out.println("pages : "+ pageCount);
            System.out.println("wordsArray : " + wordsArray.length);
            System.out.println("lineCount = "+lineCount);
            for (Cellule c : liste){
                System.out.println(c +" : "+ String.valueOf(c.getLength()) +" pages");
            }
            if (listener != null){ listener.onComplete(liste);}
        }).start();


    }
}
