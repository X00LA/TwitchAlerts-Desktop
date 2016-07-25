package io.deckerz.tad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Notify extends JFrame {
    String Message;
    Boolean built = false;

    Notify(String message) {
        this.Message = message;
    }

    /**
     * Build yourself and modify
     */
    void buildNotification() {
        setType(Type.UTILITY);
        setSize(300, 125);
        setUndecorated(true);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0f;
        constraints.weighty = 0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        JLabel messageLabel = new JLabel("<html>" + Message);
        add(messageLabel, constraints);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        setLocation(scrSize.width - getWidth(), scrSize.height - toolHeight.bottom - getHeight());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        built = true;
    }

    String getMessage() {
        return this.Message;
    }

    Boolean setNotifyMessage(String msg) {
        this.Message = msg;
        return true;
    }

    /**
     * push notification, if not build it builds it for you
     */
    Boolean push() {
        if (built) {
            try {
                setVisible(true);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            buildNotification();
            return push();
        }
    }
}