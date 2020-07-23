package it.nfdev.ggk.gui;

import it.nfdev.ggk.conf.Application;
import it.nfdev.ggk.conf.Constants;
import it.nfdev.ggk.action.GameActionData;
import it.nfdev.ggk.control.GameCommunicationChannel;
import it.nfdev.ggk.control.GameContext;
import it.nfdev.ggk.control.GameMessageFactory;
import it.nfdev.ggk.control.GameOutputListener;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.Set;
import javax.swing.JFrame;

public class MainFrame extends JFrame implements GameOutputListener {

    private Board gameBoard;
    private CommandPanel commandPanel;

    private GameCommunicationChannel channel;

    public MainFrame(GameCommunicationChannel channel) throws HeadlessException {
        super(Application.instance().getProperty(Constants.KEY_TITLE) + " v" + Application.instance().getProperty(Constants.KEY_VERSION));
        this.channel = channel;
        init();
    }

    public void showMainFrame() {
        setVisible(true);
    }

    private void init() {
        defineMainFrame();
        createComponents();
        defineLayout();
    }

    private void defineMainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = Application.instance().getIntProperty(Constants.KEY_FRAME_WIDTH);
        int heigth = Application.instance().getIntProperty(Constants.KEY_FRAME_HEIGHT);
        setSize(width, heigth);
    }

    private void createComponents() {
        gameBoard = new Board();
        commandPanel = new CommandPanel(channel);
    }

    private void defineLayout() {
        this.setLayout(new BorderLayout());
        this.getContentPane().add(BorderLayout.CENTER, gameBoard);
        this.getContentPane().add(BorderLayout.SOUTH, commandPanel);
    }

    @Override
    public void listenNewPlayer(String playerName) {
        Set<String> playersSet = GameContext.instance().getAllPlayers();
        String message = GameMessageFactory.createNewPlayerMessage(playersSet);
        commandPanel.addMessage(message);
        gameBoard.addPlayer(playerName);
    }

    @Override
    public void listenExistingPlayer(String playerName) {
        String message = GameMessageFactory.createExistingPlayerMessage(playerName);
        commandPanel.addMessage(message);
    }

    @Override
    public void listenNewGameAction(GameActionData gameActionData) {
        commandPanel.addMessage(gameActionData.getMessage());
        gameBoard.movePlayer(gameActionData);
    }
}
