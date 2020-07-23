package it.nfdev.ggk;

import it.nfdev.ggk.control.GameCommunicationChannel;
import it.nfdev.ggk.control.GameController;
import it.nfdev.ggk.gui.MainFrame;

public class Main {

    public static void main(String[] args) {   
        GameCommunicationChannel channel = new GameCommunicationChannel();
        MainFrame mainFrame = new MainFrame(channel);
        GameController controller = new GameController(channel); 
        channel.addCommandListener(controller);
        channel.addOutputListener(mainFrame);
        mainFrame.showMainFrame();
    }

}
