package Presentation.Controllers;

import Presentation.Controllers.ClientViewController;
import Presentation.Controllers.OrdersViewController;
import Presentation.Controllers.ProductViewController;
import Presentation.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController{

    private MainView mainView;
    private ClientViewController clientViewController;
    private OrdersViewController ordersViewController;
    private ProductViewController productViewController;

    public MainController(){

        mainView = new MainView();
        mainView.setActionListeners(new ClientViewListener(), new ProductViewListener(), new OrdersViewListener());
        this.initialize();

        clientViewController = new ClientViewController(this);
        ordersViewController = new OrdersViewController(this);
        productViewController = new ProductViewController(this);

    }

    public void initialize(){
        mainView.setVisible(true);
    }
    public void hide(){
        System.out.println("Main view hidden");
        mainView.setVisible(false);
    }

    public class ClientViewListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            hide();
            clientViewController.initialize();
        }
    }
    public class ProductViewListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            hide();
            productViewController.initialize();
        }
    }
    public class OrdersViewListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            hide();
            ordersViewController.initialize();
        }
    }

}
