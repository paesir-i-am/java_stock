package ui;

import javax.swing.*;

public class NewsPanelTest {
  public static void main(String[] args) {
    // JFrame 설정
    JFrame frame = new JFrame("News Panel Test");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 400);

    // NewsPanel 추가
    NewsPanel newsPanel = new NewsPanel();
    frame.add(newsPanel);

    frame.setVisible(true);
  }
}
