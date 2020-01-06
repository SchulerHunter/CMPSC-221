import javax.swing.*;
import java.awt.*;

public class FtoC extends JFrame{
    public FtoC() {
        // Set meta-deta
        this.setTitle("Fahrenheit to Celsius Temperature Converter");
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(350, 110));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Generate title
        JLabel title = new JLabel("Fahrenheit to Celsius Temperature Converter", JLabel.CENTER);
        title.setForeground(Color.red);

        // Generate temp fields
        JTextField tempF = new JTextField("", 8);
        JLabel tempC = new JLabel("Celsius Temperature");

        // Generate convert button, set callback
        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(e -> {
            String temp = convertTemp(tempF.getText());
            tempC.setText(String.format("%s degrees Celsius", temp));
        });

        // Add items
        this.add(title, BorderLayout.NORTH);
        this.add(new JLabel("Fahrenheit temperature:"), BorderLayout.WEST);
        this.add(tempF, BorderLayout.CENTER);
        this.add(tempC, BorderLayout.EAST);
        this.add(convertButton, BorderLayout.SOUTH);

        // Pack
        this.pack();
    }

    String convertTemp(String tempInit){
        // Set try catch for string to double conversion
        try {
            double temp = Double.parseDouble(tempInit);
            // Convert format of float to 1 decimal
            return String.format("%.1f", (5.0 / 9.0 * (temp - 32.0)));
        } catch (Exception e) {
            // Log error and return string about number
            System.out.println(e);
            return "Number Parse Error";
        }
    }

    public static void main(String[] args) {
        // Create new frame
        JFrame frame = new FtoC();
        // Show the frame
        frame.setVisible(true);
    }
}
