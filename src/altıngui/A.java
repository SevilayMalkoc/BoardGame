package altıngui;

import java.awt.Color;
import java.util.Arrays;

public class A extends Oyuncu {

    public A(String oyuncuAdi, int adımSayısı, int toplamAdımSayısı, int harcananAltınMiktarı, int toplananAltınMiktarı) {
        super(oyuncuAdi, adımSayısı, toplamAdımSayısı, harcananAltınMiktarı, toplananAltınMiktarı);
    }
    ShortestPath path = new ShortestPath();

    public A() {
    }
    int hedefSayısı = 1, hamle, hedefBelirleme;
    //hamle ve hedefBelirleme maliyetleri

    public int[] adımSecim(Btn[][] board, char[][] altınIndex, int[][] gizliAltınIndex, int x, int y, int hamle, int hedefBelirleme) {
        
        char[][] yeniDizi = null;
       yeniDizi = altınIndex.clone();
        for (int[] gizliAltınIndex1 : gizliAltınIndex) {
            yeniDizi[gizliAltınIndex1[0]][gizliAltınIndex1[1]] = '1';
        }
        int yol[] = path.pathExists(yeniDizi, x, y);
        adımSayısı = yol[0] + adımSayısı;
        System.out.println(yol[0]);
        if (yol[0] <= 3) {
            board[yol[1]][yol[2]].setBackground(Color.PINK);
            System.out.println("İlerle ve Altını topla");
            board[yol[1]][yol[2]].setText("<html>" + board[yol[1]][yol[2]].getText() + "<br>" + "A" + "</html>");
            // https://stackoverflow.com/questions/15746970/how-to-add-a-multi-line-text-to-a-jbutton-with-the-line-unknown-dynamically
            harcananAltınMiktarı = yol[0] * 5 + hedefBelirleme + harcananAltınMiktarı;
            toplananAltınMiktarı = board[yol[1]][yol[2]].getCount() + toplananAltınMiktarı;
            kasadakiAltın = kasadakiAltın - harcananAltınMiktarı + toplananAltınMiktarı;
            // System.out.println(skor);
            // board[row][col].setText(board[row][col].getCount() + "");
            return yol;
        } else {
            System.out.println("yolgüzergahında üc adım ilerle");
            if (yol[0] % 3 == 0) {
                board[yol[1]][yol[2]].setBackground(Color.PINK);
                board[yol[1]][yol[2]].setText("<html>" + board[yol[1]][yol[2]].getText() + "<br>" + "A" + "</html>");
                hedefSayısı = yol[0] / 3;
                yol[0] = 3;
                harcananAltınMiktarı = yol[0] * 5 + hedefBelirleme + harcananAltınMiktarı;
                toplananAltınMiktarı = board[yol[1]][yol[2]].getCount() + toplananAltınMiktarı;
                kasadakiAltın = kasadakiAltın - harcananAltınMiktarı + toplananAltınMiktarı;

            } else {
                board[yol[1]][yol[2]].setBackground(Color.PINK);
                board[yol[1]][yol[2]].setText("<html>" + board[yol[1]][yol[2]].getText() + "<br>" + "A" + "</html>");
                hedefSayısı = (yol[0] / 3) + 1;
                yol[0] = 3;
                harcananAltınMiktarı = yol[0] * 5 + hedefBelirleme + harcananAltınMiktarı;
                toplananAltınMiktarı = board[yol[1]][yol[2]].getCount() + toplananAltınMiktarı;
                kasadakiAltın = kasadakiAltın - harcananAltınMiktarı + toplananAltınMiktarı;
            }
            return yol;
        }

    }
}
 /* for (int i = 0; i < altınIndex.length; i++) {
            for (int j=0;j<altınIndex.length;j++) {
                {
                    yeniDizi[i][j]= Integer.parseInt(altınIndex[i][j]);
                            
                }
            }
        }*/