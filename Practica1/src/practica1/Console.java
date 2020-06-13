/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Carlos
 */
public class Console implements Observer {
    
     
    private Line linia;
    // private Keys keys;
   
    
    public Console (Line value) throws IOException{
        this.linia = value;   
    }
    
    public void update(Observable obs, Object obj){
        int var = (int)obj;
        switch(var){
            case Keys.SEC_CHARACTER:
                boolean aux = this.linia.getInsert();
                this.printChar(this.linia.getLastChar(), aux);
            break;
            
            case Keys.SEC_RIGHT:
                System.out.print(Keys.ANSI_RIGHT);
            break;
            
            case Keys.SEC_LEFT:
                System.out.print(Keys.ANSI_LEFT);
            break;
            
            case Keys.SEC_HOME:
                this.updateCursor(this.linia.getPos());
                //System.out.print(Keys.ANSI_HOME);
                
            break;
            
            case Keys.SEC_FIN:
                this.updateCursor(this.linia.getPos());
                System.out.print(Keys.ANSI_END);
            break;
            
            case Keys.BACKSPACE:
                System.out.print(Keys.ANSI_BACKSPACE);
            break;
            
            case Keys.SEC_DELETE:
                System.out.print(Keys.ANSI_DELETE);
            break;
            
            // case Keys.ENTER:
            //     this.unsetRaw();
            // break;
            
            case Keys.SEC_INSERT:
                System.out.print(Keys.ANSI_INSERT);
            break;
            default:
            break;
        }
    }
    
    public void insert(){
        System.out.print(Keys.ANSI_INSERT);
    }
    
    public void printChar(char a, boolean insert){
        if(insert){
            System.out.print(a);
        }else{
            System.out.print(Keys.ANSI_BLANK);
            System.out.print(a);
        }
    }

    public void updateCursor(int cursorPos){
        int aux = cursorPos + 1;
        System.out.print("\033["+aux+"G");
    }
}
