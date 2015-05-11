package gna;

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
        int[][] img1 = new int[][]{ {1,1,2},
                                    {3,1,3},
                                    {3,1,1}};
        int[][] img2 = new int[][]{ {1,1,5},
                                    {2,1,9},
                                    {7,1,1}};
        ArrayList<Position> alPos = (ArrayList<Position>) oStitch.seam(img1, img2);
        System.out.print(alPos);
    }
}