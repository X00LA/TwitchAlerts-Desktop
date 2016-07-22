package io.deckerz.tad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class Notify extends JFrame {
    String Title;
    String Message;
    Boolean built = false;

    Notify(String title, String message) {
        this.Title = title;
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
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        JLabel headingLabel = new JLabel(Title);
        headingLabel.setOpaque(false);
        add(headingLabel, constraints);
        constraints.gridx++;
        constraints.weightx = 0f;
        constraints.weighty = 0f;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTH;
        JButton closeButton = new JButton(new AbstractAction("Close") {
            @Override
            public void actionPerformed(final ActionEvent e) {
                dispose();
            }
        });
        closeButton.setMargin(new Insets(1, 4, 1, 4));
        closeButton.setFocusable(false);
        add(closeButton, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        JLabel messageLabel = new JLabel("<html>" + Message);
        add(messageLabel, constraints);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        setLocation(scrSize.width - getWidth(), scrSize.height - toolHeight.bottom - getHeight());
        built = true;
    }

    String getMessage() {
        return this.Message;
    }

    String getNotifyTitle() {
        return this.Message;
    }

    Boolean setNotifyMessage(String msg) {
        this.Message = msg;
        return true;
    }

    Boolean setNotifyTitle(String title) {
        this.Title = title;
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