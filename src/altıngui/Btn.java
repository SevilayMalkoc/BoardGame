/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altıngui;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 *
 * @author stara
 */
public class Btn extends JButton {
    private int row,col,count;
    private boolean gold,gizliAltın;

    public boolean isGizliAltın() {
        return gizliAltın;
    }

    public void setGizliAltın(boolean gizliAltın) {
        this.gizliAltın = gizliAltın;
    }

    public Btn(int row, int col) {
        this.row = row;
        this.col = col;
        this.count = count;
        this.gold = false;
        this.gizliAltın=gizliAltın;
      
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

  
    public boolean isGold() {
        return gold;
    }

    public void setGold(boolean gold) {
        this.gold = gold;
    }

   
    void setIconImage(Image image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setImage(ImageIcon goldImage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    


    
    
    
}
