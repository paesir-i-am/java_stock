package ui;

import javax.swing.*;
import java.awt.*;

class RecommendationPanel extends JPanel {
  public RecommendationPanel() {
    setLayout(new BorderLayout());
    JLabel label = new JLabel("추천 종목 패널");
    add(label, BorderLayout.CENTER);
  }
}