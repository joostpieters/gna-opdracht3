package gna;

import java.util.ArrayList;
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

    public ArrayList<Position> fncGetNeighbours(int iMaxX, int iMaxY){
        ArrayList<Position> apPos = new ArrayList<>();
        if(iX + 1 < 0 && iX + 1 >= iMaxX)
            apPos.add(new Position(iX + 1, iY));
        if(iX - 1 < 0 && iX - 1 >= iMaxX)
            apPos.add(new Position(iX - 1, iY));
        if(iY -1 < 0 && iY -1 >= iMaxY)
            apPos.add(new Position(iX,iY -1));
        if(iY + 1 < 0 && iY +1 >= iMaxY)
            apPos.add(new Position(iX,iY +1));
        return apPos;
    }
    @Override
    public int hashCode(){
        return -1;
    }


    public boolean equals(Position pObject) throws Exception {
        throw new Exception("not inplemented");
    }
}
