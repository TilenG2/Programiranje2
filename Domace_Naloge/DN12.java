import java.awt.BorderLayout;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DN12 {

    public static void main(String[] args) {
        JFrame okno = new JFrame();
        okno.setTitle("DN12 Tilen Gabršček");
        okno.setSize(1280, 720);
        okno.setResizable(false);
        okno.setLocationRelativeTo(null);
        okno.setLayout(new BorderLayout());
        JPanel center = new JPanel();
        JPanel left = new JPanel();
        JPanel right = new JPanel();

        JButton pretvori = new JButton("Pretvori -->");
        JTextArea orinal = new JTextArea(45, 45);
        JTextArea pretvorba = new JTextArea(45, 45);
        left.add(orinal);
        right.add(pretvorba);
        center.setLayout(new GridBagLayout());

        okno.add(center, BorderLayout.CENTER);
        okno.add(right, BorderLayout.LINE_END);
        okno.add(left, BorderLayout.LINE_START);
        center.add(pretvori);

        pretvori.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pretvorba.setText(orinal.getText().toUpperCase());
            }

        });

        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setVisible(true);

    }
}
