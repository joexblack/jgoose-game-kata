package it.nfdev.ggk.gui;

import it.nfdev.ggk.conf.Application;
import it.nfdev.ggk.conf.Constants;
import it.nfdev.ggk.action.GameActionData;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class Board extends JPanel {

    private Box[][] boardBoxes;

    public Board() {//Image image) {
        super();
        init();
    }

    public void addPlayer(String playerName) {
        boardBoxes[0][0].addPlayer(playerName);
    }

    public void movePlayer(GameActionData gameActionData) {
        int[] startCoordinates = locateBoxByPosition(gameActionData.getStartPosition());
        int[] destCoordinates = locateBoxByPosition(gameActionData.getEndPosition());

        Box startBox = boardBoxes[startCoordinates[0]][startCoordinates[1]];
        Box destBox = boardBoxes[destCoordinates[0]][destCoordinates[1]];

        startBox.removePlayer(gameActionData.getPlayerName());
        destBox.addPlayer(gameActionData.getPlayerName());
    }

    private int[] locateBoxByPosition(int position) {
        int[] coordinates = new int[]{-1, -1};
        for (int i = 0; i < boardBoxes.length; i++) {
            for (int j = 0; j < boardBoxes[0].length; j++) {
                if (boardBoxes[i][j] != null) {
                    if (boardBoxes[i][j].getBoxNumber() == position) {
                        coordinates[0] = i;
                        coordinates[1] = j;
                    }
                }
            }
        }
        return coordinates;
    }

    private void init() {
        createComponents();
        defineLayout();
    }

    private void createComponents() {
        int rows = Application.instance().getIntProperty(Constants.KEY_BOARD_ROWS);
        int columns = Application.instance().getIntProperty(Constants.KEY_BOARD_COLUMNS);
        boardBoxes = new Box[rows][columns];
        navigate((value, x, y, sides) -> {
            Box box = new Box(value);
            box.addBorder(sides);
            boardBoxes[x][y] = box;
        });
    }

    private void defineLayout() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.ipadx = Application.instance().getIntProperty(Constants.KEY_BOARD_BOX_WIDTH);
        gridBagConstraints.ipady = Application.instance().getIntProperty(Constants.KEY_BOARD_BOX_HEIGHT);

        navigate((value, x, y, sides) -> {
            gridBagConstraints.gridx = y;
            gridBagConstraints.gridy = x;
            add(boardBoxes[x][y], gridBagConstraints);
        });
    }

    private void navigate(QuadConsumer<Integer, Integer, Integer, Integer[]> valuesConsumer) {
        int counter = 0;
        int rowStart = 0;
        int rowEnd = boardBoxes.length - 1;
        int colStart = 0;
        int colEnd = boardBoxes[0].length - 1;
        Integer[] sides = null;

        while (colStart <= colEnd && rowStart <= rowEnd && counter < Constants.BOX_LAST) {
            for (int i = colStart; i < colEnd && counter < Constants.BOX_LAST; i++) {
                counter++;
                sides = i == colStart ? new Integer[]{ Box.TOP } : new Integer[]{Box.TOP};
                valuesConsumer.accept(counter, rowStart, i, sides);
            }

            for (int i = rowStart; i < rowEnd && counter < Constants.BOX_LAST; i++) {
                counter++;
                sides = i == rowStart ? new Integer[]{ Box.TOP, Box.RIGHT } : new Integer[]{Box.RIGHT};
                valuesConsumer.accept(counter, i, colEnd, sides);
            }

            for (int i = colEnd; i > colStart && counter < Constants.BOX_LAST; i--) {
                counter++;
                sides = i == colEnd ? (counter == Constants.BOX_LAST ? new Integer[]{ Box.TOP, Box.BOTTOM, Box.RIGHT } : new Integer[]{ Box.BOTTOM, Box.RIGHT }) : new Integer[]{Box.BOTTOM};
                valuesConsumer.accept(counter, rowEnd, i, sides);
            }

            for (int i = rowEnd; i > rowStart && counter < Constants.BOX_LAST; i--) {
                counter++;
                sides = i == rowEnd ? new Integer[]{ Box.LEFT, Box.BOTTOM } : ( i == rowStart + 1 ? new Integer[]{ Box.LEFT, Box.TOP } : new Integer[]{ Box.LEFT } );
                valuesConsumer.accept(counter, i, colStart, sides);
            }
            rowStart++;
            colEnd--;
            rowEnd--;
            colStart++;

        }
    }
    
    @FunctionalInterface
    private interface QuadConsumer<I1, I2, I3, I4> {
        void accept(I1 i1, I2 i2, I3 i3, I4 i4);
    }
}
