package hmas;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


// Make a 2D boolean array out of an image file
public class ImageOpener
{
    public static boolean[][] urlToBoolArray(String url) throws IOException {
        File f = new File(url);
        BufferedImage i = null;
        i = ImageIO.read(f);
        int c;
        int blue;
        boolean boolArray[][] = new boolean[i.getWidth()][i.getHeight()];

        for (int x = 0; x < i.getWidth(); x++) {
            for (int y = 0; y < i.getHeight(); y++) {
                blue = i.getRGB(x,y) & 0x000000ff ;
                boolArray[x][y] = (blue != 255);
            }
        }
        return boolArray;
    }

    public static void displayBoolArray(boolean[][] b) {
        for (int y=0; y < b[0].length; y++) {
            for (int x=0; x < b.length; x++) {
                 System.out.print(b[x][y] ? "X" : " ");   
            }
            System.out.println("");
        }
    }
}
