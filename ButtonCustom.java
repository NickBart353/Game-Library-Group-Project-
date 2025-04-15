import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ButtonCustom extends JButton {

    // aus dem TUT https://www.youtube.com/watch?v=nnLcyV9XNgc 
    private ButtonStyle style = ButtonStyle.PRIMARY;
    private ButtonColor currentStyle = new ButtonColor(ButtonStyle.PRIMARY);
    private int round = 10;

    public ButtonCustom() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(8, 8, 8, 8));
        setForeground(style.foreground);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                currentStyle.setBackground(currentStyle.backgroundHover);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                currentStyle.setBackground(style.background);
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                currentStyle.setBackground(style.backgroundPress);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                currentStyle.setBackground(style.background);
                repaint();
            }
        });
    }

    public ButtonStyle getStyle() {
        return style;
    }

    public void setStyle(ButtonStyle style) {
        if (this.style != style) {
            this.style = style;
            currentStyle.changeStyle(style);
            setForeground(style.foreground);
            repaint();
        }
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
        int width = getWidth();
        int height = getHeight();
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, round, round));
        g2.setColor(currentStyle.background);
        g2.fill(area);
        g2.dispose();
        drawText(g);
    }

    private void drawText(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    FontMetrics fm = g2.getFontMetrics();
    String text = getText();
    Icon icon = getIcon();

    int iconTextGap = getIconTextGap(); // Abstand Icon <-> Text
    int textWidth = fm.stringWidth(text);
    int textHeight = fm.getAscent();

    int iconWidth = 0;
    int iconHeight = 0;

    if (icon != null) {
        iconWidth = icon.getIconWidth();
        iconHeight = icon.getIconHeight();
    }

    // Gesamte Breite (Icon + Gap + Text)
    int totalWidth = iconWidth + (icon != null && !text.isEmpty() ? iconTextGap : 0) + textWidth;

    // Zentrieren
    int startX = (getWidth() - totalWidth) / 2;
    int centerY = (getHeight() - Math.max(iconHeight, textHeight)) / 2;

    // Zeichne Icon
    if (icon != null) {
        icon.paintIcon(this, g2, startX, centerY);
    }

    // Zeichne Text
    g2.setFont(getFont());
    g2.setColor(getForeground());
    int textX = startX + iconWidth + (icon != null ? iconTextGap : 0);
    int textY = (getHeight() + textHeight) / 2 - 2;
    g2.drawString(text, textX, textY);

    g2.dispose();
}

    // Styles der Buttons um diese auszuwählen
    public enum ButtonStyle {
        PRIMARY(new Color(0, 172, 126), Color.WHITE, new Color(4, 205, 151), new Color(2, 111, 82)),
        SECONDARY(new Color(203, 209, 219), Color.BLACK, new Color(230, 239, 255), new Color(81, 92, 108)),
        DESTRUCTIVE(new Color(255, 138, 48), Color.WHITE, new Color(255, 161, 90), new Color(198, 86, 0)),
        DARK_PRIMARY(new Color(31, 31, 90), new Color(255, 255, 255), new Color(30, 50, 70), new Color(90, 130, 180)),
        DARK_ACCENT(new Color(40, 20, 80), new Color(255, 255, 255), new Color(70, 40, 100), new Color(160, 100, 220)),
        DARK_WARNING(new Color(60, 25, 25), new Color(255, 220, 200), new Color(90, 40, 40), new Color(200, 80, 80)),
    
        // Neue Styles
        DISABLED_DARK(new Color(60, 60, 60), new Color(120, 120, 120), new Color(60, 60, 60), new Color(60, 60, 60)),
        DISABLED_LIGHT(new Color(220, 220, 220), new Color(160, 160, 160), new Color(220, 220, 220), new Color(220, 220, 220));
    
        private final Color background;
        private final Color foreground;
        private final Color backgroundHover;
        private final Color backgroundPress;
    
        ButtonStyle(Color background, Color foreground, Color backgroundHover, Color backgroundPress) {
            this.background = background;
            this.foreground = foreground;
            this.backgroundHover = backgroundHover;
            this.backgroundPress = backgroundPress;
        }
    }
    

    protected class ButtonColor {
        private Color background;
        private Color backgroundHover;
        
        public ButtonColor(ButtonStyle style) {
            changeStyle(style);
        }
        public void changeStyle(ButtonStyle style) {
            this.background = style.background;
            
            this.backgroundHover = style.backgroundHover;   
        }

        public void setBackground(Color background) {
            this.background = background;
        }
    }
}