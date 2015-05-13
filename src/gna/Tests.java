package gna;

import libpract.Stitch;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by covert on 11/05/15.
 */
public class Tests {

    @Test
    public void testSeam() throws Exception {
        Stitcher oStitch = new Stitcher();
        int[][] img1 = new int[][]{ {7,1,1},
                                    {2,8,5},
                                    {1,1,8}};
        int[][] img2 = new int[][]{ {0,0,0},
                                    {0,0,0},
                                    {0,0,0}};
        ArrayList<Position> alPos = (ArrayList<Position>) oStitch.seam(img1, img2);
        System.out.print(alPos);
    }

	@Test
	public void testPicture() {
		Stitcher oStitch = new Stitcher();
		int[][] image1 = new int[][]{ {1,1,2},
										  {3,1,3},
										  {3,1,1}};
		int[][] image2 = new int[][]{ {1,1,5},
										  {2,1,9},
										  {7,1,1}};
		ArrayList<Position> alpSeam = (ArrayList<Position>) oStitch.seam(image1, image2);
		Stitch[][] mask = new Stitch[image1.length][image1[0].length];
		for (Position pTemp:alpSeam){
			mask[pTemp.getX()][pTemp.getY()] = Stitch.SEAM;
		}
		for (int i = 0; i < mask.length;i++){
			for (int j = 0;j < mask[0].length;j++){
				if (mask[i][j] != Stitch.SEAM)
					mask[i][j] = Stitch.EMPTY;
			}
		}
		mask = oStitch.floodfill(mask);
		System.out.print(mask);
	}
}