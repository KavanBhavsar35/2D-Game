package main;

//EXTENDED LIBRARY
import javax.swing.JFrame;

public class Main {

    private static final String TITLE = "2D Adventure";
    private static final boolean IS_RESIZABLE = false;
    //MAIN METHOD
    public static void main(String[] args) {
        
        //BASIC WINDOW SETTINGS
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(IS_RESIZABLE);
        window.setTitle(TITLE);

        //GAMEPANEL INIT
        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //GAMEPANEL SETUP
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
    
}