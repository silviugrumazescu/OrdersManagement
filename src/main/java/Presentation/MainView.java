package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

public class MainView extends JFrame {

    private JPanel mainPanel;
    private JButton clientViewButton, productViewButton, orderViewButton;

    public MainView(){

        this.setPreferredSize(new Dimension(700, 400));
        this.pack();

        mainPanel = new JPanel();

        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        clientViewButton = new JButton("Clients");
        clientViewButton.setPreferredSize(new Dimension(200, 200));


        productViewButton = new JButton("Products");
        productViewButton.setPreferredSize(new Dimension(200, 200));
        orderViewButton = new JButton("Orders");
        orderViewButton.setPreferredSize(new Dimension(200, 200));

        JLabel label = new JLabel("Orders management");
        label.setFont(new Font("Verdana", Font.BOLD, 20));

        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0,0,30,0);
        mainPanel.add(label, c);

        c.gridx = 0; c.gridy = 1;
        mainPanel.add(clientViewButton,c );
        c.gridx = 1; c.gridy = 1;
        mainPanel.add(productViewButton,c);
        c.gridx = 2; c.gridy = 1;
        mainPanel.add(orderViewButton,c);

        this.add(mainPanel);

    }

    public void setActionListeners(ActionListener clientViewListener, ActionListener productViewListener, ActionListener orderViewListener){
        clientViewButton.addActionListener(clientViewListener);
        productViewButton.addActionListener(productViewListener);
        orderViewButton.addActionListener(orderViewListener);
    }


}
