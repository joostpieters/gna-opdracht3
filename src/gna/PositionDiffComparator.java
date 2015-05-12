package gna;

import java.util.Comparator;

/**
 * Created by covert on 11/05/15.
 */
public class PositionDiffComparator implements Comparator<Position> {
    @Override
    public int compare(Position position, Position t1) {
        int iSomA = position.getX() + position.getY();
        int iSomb = t1.getX() + position.getY();
        if(iSomA < iSomb)
            return -1;
        if(iSomA > iSomb)
            return 1;
        return 0;

    }
}
