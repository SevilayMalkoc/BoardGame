package altıngui;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public final class OyunEkrani {

    JFrame frame;
    ShortestPath path = new ShortestPath();
    int altınSayısı;
    int gizliAltınSayısı;

    public OyunEkrani(int oyuntürü, int satir, int sutun, A aOyuncu, B bOyuncu, C cOyuncu, D dOyuncu) {
        frame = new JFrame("Altın Toplama Oyunu");
        frame.setSize(1200, 1200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int rowa, cola;

        if (oyuntürü == 0) {
            rowa = satir;
            cola = sutun;
            frame.setLayout(new GridLayout(rowa, cola));
            altınSayısı = (satir * sutun) * 20 / 100;
            gizliAltınSayısı = (altınSayısı * 10) / 100;

        } else {
            rowa = satir;
            cola = sutun;
            frame.setLayout(new GridLayout(rowa, cola));
            altınSayısı = (satir * sutun) * 20 / 100;
            gizliAltınSayısı = (altınSayısı * 10) / 100;
        }
        Btn[][] board = new Btn[rowa][cola];
        /* if(c==0)
        {
            frame.setLayout(new GridLayout(20,20));
        }
        else
        {
            frame.setLayout(new GridLayout(boyut,boyut));
        }*/

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                Btn b = new Btn(row, col);
                frame.add(b);
                board[row][col] = b;
            }
        }
        generateGold(rowa, board);
        print(board, aOyuncu, bOyuncu, cOyuncu, dOyuncu);
        //print2();
        frame.setVisible(true);
    }
    char[][] altınIndex = new char[20][20];
    Random random = new Random();
    int max = 4, min = 1;
    int range = max - min + 1;
    int[][] gizliAltınIndex;

    public void generateGold(int rowa, Btn[][] board) {
        gizliAltınIndex = new int[gizliAltınSayısı][gizliAltınSayısı];

        int i = 0;
        // System.out.println("    gizli altın"+gizliAltınSayısı);

        int leng = board.length - 1;
        while (i < altınSayısı - gizliAltınSayısı) {
            int randRow = (int) (Math.random() * board.length);
            int randCol = (int) (Math.random() * board[0].length);
            int rand = (int) (Math.random() * range) + min;
            board[randRow][randCol].setCount(rand * 5);
            if ((randRow != 0 || randCol != 0) && !(randRow == 0 && randCol == leng) && !(randRow == leng && randCol == 0) && !(randRow == leng && randCol == leng)) {
                while (board[randRow][randCol].isGold()) {
                    randRow = (int) (Math.random() * board.length);
                    randCol = (int) (Math.random() * board[0].length);
                    board[randRow][randCol].setCount(rand * 5);
                }
                board[randRow][randCol].setGold(true);
                //  System.out.println(randRow + " " + randCol);
                i++;
            }
        }
        i = 0;

        while (i < gizliAltınSayısı) {
            int randRow = (int) (Math.random() * board.length);
            int randCol = (int) (Math.random() * board[0].length);
            int rand = (int) (Math.random() * range) + min;
            board[randRow][randCol].setCount(rand * 5);
            if ((randRow != 0 || randCol != 0) && !(randRow == 0 && randCol == leng) && !(randRow == leng && randCol == 0) && !(randRow == leng && randCol == leng)) {
                while (board[randRow][randCol].isGold()) {
                    randRow = (int) (Math.random() * board.length);
                    randCol = (int) (Math.random() * board[0].length);
                    board[randRow][randCol].setCount(rand * 5);
                }
                board[randRow][randCol].setGold(true);
                board[randRow][randCol].setBackground(Color.yellow);
                System.out.println("Gizli Altın:" + randRow + " " + randCol);
                gizliAltınIndex[i][0] = randRow;
                gizliAltınIndex[i][1] = randCol;
                System.out.println(" gizli altın index   " + gizliAltınIndex[i][0] + "" + gizliAltınIndex[i][1]);

                //  System.out.println(randRow + " " + randCol);
                i++;
            }

        }
    }
    //C:\Users\stara\OneDrive\Belgeler\NetBeansProjects\projeDeneme\src\img

    public void print(Btn[][] board, A aOyuncu, B bOyuncu, C cOyuncu, D dOyuncu) {
        int leng = board.length - 1;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].isGold() && !(row == 0 && col == 0) && !(row == 0 && col == leng) && !(row == leng && col == 0) && !(row == leng && col == leng)) {
                    board[row][col].setText("X" + " " + board[row][col].getCount());
//board[row][col].setIcon(new ImageIcon("C:\\Users\\stara\\OneDrive\\Belgeler\\NetBeansProjects\\projeDeneme\\src\\img\\gold.png")); altın resmi
                    altınIndex[row][col] = 'X';
                    // System.out.println(board[row][col]);
                    System.out.println("altınIndex[][]:" + row + " " + col);
                    //System.out.println(board[row][col].getCount());
                } else {
                    board[row][col].setText(board[row][col].getCount() + "");
                    board[row][col].setEnabled(false);//butonları çalışmaz yapıyor
                    altınIndex[row][col] = '1';
                    // altınYerleri[row][col].setEnabled(false);
                }
            }//&&(row==0&&col==0)&&!(row==0&&col==leng)&&!(row==leng&&col==0)&&!(row==leng&&col==leng)
        }

        board[0][0].setText("A");
        board[0][leng].setText(("B"));
        board[leng][0].setText(("C"));
        board[leng][leng].setText(("D"));
        oyna(board, aOyuncu, bOyuncu, cOyuncu, dOyuncu);

        SonucEkrani sonucEkrani = new SonucEkrani();
        sonucEkrani.EkranaYazdir(aOyuncu, bOyuncu, cOyuncu, dOyuncu);
        sonucEkrani.setVisible(true);
    }

    public void oyna(Btn[][] board, A aOyuncu, B bOyuncu, C cOyuncu, D dOyuncu) {
        /*A aOyuncu = new A();
    B bOyuncu = new B();
    C cOyuncu = new C();
    D dOyuncu = new D();*/
        BufferedWriter writer = null;
        try {
            boolean secimAsamasi;
            int sonucA[], sonucB[], sonucC[], sonucD[];
            //System.out.println(board.length);
            //tahtadaki altın devam edecek döngü olmalı
            // for (int i = 0; i < altınSayısı; i++) {
            System.out.println("Altın Sayısı" + altınSayısı);
            // TODO code application logic here
            File f = new File("C:/Users/stara/OneDrive/Belgeler/NetBeansProjects/altıngui/cikti.txt");
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
            writer.append("ALTIN TOPLAMA OYUNU");
            writer.newLine();
            sonucA = aOyuncu.adımSecim(board, altınIndex,gizliAltınIndex, 0, 0, aOyuncu.hamle, aOyuncu.hedefBelirleme);
            if (altınIndex[sonucA[1]][sonucA[2]] == 'X') {
                altınIndex[sonucA[1]][sonucA[2]] = '1';
                altınSayısı--;
                writer.append("A->" + "[" + sonucA[1] + "]" + "[" + sonucA[2] + "]");
                writer.newLine();
                System.out.println("A altını aldı " + sonucA[1] + " " + sonucA[2]);
                //altın olmayan yerlere 1 atadık A olan altını aldı.

            }
            System.out.println("sonucA" + sonucA[1] + sonucA[2]);
            sonucB = bOyuncu.adımSecim(board, altınIndex,gizliAltınIndex, 0, board[0].length - 1, bOyuncu.hamle, bOyuncu.hedefBelirleme);
            if (altınIndex[sonucB[1]][sonucB[2]] == 'X') {
                altınIndex[sonucB[1]][sonucB[2]] = '1';
                altınSayısı--;
                System.out.println("B altını aldı " + sonucB[1] + " " + sonucB[2]);
                writer.append("B->" + "[" + sonucB[1] + "]" + "[" + sonucB[2] + "]");
                writer.newLine();
                //altın olmayan yerlere 1 atadık B olan altını aldı.

            }
            sonucC = cOyuncu.adımSecim(board, altınIndex, board.length - 1, 0, cOyuncu.hamle, cOyuncu.hedefBelirleme);
            if (altınIndex[sonucC[1]][sonucC[2]] == 'X') {
                altınIndex[sonucC[1]][sonucC[2]] = '1';
                altınSayısı--;
                System.out.println("C altını aldı " + sonucC[1] + " " + sonucC[2]);
                writer.append("C->" + "[" + sonucC[1] + "]" + "[" + sonucC[2] + "]");
                writer.newLine();
                //altın olmayan yerlere 1 atadık C olan altını aldı.

            }
            sonucD = dOyuncu.adımSecim(board, altınIndex,gizliAltınIndex, board.length - 1, board[0].length - 1, dOyuncu.hamle, dOyuncu.hedefBelirleme);
            if (altınIndex[sonucD[1]][sonucD[2]] == 'X') {
                altınIndex[sonucD[1]][sonucD[2]] = '1';
                altınSayısı--;
                System.out.println("D altını aldı " + sonucD[1] + " " + sonucD[2]);
                writer.append("D->" + "[" + sonucD[1] + "]" + "[" + sonucD[2] + "]");
                writer.newLine();
                //altın olmayan yerlere 1 atadık D olan altını aldı.

            }
            writer.newLine();
            int i = 0;
            while (i < altınSayısı) {
                System.out.println("Altın Sayısı" + altınSayısı);
                if (aOyuncu.kasadakiAltın > 0) {
                    sonucA = aOyuncu.adımSecim(board, altınIndex,gizliAltınIndex, sonucA[1], sonucA[2], aOyuncu.hamle, aOyuncu.hedefBelirleme);
                    if (altınIndex[sonucA[1]][sonucA[2]] == 'X') {
                        altınIndex[sonucA[1]][sonucA[2]] = '1';
                        altınSayısı--;
                        System.out.println("A altını aldı " + sonucA[1] + " " + sonucA[2]);
                        writer.append("A->" + "[" + sonucA[1] + "]" + "[" + sonucA[2] + "]");
                        writer.newLine();
                        //  board[sonucA[1]][sonucA[2]].setText("<html>"+board[sonucA[1]][sonucA[2]].getText()+"<br>"+"1"+"</html>");
                        //altın olmayan yerlere 1 atadık A olan altını aldı.

                    }
                }
                if (bOyuncu.kasadakiAltın > 0) {
                    sonucB = bOyuncu.adımSecim(board, altınIndex,gizliAltınIndex, sonucB[1], sonucB[2], bOyuncu.hamle, bOyuncu.hedefBelirleme);
                    if (altınIndex[sonucB[1]][sonucB[2]] == 'X') {
                        altınIndex[sonucB[1]][sonucB[2]] = '1';
                        altınSayısı--;
                        System.out.println("B altını aldı " + sonucB[1] + " " + sonucB[2]);
                        writer.append("B->" + "[" + sonucB[1] + "]" + "[" + sonucB[2] + "]");
                        writer.newLine();
                        //altın olmayan yerlere 1 atadık B olan altını aldı.

                    }
                }
                if (cOyuncu.kasadakiAltın > 0) {
                    sonucC = cOyuncu.adımSecim(board, altınIndex, sonucC[1], sonucC[2], cOyuncu.hamle, cOyuncu.hedefBelirleme);
                    if (altınIndex[sonucC[1]][sonucC[2]] == 'X') {
                        altınIndex[sonucC[1]][sonucC[2]] = '1';
                        altınSayısı--;
                        System.out.println("C altını aldı " + sonucC[1] + " " + sonucC[2]);
                        writer.append("C->" + "[" + sonucC[1] + "]" + "[" + sonucC[2] + "]");
                        writer.newLine();
                        //altın olmayan yerlere 1 atadık C olan altını aldı.

                    }
                }
                if (dOyuncu.kasadakiAltın > 0) {
                    sonucD = dOyuncu.adımSecim(board, altınIndex,gizliAltınIndex, sonucD[1], sonucD[2], dOyuncu.hamle, dOyuncu.hedefBelirleme);
                    if (altınIndex[sonucD[1]][sonucD[2]] == 'X') {
                        altınIndex[sonucD[1]][sonucD[2]] = '1';
                        altınSayısı--;
                        System.out.println("D altını aldı " + sonucD[1] + " " + sonucD[2]);
                        writer.append("D->" + "[" + sonucD[1] + "]" + "[" + sonucD[2] + "]");
                        writer.newLine();
                        //altın olmayan yerlere 1 atadık D olan altını aldı.

                    }
                }
                writer.newLine();
                i++;
            }
            System.out.println("A toplam adımsayısı: " + aOyuncu.adımSayısı + " harcanan altın miktarı: " + aOyuncu.harcananAltınMiktarı + " toplanan altın miktarı: " + aOyuncu.toplananAltınMiktarı + " Kasadaki Altın:" + aOyuncu.kasadakiAltın);
            System.out.println("B toplam adımsayısı: " + bOyuncu.adımSayısı + " harcanan altın miktarı: " + bOyuncu.harcananAltınMiktarı + " toplanan altın miktarı: " + bOyuncu.toplananAltınMiktarı + " Kasadaki Altın:" + bOyuncu.kasadakiAltın);
            System.out.println("C toplam adımsayısı: " + cOyuncu.adımSayısı + " harcanan altın miktarı: " + cOyuncu.harcananAltınMiktarı + " toplanan altın miktarı: " + cOyuncu.toplananAltınMiktarı + " Kasadaki Altın:" + cOyuncu.kasadakiAltın);
            System.out.println("D toplam adımsayısı: " + dOyuncu.adımSayısı + " harcanan altın miktarı: " + dOyuncu.harcananAltınMiktarı + " toplanan altın miktarı: " + dOyuncu.toplananAltınMiktarı + " Kasadaki Altın:" + dOyuncu.kasadakiAltın);
            //altın toplandıysa altınSayısını-1
            //}
            System.out.println("OYUN BİTTİ");
            writer.append("A toplam adımsayısı: " + aOyuncu.adımSayısı + " harcanan altın miktarı: " + aOyuncu.harcananAltınMiktarı + " toplanan altın miktarı: " + aOyuncu.toplananAltınMiktarı + " Kasadaki Altın:" + aOyuncu.kasadakiAltın);
            writer.newLine();
            writer.append("B toplam adımsayısı: " + bOyuncu.adımSayısı + " harcanan altın miktarı: " + bOyuncu.harcananAltınMiktarı + " toplanan altın miktarı: " + bOyuncu.toplananAltınMiktarı + " Kasadaki Altın:" + bOyuncu.kasadakiAltın);
            writer.newLine();
            writer.append("C toplam adımsayısı: " + cOyuncu.adımSayısı + " harcanan altın miktarı: " + cOyuncu.harcananAltınMiktarı + " toplanan altın miktarı: " + cOyuncu.toplananAltınMiktarı + " Kasadaki Altın:" + cOyuncu.kasadakiAltın);
            writer.newLine();
            writer.append("D toplam adımsayısı: " + dOyuncu.adımSayısı + " harcanan altın miktarı: " + dOyuncu.harcananAltınMiktarı + " toplanan altın miktarı: " + dOyuncu.toplananAltınMiktarı + " Kasadaki Altın:" + dOyuncu.kasadakiAltın);
            //DosyayaYazdir("A->"+"["+sonucA[1]+"]"+"["+sonucA[2]+"]");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OyunEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OyunEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(OyunEkrani.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /* public void print2(Btn[][] board) {

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                //System.out.print(altınIndex[row][col]);
                if (altınIndex[row][col] == 'X') {
                    System.out.println("altınIndex[][]:" + row + " " + col);
                }
            }
            //System.out.println();

        }

    }*/

}
