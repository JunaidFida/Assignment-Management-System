import javax.swing.SwingUtilities;

import Interfaces.Login;
class App{
public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            new Login().setVisible(true);
        }
    });
}
}

