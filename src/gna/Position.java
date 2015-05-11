package gna;

import java.util.IllegalFormatCodePointException;

/**
 * Created by covert on 08/05/15.
 */
public class Position extends Object{
    private int iX;
    private int iY;

    public Position(int iX, int iY){
        this.iX = iX;
        this.iY = iY;
    }

    public int getX(){
        return iX;
    }
    public void setX(int iX) throws IllegalArgumentException {
        if (iX < 0) throw new IllegalArgumentException();
        this.iX = iX;
    }

    public int getY(){
        return iY;
    }
    public void setY(int iY) throws IllegalArgumentException {
        if(iY < 0) throw new IllegalArgumentException();
        this.iY = iY;
    }

    public boolean isAdjacentTo(Position pOther){
        if (Math.abs(pOther.getX() - iX) != 1)
            return false;
        if (Math.abs(pOther.getY() - iY) != 1)
            return false;

        return true;
    }

    @Override
    public int hashCode(){
        return -1;
    }

    @Override
    public boolean equals(Position pObject){

    }
}
