package practica1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * þ
 *
 * @author Carlos
 */
public class EditableBufferedReader extends BufferedReader {

    Line linia;
  

    public EditableBufferedReader(InputStreamReader in) {
        super(in);
        this.linia = new Line();
      
    }

    public void setRaw() throws IOException {
        String[] cmd = { "/bin/sh", "-c", "stty -echo raw </dev/tty" };
        try {
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(EditableBufferedReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void unsetRaw() throws IOException {
        String[] cmd = { "/bin/sh", "-c", "stty sane </dev/tty" };
        try {
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(EditableBufferedReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int read() throws IOException {
        int caracter = 0;
        caracter = super.read();
        if (caracter == Keys.ESC) {
            caracter = super.read();
            if (caracter == Keys.CORCHETE) {
                caracter = super.read();
                switch (caracter) {
                    case Keys.RIGHT:
                        return Keys.SEC_RIGHT;
                    case Keys.LEFT:
                        return Keys.SEC_LEFT;
                    case Keys.HOME:
                        return Keys.SEC_HOME;
                    case Keys.END:
                        return Keys.SEC_FIN;
                    case Keys.INSERT:
                        caracter = super.read();
                        if (caracter == Keys.TILDE) {
                            return Keys.SEC_INSERT;
                        }
                        return -1;
                    case Keys.DELETE:
                        caracter = this.read();
                        if (caracter == Keys.TILDE) {
                            return Keys.SEC_DELETE;
                        }
                        return -1;
                    default:
                        return -1;
                }
            }
        } else if (caracter == Keys.BACKSPACE) {
            return Keys.BACKSPACE;
        } else {
            return caracter;
        }
        return -1;
    }

    @Override
    public String readLine() throws IOException{
        int caracter = 0;
        this.setRaw();
        while((caracter = this.read()) != Keys.ENTER){
            switch(caracter){
                case Keys.SEC_RIGHT :
                    this.linia.right();
                    break;
                case Keys.SEC_LEFT :
                    this.linia.left();
                    break;
                case Keys.SEC_INSERT :
                    this.linia.setInster();
                    break;
                case Keys.SEC_DELETE :
                    this.linia.delete();
                    break;
                case Keys.SEC_HOME :
                    this.linia.home();
                    break;
                case Keys.SEC_FIN :
                    this.linia.end();
                    break;
                case Keys.BACKSPACE :
                    this.linia.backspace();
                    break;
                default :
                    this.linia.addCharacter((char) caracter);
            }
        }
        this.unsetRaw();
        String tmp = this.linia.toString();
        return tmp;
    }
}
