import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Board {

    private BufferedImage blocks;

    private final int blockSize = 30;

    private final int width = 10, height = 20;

    private int[][] board = new int[height][width];

    private Shape[] shapes = new Shape[7];

    private Shape currentShape;

    private final int FPS = 60;

    private boolean gameOver = false;

    public Board() {
        try {
            blocks = ImageIO.read(Board.class.getResource("/tiles.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //shapes

        shapes[0] = new Shape(blocks.getSubimage(0, 0, blockSize, blockSize), new int[][]{
                {1, 1, 1, 1}
        }, this, 1);

        shapes[1] = new Shape(blocks.getSubimage(blockSize, 0, blockSize, blockSize), new int[][]{
                {1, 1, 0},
                {0, 1, 1}
        }, this, 2);

        shapes[2] = new Shape(blocks.getSubimage(blockSize * 2, 0, blockSize, blockSize), new int[][]{
                {0, 1, 1},
                {1, 1, 0}
        }, this, 3);

        shapes[3] = new Shape(blocks.getSubimage(blockSize * 3, 0, blockSize, blockSize), new int[][]{
                {1, 1, 1},
                {0, 0, 1}
        }, this, 4);

        shapes[4] = new Shape(blocks.getSubimage(blockSize * 4, 0, blockSize, blockSize), new int[][]{
                {1, 1, 1},
                {1, 0, 0}
        }, this, 5);

        shapes[5] = new Shape(blocks.getSubimage(blockSize * 5, 0, blockSize, blockSize), new int[][]{
                {1, 1, 1},
                {0, 1, 0}
        }, this, 6);

        shapes[6] = new Shape(blocks.getSubimage(blockSize * 6, 0, blockSize, blockSize), new int[][]{
                {1, 1},
                {1, 1}
        }, this, 7);

        setNextShape();
    }

    public void setNextShape(){
        int index = (int)(Math.random()*shapes.length);

        currentShape = new Shape(shapes[index].getBlock(), shapes[index].getCoords(), this,
                shapes[index].getColor());

        for (int row = 0; row < currentShape.getCoords().length; row++)
            for (int col = 0; col < currentShape.getCoords()[row].length; col++)
                if(currentShape.getCoords()[row][col] != 0){
                    if (board[row][col + 3] != 0)
                        gameOver = true;
                }
    }

    public int getBlockSize(){
        return blockSize;
    }

    public int[][] getBoard(){
        return board;
    }

    public BufferedImage getBlocks() {
        return blocks;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Shape getCurrentShape() {
        return currentShape;
    }
}