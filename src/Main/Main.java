package Main;

import Controller.Controller;
import Observable.Observable;
import Observer.*;
import View.Gui;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Gui app = new Gui();
            Observable observable = new Observable();
            AdvisorMAC advisorMAC = new AdvisorMAC(observable);
            AdvisorRSI advisorRSI = new AdvisorRSI(observable);
            AdvisorMACD advisorMACD = new AdvisorMACD(observable);
            AdvisorBB advisorBB = new AdvisorBB(observable);
            AdvisorSSAR advisorSSAR = new AdvisorSSAR(observable);
            Controller.setGui(app);
            Controller.setObservable(observable);
            app.setVisible(true);
        });

    }
}
