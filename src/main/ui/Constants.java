package ui;

import java.awt.*;

// Common constants used in multiple classes
public class Constants {

    public static final Dimension GUI_SIZE = new Dimension(540, 840);

    public static final Dimension BANNER_SIZE = new Dimension(GUI_SIZE.width, 50);

    public static final Dimension EXERCISEPANEL_SIZE = new Dimension(GUI_SIZE.width - 30,
            GUI_SIZE.height - 230);

    public static final Dimension ADDEXERCISE = new Dimension(GUI_SIZE.width, 50);

    public static final Dimension EXERCISEFIELD_SIZE = new Dimension((int) (EXERCISEPANEL_SIZE.width * 0.50),
            25);

    public static final Dimension DELETEBUTTON = new Dimension((int)(40), 50);
}
