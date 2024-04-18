//created by Timothy Allec.
// Finished on 9/28/22. Discussed with Vincent(section B) and ELijah(tutor).
import java.awt.*;
import java.io.IOException;

//this class will create an organized graphic of various designs using methods and adjustable parameters
public class CafeWall{

    // this constant changes the offset of the y coordinate in between rows
    private static Integer MORTAR = 2;

    //initializes drawing panel, and displays parameters for each row and box
    public static void main(String[] args) throws IOException{
        DrawingPanel panel = initializePanel(650, 420, Color.GRAY);
        Graphics g = panel.getGraphics();

        drawRow(g, 0, 0, 3, 25);                                                     //upper left row
        drawRow(g, 40, 80, 4, 35);                                                     //mid-left row
        drawBox(g, 20, 140, 5, 20, 0);                                        //bottom-left box
        drawBox(g, 250, 180, 3, 25, 15);                                    //middle bottom box
        drawBox(g, 425, 200, 5, 20, 15);                                     //bottom-right box
        drawBox(g, 450, 20, 2, 30, 30);                                       //upper right box
        panel.save("output" + MORTAR + ".png");                                               // saves as a png
    }

    // draws a pair of 1 white box and 1 black box using drawX method to draw a blue X inside every black box
    private static void drawPair(Graphics g,int x, int y, int size){
        int offset = size -1;
        g.setColor(Color.black);
        g.fillRect(x, y, size, size);
        g.setColor(Color.blue);
        drawX(g, x, y, offset);
        g.setColor(Color.WHITE);
        g.fillRect(x + size, y, size, size);
    }

    // draws a singular row of a given number of pairs of black and white squares
    private static void drawRow(Graphics g, int x, int y, int numPairs, int size){
        for (int i = 0; i <numPairs; i++ ){
            drawPair(g, x + 2 * (i * size), y , size);
        }
    }

    /* draws multiple rows to create a box(grid) appearance.
    The offset parameter shifts the y coordinate of the row using the MORTAR constant*/
    private static void drawBox (Graphics g,int x, int y, int numPairs, int size, int offset){
        for (int i = 0; i < 2 * numPairs; i++) {
            drawRow(g, x + (i % 2) * offset, (y + size * i) + i * MORTAR , numPairs, size);
        }
    }

    // draws the blue X inside every black square
    private static void drawX(Graphics g, int x,int y, int offset){
        g.drawLine(x,y, x + offset , y + offset);
        g.drawLine(x, y + offset, x + offset , y);
    }

    //constructs the drawing panel
    private static DrawingPanel initializePanel ( int width, int height, Color bgColor){
        DrawingPanel panel = new DrawingPanel(width, height);
        panel.setBackground(bgColor);
        return panel;
    }
}