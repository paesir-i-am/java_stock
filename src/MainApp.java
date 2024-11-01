package ui;

import javax.swing.*;
import java.awt.*;
import ui.HeaderPanel;
import ui.SearchPanel;
import ui.RecommendationPanel;
import ui.NewsPanel;

public class MainApp extends JFrame {
  public MainApp() {
    setTitle("Stock Application");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // 헤더 추가 (검색 기능 포함)
    HeaderPanel headerPanel = new HeaderPanel();
    add(headerPanel, BorderLayout.NORTH);

    // 검색 패널 추가
    SearchPanel searchPanel = new SearchPanel();
    add(searchPanel, BorderLayout.CENTER);

    // 추천 종목 패널 추가
    RecommendationPanel recommendationPanel = new RecommendationPanel();
    add(recommendationPanel, BorderLayout.WEST);

    // 뉴스 기사 패널 추가
    NewsPanel newsPanel = new NewsPanel();
    add(newsPanel, BorderLayout.SOUTH);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      MainApp app = new MainApp();
      app.setVisible(true);
    });
  }
}
