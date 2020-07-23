package it.nfdev.ggk.gui;

import it.nfdev.ggk.conf.Constants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Box extends JPanel {

    public static final int TOP = 0;
    public static final int RIGHT = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;

    private final int boxNumber;
    private JLabel boxLabel;
    private JTextArea playersArea;
    private JScrollPane playersScrollPane;

    public Box(int cellNumber) {
        this(cellNumber, null);
    }

    public Box(int cellNumber, Image image) {
        super();
        this.boxNumber = cellNumber;
        init();
    }

    private void init() {
        createComponents();
        defineLayout();
    }

    private void createComponents() {
        String boxLabelString = getBoxLabel();
        Font boxLabelFont = new Font("Arial", Font.BOLD, 18);
        Font playerFont = new Font("Arial", Font.BOLD, 16);
        boxLabel = new JLabel(boxLabelString);
        boxLabel.setHorizontalAlignment(JLabel.CENTER);
        boxLabel.setOpaque(false);
        boxLabel.setFont(boxLabelFont);
        playersArea = new JTextArea(3, 7);
        playersArea.setEditable(false);
        playersArea.setOpaque(false);
        playersArea.setFont(playerFont);
        playersScrollPane = new JScrollPane(playersArea);
        playersScrollPane.getViewport().setOpaque(false);
        playersScrollPane.setOpaque(false);  
        playersScrollPane.setFont(playerFont);
        playersScrollPane.setBorder(null);
    }
    
    private String getBoxLabel() {
        String label = null;
        if (boxNumber == 1) { label = "Start"; }
        else if (boxNumber == Constants.BOX_BRIDGE_START) { label = "Bridge"; }
        else if (Constants.BOX_GOOSES.contains(boxNumber)) { label = "Goose"; }
        else if (boxNumber == Constants.BOX_LAST) { label = "End"; }
        else label = boxNumber < 10 ? "   0" + boxNumber + "   " : "   " + String.valueOf(boxNumber) + "   ";
        return label;
    }

    private void defineLayout() {
        setOpaque(false);
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, boxLabel);
        add(BorderLayout.CENTER, playersScrollPane);
    }

    public int getBoxNumber() {
        return boxNumber;
    }

    public void addPlayer(String playerName) {
        playersArea.append(playerName + "\n");
    }

    public void removePlayer(String playerName) {
        String currentPlayersList = playersArea.getText();
        String newPlayerList = Arrays.asList(currentPlayersList.split("\n")).stream()
                .filter(player -> !player.equals(playerName))
                .collect(Collectors.joining("\n"));
        playersArea.setText(newPlayerList);
    }

    public void addBorder(Integer... sides) {
        int[] borderDim = new int[]{0, 0, 0, 0};
        for (int i = 0; i < sides.length; i++) {
            borderDim[sides[i]] = 3;
        }
        setBorder(BorderFactory.createMatteBorder(borderDim[TOP], borderDim[LEFT], borderDim[BOTTOM], borderDim[RIGHT], Color.BLUE));
    }
}
