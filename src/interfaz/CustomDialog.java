package interfaz;

import javax.swing.*;
import java.awt.*;

public class CustomDialog extends JFrame {
    private JLabel titleLabel;
    private JTextArea contentTextArea;

    public CustomDialog(String title, String content) {
        setTitle(title);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);

        contentTextArea = new JTextArea(content);
        contentTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(contentTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    public void showDialog() {
        setVisible(true);
    }
}
