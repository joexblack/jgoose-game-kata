package it.nfdev.ggk.conf;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Constants {
    
    private Constants() {} // Prevent constants class instantiation
    
    public static final String KEY_TITLE        = "ggk.title";
    public static final String KEY_VERSION      = "ggk.version";
    public static final String KEY_FRAME_WIDTH  = "ggk.frame.width";
    public static final String KEY_FRAME_HEIGHT = "ggk.frame.height";
    
    public static final String KEY_BOARD_BOX_WIDTH  = "ggk.frame.board.box.width";
    public static final String KEY_BOARD_BOX_HEIGHT = "ggk.frame.board.box.height";
    
    public static final String KEY_COMMAND_PANEL_LABEL          = "ggk.frame.command.panel.label";
    public static final String KEY_COMMAND_PANEL_FIELD_LENGTH   = "ggk.frame.command.panel.field.length";
    public static final String KEY_COMMAND_PANEL_OUTPUT_ROWS    = "ggk.frame.command.panel.output.rows";
    public static final String KEY_COMMAND_PANEL_OUTPUT_COLUMNS = "ggk.frame.command.panel.output.columns";
    
    public static final String KEY_BOARD_ROWS           = "ggk.board.rows";
    public static final String KEY_BOARD_COLUMNS        = "ggk.board.columns";
    public static final String KEY_BOARD_BOX_BACKGROUND = "ggk.board.box.background";
    
    public static final String KEY_GAME_ACTION_CLAZZ_PREFIX = "ggk.game.action.chain.ring_";
    
    public static final int BOX_BRIDGE_START     = 6;
    public static final int BOX_BRIDGE_END       = 12;
    public static final int BOX_LAST             = 63;
    public static final List<Integer> BOX_GOOSES =  Stream.of(5, 9, 14, 18, 23, 27).collect(Collectors.toList());

}
