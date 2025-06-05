package Controlador.bancos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import vista.Login;
import vista.MdiGenebac;

public class SesionInactiva {

    public static void iniciarSesion(MdiGenebac aThis, JLabel labelContador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private Timer timerCerrarSesion;
    private Timer timerContador;
    private final int TIEMPO_INACTIVIDAD = 3*60*1000; // 3 segundos para prueba 3 * 1000; (luego 5*60*1000)
    private int tiempoRestante; // en segundos
    private JLabel labelContador;

    public SesionInactiva(JFrame frame, JLabel labelContador) {
        this.labelContador = labelContador;
        tiempoRestante = TIEMPO_INACTIVIDAD / 1000; // en segundos

        // Timer para cerrar sesión
        timerCerrarSesion = new Timer(TIEMPO_INACTIVIDAD, e -> cerrarSesion(frame));
        timerCerrarSesion.setRepeats(false);
        timerCerrarSesion.start();

        // Timer para actualizar contador cada segundo
        timerContador = new Timer(1000, e -> actualizarContador());
        timerContador.start();

        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            public void eventDispatched(AWTEvent event) {
                if (event instanceof MouseEvent || event instanceof KeyEvent) {
                    reiniciarTimers();
                }
            }
        }, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
    }

    private void actualizarContador() {
        tiempoRestante--;
        if (tiempoRestante >= 0) {
            labelContador.setText("Cerrando sesión Por Inactividad en: " + tiempoRestante + " segundos");
        }
    }

    private void reiniciarTimers() {
        tiempoRestante = TIEMPO_INACTIVIDAD / 1000;
        labelContador.setText("Cerrando sesión en: " + tiempoRestante + " segundos");
        timerCerrarSesion.restart();
        timerContador.restart();
    }

    private void cerrarSesion(JFrame frame) {
        timerContador.stop();
        JOptionPane.showMessageDialog(frame, "Sesión cerrada por inactividad.");

        for (Window window : Window.getWindows()) {
            window.dispose();
        }

        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
        });
    }
}
