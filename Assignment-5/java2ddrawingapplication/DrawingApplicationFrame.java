package java2ddrawingapplication;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;

public class DrawingApplicationFrame extends JFrame {

    // Create the panels for the top of the application. One panel for each
    // line and one to contain both of those panels.
    JPanel topLine = new JPanel();
    JPanel bottomLine = new JPanel();
    JPanel topPanel = new JPanel();

    // create the widgets for the firstLine Panel.
    JButton undoButton = new JButton("Undo");
    JButton clearButton = new JButton("Clear");

    String shapeLabels[] = {"Line", "Rectangle", "Oval"};
    JLabel shapeLabel = new JLabel("Shape: ");
    JComboBox shapeComboBox = new JComboBox<>(shapeLabels);

    JCheckBox fillCheckBox = new JCheckBox("Filled");

    //create the widgets for the secondLine Panel.
    JCheckBox gradientCheckBox = new JCheckBox("Use Gradient");

    JButton color1Button = new JButton("Color 1");
    JButton color2Button = new JButton("Color 2");

    JLabel widthLabel = new JLabel("Line Width: ");
    JTextField lineWidthBox = new JTextField("10", 3);

    JLabel dashLabel = new JLabel("Dash Length: ");
    JTextField dashLengthBox = new JTextField("15", 3);

    JCheckBox dashCheckBox = new JCheckBox("Dashed");

    // Variables for drawPanel.
    DrawPanel drawPanel = new DrawPanel();
    int lineWidth = 10;
    float dashLength = 15;
    Color color1 = Color.BLACK;
    Color color2 = Color.BLACK;
    ArrayList<MyShapes> shapes = new ArrayList<MyShapes>();

    // add status label
    JLabel statusLabel = new JLabel();

    // Constructor for DrawingApplicationFrame
    public DrawingApplicationFrame() {
        this.setLayout(new BorderLayout());
        topPanel.setLayout(new BorderLayout());
        // add widgets to panels
        // firstLine widgets
        topLine.add(undoButton);
        topLine.add(clearButton);
        topLine.add(shapeLabel);
        topLine.add(shapeComboBox);
        topLine.add(fillCheckBox);

        // secondLine widgets
        bottomLine.add(gradientCheckBox);
        bottomLine.add(color1Button);
        bottomLine.add(color2Button);
        bottomLine.add(widthLabel);
        bottomLine.add(lineWidthBox);
        bottomLine.add(dashLabel);
        bottomLine.add(dashLengthBox);
        bottomLine.add(dashCheckBox);

        // add top panel of two panels
        topPanel.add(topLine, BorderLayout.NORTH);
        topPanel.add(bottomLine, BorderLayout.SOUTH);

        // add topPanel to North, drawPanel to Center, and statusLabel to South
        this.add(topPanel, BorderLayout.NORTH);
        this.add(drawPanel, BorderLayout.CENTER);
        this.add(statusLabel, BorderLayout.SOUTH);

        //add listeners and event handlers
        // Buttons
        color1Button.addActionListener(l -> {
            color1 = JColorChooser.showDialog(null, "Select Color 1", color1);
        });
        color2Button.addActionListener(l -> {
            color2 = JColorChooser.showDialog(null, "Select Color 2", color2);
        });
        undoButton.addActionListener(l -> {
            try {
                shapes.remove(shapes.size() - 1);
                drawPanel.repaint();
            } catch(Exception e) {
                System.out.println(e);
            }
        });
        clearButton.addActionListener(l -> {
            shapes = new ArrayList<MyShapes>();
            drawPanel.repaint();
        });

        // Textboxes
        lineWidthBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkInput();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                checkInput();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkInput();
            }
            public void checkInput() {
                try {
                    lineWidth = Integer.parseInt(lineWidthBox.getText());
                } catch (Exception a) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Please enter a line width that is an integer greater than 0", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dashLengthBox.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkInput();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                checkInput();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkInput();
            }
            public void checkInput() {
                try {
                    dashLength = Float.parseFloat(dashLengthBox.getText());
                } catch (Exception a) {
                    JOptionPane.showMessageDialog(null, "Error: Please enter a dash length greater that is an integer greater than 0", 
                    "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Pack
        this.pack();
    }

    // Create method to build graphic class
    private MyShapes buildGraphic(Point start, Point end) {
        Paint paint;
        BasicStroke stroke;
        // Set paint color
        if (gradientCheckBox.isSelected()){
            paint = new GradientPaint(0, 0, color1, 50, 50, color2, true);
        } else {
            paint = new GradientPaint(0, 0, color1, 50, 50, color1, true);
        }
        // Set stroke type
        if (dashCheckBox.isSelected()) {
            float[] dash = {dashLength};
            stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dash, 0);
        } else {
            stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        }
        // Return selected shape
        if (shapeComboBox.getSelectedItem().toString() == "Line") {
            return new MyLine(start, end, paint, stroke);
        } else if (shapeComboBox.getSelectedItem().toString() == "Rectangle") {
            return new MyRectangle(start, end, paint, stroke, fillCheckBox.isSelected());
        } else if (shapeComboBox.getSelectedItem().toString() == "Oval") {
            return new MyOval(start, end, paint, stroke, fillCheckBox.isSelected());
        } else {
            System.out.println(String.format("%s is not a selectable shape", shapeComboBox.getSelectedItem().toString()));
            return null;
        }
    }

    // Create a private inner class for the DrawPanel.
    private class DrawPanel extends JPanel {

        Point start;
        Point end;
        
        // A tempShape array list is used to have deletion occur in the paintComponent method
        ArrayList<MyShapes> tempShapes = new ArrayList<MyShapes>();

        public DrawPanel() {
            setBackground(Color.WHITE);
            addMouseListener(new MouseHandler());
            addMouseMotionListener(new MouseHandler());
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            for (MyShapes shape : shapes) {
                shape.draw(g2d);
            }
            for (MyShapes shape : tempShapes) {
                shape.draw(g2d);
            }
            
            tempShapes = new ArrayList<MyShapes>();            
        }

        private class MouseHandler extends MouseAdapter implements MouseMotionListener {

            public void mousePressed(MouseEvent event) {
                start = event.getPoint();
            }

            public void mouseReleased(MouseEvent event) {
                end = event.getPoint();
                MyShapes shape = buildGraphic(start, end);
                if (shape != null) {
                    shapes.add(shape);
                    drawPanel.repaint();
                }
                start = new Point();
                end = new Point();
                }

            @Override
            public void mouseDragged(MouseEvent event) {
                Point temp = event.getPoint();
                statusLabel.setText(String.format("(%d, %d)", event.getX(), event.getY()));
                MyShapes shape = buildGraphic(start, temp);
                if (shape != null) {
                    tempShapes.add(shape);
                    drawPanel.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent event) {
                statusLabel.setText(String.format("(%d, %d)",event.getX(), event.getY()));
            }
        }

    }
}