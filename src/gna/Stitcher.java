package gna;

import java.util.*;

import libpract.*;
import sun.invoke.empty.Empty;

/**
 * Implement the methods stitch, seam and floodfill.
 */
public class Stitcher
{
	/**
	 * Return the sequence of positions on the seam. The first position in the
	 * sequence is (0, 0) and the last is (width - 1, height - 1). Each position
	 * on the seam must be adjacent to its predecessor and successor (if any).
	 * Positions that are diagonally adjacent are considered adjacent.
	 * 
	 * image1 and image2 are both non-null and have equal dimensions.
	 *
	 * Remark: Here we use the default computer graphics coordinate system,
	 *   illustrated in the following image:
	 * 
	 *        +-------------> X
	 *        |  +---+---+
	 *        |  | A | C |
	 *        |  +---+---+
	 *        |  | B | D |
	 *        |  +---+---+
	 *      Y v 
	 * 
	 *   The historical reasons behind using this layout is explained on the following
	 *   website: http://programarcadegames.com/index.php?chapter=introduction_to_graphics
	 * 
	 *   Position (x, y) corresponds to the pixels image1[x][y] and image2[x][y]. This
	 *   also means that, when an automated test mentioned that it used the array
	 *   {{A,B},{C,D}} as a test image, this corresponds to the image layout as shown in
	 *   the illustration above.
	 */
    //TODO check
	public List<Position> seam(int[][] image1, int[][] image2) {
        ArrayList<Position> alPositions;
        Comparator pComparator = new PositionDiffComparator();
        int[][] iaCostMap = new int[image1.length][image1[0].length];
        for(int i = 0; i < image1.length; i++){
            for(int j =0; j <image1[0].length;j++){
                //top Neighbours
                ArrayList<Position> alTemp = new Position(i,j).fncGetTopNeighbours(image1.length,image1[0].length);
                //als array leeg is skip zonder checking
                if (alTemp.size() == 0) {
                    iaCostMap[i][j] = fncDiff(image1, image2, i, j);
                    continue;
                }
                //als array gevuld is
                Position selectedPos = alTemp.get(0);
                for (Position pTemp:alTemp){
                    //als de nieuwer buur kleiner is dan de huidige kleinste -> zet nieuwe als kleinste
                    if(fncDiff(image1,image2,pTemp) < fncDiff(image1,image2,selectedPos)){
                        selectedPos = pTemp;
                    }
                }
                //dif van huidige pos + dif van kleinste buur pos
                iaCostMap[i][j] = fncDiff(image1,image2,i,j) + fncDiff(image1,image2,selectedPos) ;
            }
        }
        //path find
        alPositions = fncFindPath(iaCostMap,new Position(iaCostMap.length-1,iaCostMap[0].length-1));


        //debug
        System.out.print("lol k");
        return alPositions;
	}

    @Deprecated
    private boolean fncAlrinList(ArrayList<Position> alPos, Position cPoss) {
        for (Position selectedPos:alPos){
            if (selectedPos.getX() == cPoss.getX() && selectedPos.getY() == cPoss.getY())
                return true;
        }
        return false;
    }

    private int fncDiff(int[][] img1,int[][] img2,int iX, int iY)throws ArrayIndexOutOfBoundsException{
        return Math.abs(img1[iX][iY] - img2[iX][iY]);
    }
    private int fncDiff(int[][] img1,int[][] img2,Position pCurrent) throws IllegalArgumentException{
        if(pCurrent == null)
            throw new IllegalArgumentException();
        return fncDiff(img1, img2, pCurrent.getX(), pCurrent.getY());
    }

    private ArrayList<Position> fncFindPath(int[][] iaPath,Position pStart) throws ArrayIndexOutOfBoundsException,RuntimeException{
        ArrayList<Position> alPositions = new ArrayList<Position>();
        ArrayList<Position> alNieghbours = new ArrayList<Position>();
        alPositions.add(pStart);
        if(pStart.getY() == 0) {
            return alPositions;
        }
        alNieghbours = pStart.fncGetTopNeighbours(iaPath.length,iaPath[0].length);
        Position pSelected = alNieghbours.get(0);
        for (Position pTemp:alNieghbours){
            if(iaPath[pTemp.getX()][pTemp.getY()] < iaPath[pSelected.getX()][pSelected.getY()]){
                pSelected = pTemp;
            }
        }
        alPositions.addAll(fncFindPath(iaPath,pSelected));
        return alPositions;
    }
	/**
	 * Apply the floodfill algorithm described in the assignment to mask. You can assume the mask
	 * contains a seam from the upper left corner to the bottom right corner. Each position in the
	 * seam is adjacent to only one other seam position. For example, the seam will never go vertical
	 * and then horizontal (it will immediately go diagonal). The seam is represented using Stitch.SEAM
	 * and all other positions contain the default value Stitch.EMPTY. So your algorithm must replace
	 * all Stitch.EMPTY values with either Stitch.IMAGE1 or Stitch.IMAGE2.
	 *
	 * Positions left to the seam should contain Stitch.IMAGE1, and those right to the seam
	 * should contain Stitch.IMAGE2. You can run `ant test` for a basic (but not complete) test
	 * to check whether your implementation does this properly.
	 */
	public void floodfill(Stitch[][] mask) {


	}

	/**
	 * Return the mask to stitch two images together. The seam runs from the upper
	 * left to the lower right corner, where in general the rightmost part comes from
	 * the first image (but remember that the seam can be complex, see the spiral example
	 * in the assignment). A pixel in the mask is Stitch.IMAGE1 on the places where
	 * image1 should be used, and Stitch.IMAGE2 where image2 should be used. On the seam
	 * record a value of Stitch.SEAM.
	 * 
	 * ImageCompositor will only call this method (not seam and floodfill) to
	 * stitch two images.
	 * 
	 * image1 and image2 are both non-null and have equal dimensions.
	 */
	public Stitch[][] stitch(int[][] image1, int[][] image2) {
		// use seam and floodfill to implement this method
		throw new RuntimeException("not implemented yet");
	}
}


