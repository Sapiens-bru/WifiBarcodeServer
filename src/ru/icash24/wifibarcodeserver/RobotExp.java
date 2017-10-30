package ru.icash24.wifibarcodeserver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * Created by Vladimir on 26.10.2017.
 */
public class RobotExp {

    public static void type(String args) {
        Character ch;

        try {
            Robot robot = new Robot();

            for (int i = 0; i < args.length(); i++) {
                ch=args.charAt(i);
                switch (ch) {
                    case '0': robot.keyPress(KeyEvent.VK_0); break;
                    case '1': robot.keyPress(KeyEvent.VK_1); break;
                    case '2': robot.keyPress(KeyEvent.VK_2); break;
                    case '3': robot.keyPress(KeyEvent.VK_3); break;
                    case '4': robot.keyPress(KeyEvent.VK_4); break;
                    case '5': robot.keyPress(KeyEvent.VK_5); break;
                    case '6': robot.keyPress(KeyEvent.VK_6); break;
                    case '7': robot.keyPress(KeyEvent.VK_7); break;
                    case '8': robot.keyPress(KeyEvent.VK_8); break;
                    case '9': robot.keyPress(KeyEvent.VK_9); break;
                }
            }
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
