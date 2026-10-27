import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RadioButtonDemo extends JFrame {

    private JRadioButton bird, cat, dog, rabbit, pig;
    private ButtonGroup group;
    private PetDisplay petDisplay;
    private JLabel appTitle;

    public RadioButtonDemo() {
        setUndecorated(false); // keep normal frame
        setTitle("RadioButtonDemo");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Outer gray background
        JPanel outer = new JPanel();
        outer.setLayout(new BorderLayout());
        outer.setBackground(new Color(223, 230, 236));
        outer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title area
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(new Color(223, 230, 236));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        appTitle = new JLabel("JAVA (ASSIGNMENT 2)", SwingConstants.CENTER);
        appTitle.setFont(new Font("SansSerif", Font.BOLD, 34));
        appTitle.setForeground(Color.BLACK);

        titlePanel.add(appTitle, BorderLayout.CENTER);

        // top line
        JSeparator sep = new JSeparator();
        titlePanel.add(sep, BorderLayout.SOUTH);

        outer.add(titlePanel, BorderLayout.NORTH);

        // Main app content
        JPanel framePanel = new JPanel();
        framePanel.setLayout(new BorderLayout());
        framePanel.setBackground(new Color(223, 230, 236));
        framePanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        // Blue title bar inside app area
        JPanel blueBar = new JPanel(new BorderLayout());
        blueBar.setBackground(new Color(32, 118, 210));
        blueBar.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        JLabel appName = new JLabel("RadioButtonDemo");
        appName.setForeground(Color.WHITE);
        appName.setFont(new Font("SansSerif", Font.BOLD, 30));

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        controls.setOpaque(false);

        JButton min = createControlButton("-");
        JButton box = createControlButton("□");
        JButton close = createControlButton("✕");
        close.setBackground(new Color(245, 81, 81));

        controls.add(min);
        controls.add(box);
        controls.add(close);

        blueBar.add(appName, BorderLayout.WEST);
        blueBar.add(controls, BorderLayout.EAST);

        // Body inside panel
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(new Color(220, 220, 220));
        content.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        optionPanel.setBackground(new Color(230, 230, 230));
        optionPanel.setBorder(BorderFactory.createEmptyBorder(18, 25, 10, 10));

        bird = new JRadioButton("Bird");
        cat = new JRadioButton("Cat");
        dog = new JRadioButton("Dog");
        rabbit = new JRadioButton("Rabbit");
        pig = new JRadioButton("Pig");

        Font radioFont = new Font("SansSerif", Font.PLAIN, 26);
        bird.setFont(radioFont);
        cat.setFont(radioFont);
        dog.setFont(radioFont);
        rabbit.setFont(radioFont);
        pig.setFont(radioFont);

        bird.setOpaque(false);
        cat.setOpaque(false);
        dog.setOpaque(false);
        rabbit.setOpaque(false);
        pig.setOpaque(false);

        group = new ButtonGroup();
        group.add(bird);
        group.add(cat);
        group.add(dog);
        group.add(rabbit);
        group.add(pig);

        optionPanel.add(createRadioRow(bird));
        optionPanel.add(createRadioRow(cat));
        optionPanel.add(createRadioRow(dog));
        optionPanel.add(createRadioRow(rabbit));
        optionPanel.add(createRadioRow(pig));

        petDisplay = new PetDisplay("Pig");
        petDisplay.setPreferredSize(new Dimension(340, 260));

        // add listeners
        ActionListener selListener = e -> {
            JRadioButton selected = (JRadioButton) e.getSource();
            petDisplay.setPet(selected.getText());
            JOptionPane.showMessageDialog(
                    RadioButtonDemo.this,
                    "You selected: " + selected.getText(),
                    "Pet Selection",
                    JOptionPane.INFORMATION_MESSAGE
            );
        };

        bird.addActionListener(selListener);
        cat.addActionListener(selListener);
        dog.addActionListener(selListener);
        rabbit.addActionListener(selListener);
        pig.addActionListener(selListener);

        pig.setSelected(true);

        JPanel left = new JPanel(new BorderLayout());
        left.setBackground(new Color(230, 230, 230));
        left.add(optionPanel, BorderLayout.CENTER);

        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(new Color(210, 210, 210));
        right.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
        right.add(petDisplay, BorderLayout.CENTER);

        content.add(left, BorderLayout.WEST);
        content.add(right, BorderLayout.CENTER);

        framePanel.add(blueBar, BorderLayout.NORTH);
        framePanel.add(content, BorderLayout.CENTER);

        outer.add(framePanel, BorderLayout.CENTER);

        add(outer);
        setVisible(true);
    }

    private JPanel createRadioRow(JRadioButton button) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 6));
        row.setOpaque(false);
        row.add(button);
        return row;
    }

    private JButton createControlButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(30, 30));
        btn.setBackground(new Color(58, 145, 255));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 22));
        btn.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RadioButtonDemo());
    }
}

class PetDisplay extends JPanel {
    private String pet;

    public PetDisplay(String pet) {
        this.pet = pet;
        setBackground(new Color(238, 239, 239));
        setBorder(BorderFactory.createLineBorder(new Color(90, 90, 90), 1));
    }

    public void setPet(String pet) {
        this.pet = pet;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if ("Bird".equals(pet)) {
            drawBird(g2);
        } else if ("Cat".equals(pet)) {
            drawCat(g2);
        } else if ("Dog".equals(pet)) {
            drawDog(g2);
        } else if ("Rabbit".equals(pet)) {
            drawRabbit(g2);
        } else {
            drawPig(g2);
        }

        g2.dispose();
    }

