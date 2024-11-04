package sk.pocsik.utils;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UIHelper {

    public static Action getEnterAction(Runnable action) {
        return createEnterAction(action);
    }

    private static Action createEnterAction(Runnable action) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        };
    }
}
