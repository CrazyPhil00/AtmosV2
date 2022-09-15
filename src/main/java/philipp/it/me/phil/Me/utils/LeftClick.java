package philipp.it.me.phil.Me.utils;

import java.awt.*;
import java.awt.event.InputEvent;

public class LeftClick {
    public void leftclick(){

        Robot bot;
        try {

            bot = new Robot();
            bot.mousePress(InputEvent.BUTTON1_MASK);
            bot.mouseRelease(InputEvent.BUTTON1_MASK);

        }

        catch (AWTException e) {

            e.printStackTrace();
        }

    }

    public static void rightclick(){

        Robot bot;
        try {

            bot = new Robot();
            bot.mousePress(InputEvent.BUTTON3_MASK);
            bot.mouseRelease(InputEvent.BUTTON3_MASK);

        }

        catch (AWTException e) {

            e.printStackTrace();
        }

    }

}

