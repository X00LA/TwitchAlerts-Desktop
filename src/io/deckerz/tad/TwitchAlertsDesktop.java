package io.deckerz.tad;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TwitchAlertsDesktop {
    static List<Thread> channels = new ArrayList<>();
    static Class TWD = TwitchAlertsDesktop.class;
    public static void main(String[] args) {

        TrayIcon trayIcon = null;
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage(getImage());
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

    static Byte[] getImage() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        URL url = TWD.getClass().getResource("resources/images.png");
        Image img2 = tk.createImage(url);
        Btye[] img =

        return img;
    }
}
