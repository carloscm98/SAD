package practica1;


import java.util.ArrayList;
import java.util.Observable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carlos
 */
public class Line extends Observable{
    Boolean insert;
    int pos;
    char lastChar;
    ArrayList<Character> lineBuffer;
    
    public Line(){
        this.insert = false;
        this.pos = 0;
        this.lineBuffer = new ArrayList<>();
    }

    public char getLastChar(){
        return this.lastChar;
    }
    
    public int getPos(){
        return this.pos;
    }
    
    public void setPos(int pos) {
        this.pos = pos;
    }

    public boolean getInsert() {
        return this.insert;
    }

    public void setInster() {
        this.insert = !this.insert;
        this.setChanged();
        this.notifyObservers(Keys.SEC_INSERT);
    }

    public void right() {
        if (this.pos < this.lineBuffer.size()) {
            this.pos++;
        }
        this.setChanged();
        this.notifyObservers(Keys.SEC_RIGHT);
    }

    public void left() {
        if (this.pos > 0) {
            this.pos--;
        }
        this.setChanged();
        this.notifyObservers(Keys.SEC_LEFT);
    }

    public void addCharacter(char caracter) {
        if (this.insert) {
            if (this.pos < this.lineBuffer.size()) {
                this.lineBuffer.set(this.pos, caracter);
            } else {
                this.lineBuffer.add(this.pos, caracter);
            }
        } else {
            this.lineBuffer.add(this.pos, caracter);
        }
        this.pos++;
        this.lastChar = caracter;
        this.setChanged();
        this.notifyObservers(Keys.SEC_CHARACTER);
    }

    public void home() {
        this.pos = 0;
        this.setChanged();
        this.notifyObservers(Keys.SEC_HOME);
    }

    public void end() {
        this.pos = this.lineBuffer.size();
        this.setChanged();
        this.notifyObservers(Keys.SEC_FIN);
    }

    public void delete() {
        if (this.pos < this.lineBuffer.size()) {
            this.lineBuffer.remove(this.pos);
        }
        this.setChanged();
        this.notifyObservers(Keys.SEC_DELETE);
    }

    public void backspace() {
        if (this.pos > 0) {
            this.lineBuffer.remove(this.pos - 1);
            this.left();
        }
        this.setChanged();
        this.notifyObservers(Keys.BACKSPACE);
    }

    public String toString() {
        String str = "";
        for (Character c : lineBuffer) {
            str = str + c;
        }
        return str;
    }
}
