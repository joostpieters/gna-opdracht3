package gna;

import java.util.*;

import libpract.*;
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
	public List<Position> seam(int[][] image1, int[][] image2) {
        ArrayList<Position> alPositions = new ArrayList<Position>();
        Comparator pComparator = new PositionDiffComparator();
        PriorityQueue<Position> pqPos = new PriorityQueue<Position>(image1.length*image1[0].length,pComparator);
        Position pCurrent = new Position(0,0);
        alPositions.add(new Position(pCurrent.getX(),pCurrent.getY()));
        while(true){
            ArrayList<Position> alTemp = new ArrayList<Position>();
            alTemp = pCurrent.fncGetNeighbours(image1.length,image1[0].length);
            for (Position pTemp:alTemp){

            }
        }
        return alPositions;
	}

    public boolean fncAllinList(ArrayList<Position> alPos, Position cPoss) {
        for (Position selectedPos:alPos){
            if (selectedPos.getX() == cPoss.getX() && selectedPos.getY() == cPoss.getY())
                return true;
        }
        return false;
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
		throw new RuntimeException("not implemented yet");
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


