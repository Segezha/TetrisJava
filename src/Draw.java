import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Draw extends JPanel implements KeyListener {
    private Board board;

    private Timer timer;

    private final int FPS = 60;

    private final int delay = 1000/FPS;


    public Draw(Board board) {

        this.board = board;

        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });

        timer.start();

    }

    public void update(){
        board.getCurrentShape().update();
        if (board.isGameOver())
            timer.stop();
    }

    public void render(Graphics g){

        for (int row = 0; row < board.getCurrentShape().getCoords().length; row++)
            for (int col = 0; col < board.getCurrentShape().getCoords()[row].length; col++)
                if (board.getCurrentShape().getCoords()[row][col] != 0)
                    g.drawImage(board.getCurrentShape().getBlock(), col*board.getBlockSize() + board.getCurrentShape().getX()*board.getBlockSize(),
                            row*board.getBlockSize() + board.getCurrentShape().getY()*board.getBlockSize(), null);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        render(g);

        for (int row = 0; row < board.getBoard().length; row++)
            for (int col = 0; col < board.getBoard()[row].length; col++)
                if (board.getBoard()[row][col] != 0)
                    g.drawImage(board.getBlocks().getSubimage((board.getBoard()[row][col] - 1)*board.getBlockSize(),
                            0, board.getBlockSize(), board.getBlockSize()),
                            col*board.getBlockSize(),
                            row*board.getBlockSize(), null);

        for (int i = 0; i < board.getHeight(); i++){
            g.drawLine(0, i*board.getBlockSize(),
                    board.getWidth()*board.getBlockSize(), i*board.getBlockSize());
        }

        for (int j = 0; j < board.getWidth(); j++){
            g.drawLine(j*board.getBlockSize(), 0,
                    j*board.getBlockSize(), board.getHeight()*board.getBlockSize());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            board.getCurrentShape().setDeltaX(-1);
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            board.getCurrentShape().setDeltaX(1);
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            board.getCurrentShape().speedDown();
        if (e.getKeyCode() == KeyEvent.VK_UP)
            board.getCurrentShape().rotate();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            board.getCurrentShape().normalSpeed();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