    private void drawPig(Graphics2D g2) {
        int x = 60, y = 60;

        g2.setColor(new Color(235, 180, 190));
        g2.fillOval(x + 10, y + 15, 200, 130);

        g2.setColor(new Color(245, 230, 230));
        g2.fillOval(x + 40, y + 40, 60, 40);
        g2.fillOval(x + 130, y + 40, 60, 40);

        g2.setColor(new Color(20, 20, 20));
        g2.fillOval(x + 60, y + 60, 12, 12);
        g2.fillOval(x + 150, y + 60, 12, 12);

        g2.setColor(new Color(20, 20, 20));
        g2.drawArc(x + 75, y + 15, 80, 70, 180, 180);

        g2.setColor(new Color(100, 100, 100));
        g2.fillOval(x + 78, y + 85, 12, 12);
        g2.fillOval(x + 160, y + 85, 12, 12);

        g2.setColor(new Color(0, 0, 0));
        g2.fillOval(x + 110, y + 80, 45, 25);

        g2.setColor(new Color(240, 145, 150));
        g2.fillOval(x + 120, y + 85, 15, 8);

        g2.setColor(new Color(0, 0, 0));
        g2.drawLine(x + 100, y + 80, x + 82, y + 35);
        g2.drawLine(x + 145, y + 80, x + 170, y + 35);

        g2.setColor(new Color(80, 80, 80));
        g2.drawArc(x + 25, y + 110, 85, 35, 0, 180);
        g2.drawArc(x + 150, y + 110, 85, 35, 0, 180);
    }

    private void drawBird(Graphics2D g2) {
        int x = 70, y = 60;

        // body
        g2.setColor(new Color(245, 200, 80));
        g2.fillOval(x + 25, y + 40, 150, 90);

        // head
        g2.setColor(new Color(250, 215, 110));
        g2.fillOval(x + 100, y + 10, 75, 65);

        // eye
        g2.setColor(new Color(20, 20, 20));
        g2.fillOval(x + 128, y + 38, 8, 8);

        // beak
        g2.setColor(new Color(250, 160, 60));
        g2.fillPolygon(new int[]{x + 165, x + 200, x + 170},
                new int[]{y + 40, y + 52, y + 72}, 3);

        // wing
        g2.setColor(new Color(220, 180, 70));
        g2.fillOval(x + 80, y + 48, 60, 40);

        // legs
        g2.setColor(new Color(0, 0, 0));
        g2.drawLine(x + 112, y + 130, x + 98, y + 160);
        g2.drawLine(x + 150, y + 130, x + 165, y + 160);
    }

    private void drawCat(Graphics2D g2) {
        int x = 70, y = 60;

        g2.setColor(new Color(210, 170, 170));
        g2.fillOval(x + 15, y + 20, 180, 120);

        g2.setColor(new Color(210, 170, 170));
        g2.fillPolygon(new int[]{x + 35, x + 90, x + 75},
                new int[]{y + 25, y + 10, y + 60}, 3);
        g2.fillPolygon(new int[]{x + 155, x + 200, x + 150},
                new int[]{y + 25, y + 10, y + 60}, 3);

        g2.setColor(new Color(250, 250, 250));
        g2.fillOval(x + 78, y + 60, 36, 24);
        g2.fillOval(x + 150, y + 60, 36, 24);

        g2.setColor(new Color(0, 0, 0));
        g2.fillOval(x + 90, y + 68, 7, 7);
        g2.fillOval(x + 168, y + 68, 7, 7);

        g2.setColor(new Color(0, 0, 0));
        g2.drawLine(x + 118, y + 90, x + 150, y + 90);
        g2.fillOval(x + 116, y + 90, 30, 15);

        g2.setColor(new Color(0, 0, 0));
        g2.drawArc(x + 90, y + 100, 85, 28, 0, 180);
    }

    private void drawDog(Graphics2D g2) {
        int x = 70, y = 60;

        g2.setColor(new Color(205, 200, 160));
        g2.fillOval(x + 10, y + 25, 180, 120);

        g2.setColor(new Color(205, 200, 160));
        g2.fillOval(x + 150, y + 20, 60, 60);

        g2.setColor(new Color(250, 250, 250));
        g2.fillOval(x + 82, y + 65, 35, 22);
        g2.fillOval(x + 150, y + 65, 35, 22);

        g2.setColor(new Color(0, 0, 0));
        g2.fillOval(x + 92, y + 72, 7, 7);
        g2.fillOval(x + 166, y + 72, 7, 7);

        g2.setColor(new Color(0, 0, 0));
        g2.fillOval(x + 120, y + 86, 30, 14);

        g2.setColor(new Color(220, 130, 120));
        g2.fillOval(x + 120, y + 95, 35, 18);

        g2.setColor(new Color(0, 0, 0));
        g2.drawArc(x + 90, y + 102, 80, 24, 0, 180);
    }

    private void drawRabbit(Graphics2D g2) {
        int x = 70, y = 60;

        g2.setColor(new Color(220, 210, 220));
        g2.fillOval(x + 20, y + 25, 180, 120);

        g2.setColor(new Color(220, 210, 220));
        g2.fillOval(x + 20, y + 15, 45, 60);
        g2.fillOval(x + 160, y + 15, 45, 60);

        g2.setColor(new Color(250, 250, 250));
        g2.fillOval(x + 82, y + 65, 32, 22);
        g2.fillOval(x + 150, y + 65, 32, 22);

        g2.setColor(new Color(0, 0, 0));
        g2.fillOval(x + 90, y + 72, 7, 7);
        g2.fillOval(x + 165, y + 72, 7, 7);

        g2.setColor(new Color(0, 0, 0));
        g2.fillOval(x + 120, y + 88, 26, 12);

        g2.setColor(new Color(0, 0, 0));
        g2.drawLine(x + 108, y + 95, x + 110, y + 130);
        g2.drawLine(x + 150, y + 95, x + 148, y + 130);
    }
}
