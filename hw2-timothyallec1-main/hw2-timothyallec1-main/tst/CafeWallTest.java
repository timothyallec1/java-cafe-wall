import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


/**
 * ****This Class should never be ran on its own.***** This class has to be ran one test at a time with the corresponding
 * test in CreatePngTest.java
 **/

public class CafeWallTest {

    private final static int MAX_PIXEL_DIFF_ALLOWED = 550;

    @AfterAll
    public static void runAfterTestMethod() throws IOException {
        Files.deleteIfExists((new File("output1.png")).toPath());
        Files.deleteIfExists((new File("output2.png")).toPath());
        System.out.println("@AfterAll - removing output1.png and output2.png if exists");
    }

    //tests mortar1
    @Test
    public void test1() throws Exception {
        if (!new File("output1.png").exists()) {
            Assertions.fail("output1.png was not created"); //output1.png was not created
        }
        //compare pixel differences
        long pixelDiff = getPixelDifference("output1.png", "expected_png/expected_mortar1.png");
        String failMessage = String.format("The pixel difference is larger that the Maximum allowed Pixel Difference%s" +
                        "Pixel Difference: %s %sMaximum allowed Pixel Difference: %s %s",
                System.lineSeparator(), pixelDiff, System.lineSeparator(), MAX_PIXEL_DIFF_ALLOWED, System.lineSeparator());
        Assertions.assertTrue(pixelDiff <= MAX_PIXEL_DIFF_ALLOWED, failMessage);

    }

    //tests mortar2
    @Test
    public void test2() throws Exception {
        if (!new File("output2.png").exists()) {
            Assertions.fail("output2.png was not created"); //output2.png was not created
        }
        //compare pixel differences
        long pixelDiff = getPixelDifference("output2.png", "expected_png/expected_mortar2.png");
        String failMessage = String.format("The pixel difference is larger that the Maximum allowed Pixel Difference%s" +
                        "Pixel Difference: %s %sMaximum allowed Pixel Difference: %s %s",
                System.lineSeparator(), pixelDiff, System.lineSeparator(), MAX_PIXEL_DIFF_ALLOWED, System.lineSeparator());
        Assertions.assertTrue(pixelDiff <= MAX_PIXEL_DIFF_ALLOWED, failMessage);
    }

    private long getPixelDifference(String actualFilePath, String expectedFilePath) throws IOException {
        BufferedImage actualImage = ImageIO.read(new File(actualFilePath));
        BufferedImage expectedImage = ImageIO.read(new File(expectedFilePath));
        int actualImageWidth = actualImage.getWidth();
        int actualImageHeight = actualImage.getHeight();
        int expectedImageWidth = expectedImage.getWidth();
        int expectedImageHeight = expectedImage.getHeight();
        if ((actualImageWidth != expectedImageWidth) || (actualImageHeight != expectedImageHeight)) {
            Assertions.fail("Dimensions do not match");
            return MAX_PIXEL_DIFF_ALLOWED + 1; //dimensions must match
        } else {
            // check each pair of pixels
            int numDiffPixels = 0;
            for (int y = 0; y < actualImageHeight; y++) {
                for (int x = 0; x < actualImageWidth; x++) {
                    int actualImageRGB =  actualImage.getRGB(x, y);
                    int expectedImageRGB = expectedImage.getRGB(x, y);
                    if (actualImageRGB != expectedImageRGB) {
                        numDiffPixels++;
                    }
                }
            }
            return numDiffPixels;
        }
    }
}
