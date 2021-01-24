package altıngui;

import java.awt.Color;

public class C extends Oyuncu {

    public C(String oyuncuAdi, int adımSayısı, int toplamAdımSayısı, int harcananAltınMiktarı, int toplananAltınMiktarı) {
        super(oyuncuAdi, adımSayısı, toplamAdımSayısı, harcananAltınMiktarı, toplananAltınMiktarı);
    }

    public C() {
    }
    ShortestPath path = new ShortestPath();
    int hedefSayısı = 1, hamle, hedefBelirleme;
    //hamle ve hedefBelirleme maliyetleri

    public int[] adımSecim(Btn[][] board, char[][] altınIndex, int x, int y, int hamle, int hedefBelirleme) {

        int yol[] = path.pathExists(altınIndex, x, y);
        adımSayısı = yol[0] + adımSayısı;
        System.out.println(yol[0]);
        if (yol[0] <= 3) {
            // System.out.println("İlerle ve Altını topla");
            board[yol[1]][yol[2]].setBackground(Color.GREEN);
            board[yol[1]][yol[2]].setText("<html>" + board[yol[1]][yol[2]].getText() + "<br>" + "C" + "</html>");
            harcananAltınMiktarı = yol[0] * 5 + hedefBelirleme + harcananAltınMiktarı;
            toplananAltınMiktarı = board[yol[1]][yol[2]].getCount() + toplananAltınMiktarı;
            kasadakiAltın = kasadakiAltın - harcananAltınMiktarı + toplananAltınMiktarı;
            return yol;
        } else {
            // System.out.println("yolgüzergahında üc adım ilerle");

            if (yol[0] % 3 == 0) {
                board[yol[1]][yol[2]].setBackground(Color.GREEN);
                board[yol[1]][yol[2]].setText("<html>" + board[yol[1]][yol[2]].getText() + "<br>" + "C" + "</html>");
                hedefSayısı = yol[0] / 3;
                yol[0] = 3;
                harcananAltınMiktarı = yol[0] * 5 + hedefBelirleme + harcananAltınMiktarı;
                toplananAltınMiktarı = board[yol[1]][yol[2]].getCount() + toplananAltınMiktarı;
                kasadakiAltın = kasadakiAltın - harcananAltınMiktarı + toplananAltınMiktarı;

            } else {
                board[yol[1]][yol[2]].setBackground(Color.GREEN);
                board[yol[1]][yol[2]].setText("<html>" + board[yol[1]][yol[2]].getText() + "<br>" + "C" + "</html>");
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
