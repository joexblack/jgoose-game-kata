package it.nfdev.ggk.gui;

import it.nfdev.ggk.conf.Application;
import it.nfdev.ggk.conf.Constants;
import it.nfdev.ggk.control.GameCommunicationChannel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CommandPanel extends JPanel {

    private JLabel inputLabel;
    private JTextField inputField;
    private JTextArea outputArea;
    private JScrollPane outputScrollPane;
    private GameCommunicationChannel channel;

    public CommandPanel(GameCommunicationChannel channel) {
        super();
        this.channel = channel;
        init();
    }

    private void init() {
        createComponents();
        defineLayout();
    }

    private void createComponents() {
        // Output Text Area      
        int outputAreaRows = Application.instance().getIntProperty(Constants.KEY_COMMAND_PANEL_OUTPUT_ROWS);
        int outputAreaColumns = Application.instance().getIntProperty(Constants.KEY_COMMAND_PANEL_OUTPUT_COLUMNS);
        Font outputFont = new Font("Arial", Font.ITALIC, 15);
        outputArea = new JTextArea(outputAreaRows, outputAreaColumns);
        outputArea.setEditable(false);
        outputArea.setFont(outputFont);
        outputScrollPane = new JScrollPane(outputArea);
        outputScrollPane.setFont(outputFont);

        Font inputFont = new Font("Arial", Font.PLAIN, 15);
        
        // Input label
        String inputLabelString = Application.instance().getProperty(Constants.KEY_COMMAND_PANEL_LABEL);
        inputLabel = new JLabel(inputLabelString);
        inputLabel.setFont(inputFont);

        // Input Field                
        int inputFieldLength = Application.instance().getIntProperty(Constants.KEY_COMMAND_PANEL_FIELD_LENGTH);
        inputField = new JTextField(inputFieldLength);
        inputField.addActionListener(this::handleInput);
        inputField.setFont(inputFont);
    }

    private void defineLayout() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        add(BorderLayout.CENTER, outputScrollPane);
        add(BorderLayout.SOUTH, inputPanel);

        inputField.requestFocusInWindow();
    }

    private void handleInput(ActionEvent event) {
        String command = inputField.getText();
        channel.sendCommand(command);
        inputField.setText("");
    }

    public void addMessage(String message) {
        outputArea.append(message);
        outputArea.append("\n");
    }
}
