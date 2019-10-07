import javax.swing.*;
import java.awt.*;

public class FtoC extends JFrame{
    public FtoC() {
        this.setTitle("Fahrenheit to Celsius Temperature Converter");
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("Fahrenheit to Celsius Temperature Converter", JLabel.CENTER);
        title.setForeground(Color.red);

        JTextField tempF = new JTextField("", 8);
        JLabel tempC = new JLabel("Celsius Temperature");

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(e -> {
            String temp = convertTemp(tempF.getText());
            tempC.setText(String.format("%s degrees Celsius", temp));
        });

        this.add(title, BorderLayout.NORTH);
        this.add(new JLabel("Fahrenheit temperature:"), BorderLayout.WEST);
        this.add(tempF, BorderLayout.CENTER);
        this.add(tempC, BorderLayout.EAST);
        this.add(convertButton, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(350, 110));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    String convertTemp(String tempInit){
        try {
            double temp = Double.parseDouble(tempInit);
            return String.format("%.1f", (5.0 / 9.0 * (temp - 32.0)));
        } catch (Exception e) {
            System.out.println(e);
            return "Number Parse Error";
        }
    }

    public static void main(String[] args) {
        JFrame frame = new FtoC();
        frame.setVisible(true);
    }
}
