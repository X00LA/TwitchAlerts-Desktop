package io.deckerz.tad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TwitchAlertsDesktop {
    static List<Thread> channels = new ArrayList<>();
    static Class TWD = TwitchAlertsDesktop.class;
    public static void main(String[] args) {
        TrayIcon trayIcon;
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage(TWD.getResource("/images.png"));
            ActionListener listener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    exit();
                }
            };
            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem = new MenuItem("Exit");
            defaultItem.addActionListener(listener);
            popup.add(defaultItem);
            trayIcon = new TrayIcon(image, "TwitchAlerts-Desktop", popup);
            trayIcon.addActionListener(listener);
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println(e);
            }
            start();
        } else {
            System.exit(0);
        }
    }

    public static void exit() {
        for (int i = 0; i != channels.size(); i++) {
            channels.get(i).stop();
        }
        System.exit(0);
    }

    public static void start() {
        for (int i = 0; i != channels.size(); i++) {
            channels.get(i).start();
        }
    }

}
