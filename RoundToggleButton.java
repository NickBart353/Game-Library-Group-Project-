import javax.swing.*;
import java.awt.*;

public class RoundToggleButton extends JToggleButton {

    public RoundToggleButton(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);  
        setBorderPainted(false);
        setOpaque(false);             
        setFont(new Font("Segoe UI", Font.BOLD, 15));
        setForeground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        // Antialiasing für smooth edges
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Hintergrundfarbe je nach Zustand
        if (getModel().isPressed() || isSelected()) {
            g2.setColor(new Color(60, 60, 60));  // dunkler, wenn gedrückt oder aktiv
        } else if (getModel().isRollover()) {
            g2.setColor(new Color(90, 90, 90));  // etwas heller beim Hover
        } else {
            g2.setColor(new Color(80, 80, 80));  // normaler Zustand
        }

        // Hintergrund zeichnen als runde Form
        g2.fillRoundRect(0, 0, width, height, height, height); 

        // Text zeichnen
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getAscent();
        g2.setColor(getForeground());
        g2.drawString(getText(), (width - textWidth) / 2, (height + textHeight) / 2 - 3);

        g2.dispose();
    }
}