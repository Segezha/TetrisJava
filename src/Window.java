import javax.swing.*;

public class Window {
    public static final int WIDTH = 305, HEIGHT = 630;
    private JFrame window;
    private Board board;
    private Draw draw;

    public Window() {    //Constructor
        window = new JFrame("Tetris Game");
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        board = new Board();

        draw = new Draw(board);

        window.add(draw);
        window.addKeyListener(draw);

        window.setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
    }
}

