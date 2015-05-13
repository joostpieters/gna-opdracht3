package gna;

import java.util.*;

import javafx.geometry.Pos;
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
		int[][] iaCost = new int[image1.length][image1[0].length];
		Position[][] pFrom = new Position[image1.length][image1[0].length];
		Position pVorige = new Position(0,0);
		Position pCurrent = new Position(0,0);
		ArrayList<Position> alpVisited = new ArrayList<>();
		alpVisited.add(pCurrent);
		while (pCurrent.getX() != image1.length- 1 && pCurrent.getY() != image1[0].length) {
			ArrayList<Position> alpNeighbours = pCurrent.fncGetTopNeighbours(image1.length, image1[0].length);
			for (Position pTemp : alpNeighbours) {
				if (iaCost[pTemp.getX()][pTemp.getY()] < fncDiff(pTemp) + fncDiff(pVorige)) {
					iaCost[pTemp.getX()][pTemp.getY()] = fncDiff(pTemp) + fncDiff(pVorige);
					pFrom[pTemp.getX()][pTemp.getY()] = pTemp;
					if (pTemp.getX() != image1.length - 1 && pTemp.getY() != image1[0].length)
						break;
				}
			}

			alpVisited.add(pCurrent);
		}
		return alpVisited;
    }

	private int fncDiff(Position pCurrent) throws IllegalArgumentException{
		if(pCurrent == null)
			throw new IllegalArgumentException();
		return ImageCompositor.pixelSqDistance(pCurrent.getX(), pCurrent.getY());
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
	public Stitch[][] floodfill(Stitch[][] mask) {
		//find seam on every row
		mask = flood(mask,new Position(mask.length -1 ,0),Stitch.IMAGE1);
		mask = flood(mask,new Position(0,mask[0].length -1),Stitch.IMAGE2);
		return mask;
	}

	private Stitch[][] flood(Stitch[][] mask, Position pCurrent,Stitch sCurrent){
		if (mask[pCurrent.getX()][pCurrent.getY()] != Stitch.EMPTY)
			return mask;

		//fill
		mask[pCurrent.getX()][pCurrent.getY()] = sCurrent;
		//andere
		//boven
		if(pCurrent.getX() >= 1)
			mask = flood(mask,new Position(pCurrent.getX() - 1,pCurrent.getY()),sCurrent);
		//onder
		if(pCurrent.getX() < mask.length -1)
			mask = flood(mask,new Position(pCurrent.getX() + 1,pCurrent.getY()),sCurrent);
		//links
		if(pCurrent.getY() >= 1)
			mask = flood(mask,new Position(pCurrent.getX(),pCurrent.getY() - 1),sCurrent);
		//rechts
		if(pCurrent.getY() < mask[0].length -1)
			mask = flood(mask,new Position(pCurrent.getX(),pCurrent.getY() + 1),sCurrent);

		return mask;
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
	 * use seam and floodfill to implement this method
	 */
	public Stitch[][] stitch(int[][] image1, int[][] image2) {
		ArrayList<Position> alpSeam = (ArrayList<Position>) seam(image1,image2);
		Stitch[][] mask = new Stitch[image1.length][image1[0].length];
		for (Position pTemp:alpSeam){
			mask[pTemp.getX()][pTemp.getY()] = Stitch.SEAM;
		}

		for (int i = 0; i > mask.length;i++){
			for (int j = 0;j > mask[0].length;j++){
				if (mask[i][j] != Stitch.SEAM)
					mask[i][j] = Stitch.EMPTY;
			}
		}
		return floodfill(mask);

	}
}


