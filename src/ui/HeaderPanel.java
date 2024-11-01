package ui;

import model.StockInfo;
import storage.DataStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class HeaderPanel extends JPanel {
  private JTextField searchField;
  private JPopupMenu suggestionsPopup;
  private DataStorage dataStorage;
  private int currentIndex = -1;

  public HeaderPanel() {
    dataStorage = new DataStorage(); // 데이터 저장소 초기화
    setLayout(new BorderLayout());

    // 검색 창과 버튼 패널
    JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    searchField = new JTextField(20);
    JButton searchButton = new JButton("검색");
    searchPanel.add(searchField);
    searchPanel.add(searchButton);
    add(searchPanel, BorderLayout.WEST);

    // 로그인 버튼
    JButton loginButton = new JButton("로그인");
    add(loginButton, BorderLayout.EAST);

    // 검색어 입력 시 드롭다운 메뉴
    suggestionsPopup = new JPopupMenu();
    searchField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          if (suggestionsPopup.isVisible()) {
            currentIndex = Math.min(currentIndex + 1, suggestionsPopup.getComponentCount() - 1);
            highlightSuggestion();
          }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
          if (suggestionsPopup.isVisible()) {
            currentIndex = Math.max(currentIndex - 1, 0);
            highlightSuggestion();
          }
        } else {
          String query = searchField.getText();
          if (!query.isEmpty()) {
            updateSuggestions(query);
          } else {
            suggestionsPopup.setVisible(false);
          }
        }
      }
    });
  }

  // 검색 제안 업데이트
  private void updateSuggestions(String query) {
    suggestionsPopup.removeAll();
    currentIndex = -1; // 초기화

    List<StockInfo> stockInfos = dataStorage.loadLatestData();
    List<String> suggestions = stockInfos.stream()
        .map(StockInfo::getIsuNm)
        .filter(name -> name.contains(query))
        .distinct() // 중복 방지
        .collect(Collectors.toList());

    for (String suggestion : suggestions) {
      JMenuItem item = new JMenuItem(suggestion);
      item.setFocusable(false); // 포커스를 받지 않도록 설정
      item.addActionListener(e -> {
        searchField.setText(suggestion);
        suggestionsPopup.setVisible(false);
        performSearch(suggestion); // 검색 수행 메서드 호출
      });
      suggestionsPopup.add(item);
    }

    if (!suggestions.isEmpty()) {
      suggestionsPopup.show(searchField, 0, searchField.getHeight());
      searchField.requestFocusInWindow();
    } else {
      suggestionsPopup.setVisible(false);
    }
  }

  // 검색 수행 메서드
  private void performSearch(String query) {
    System.out.println("검색 수행: " + query);
    // 검색 기능 구현
  }

  // 현재 선택된 제안을 하이라이트
  private void highlightSuggestion() {
    if (currentIndex >= 0 && currentIndex < suggestionsPopup.getComponentCount()) {
      JMenuItem item = (JMenuItem) suggestionsPopup.getComponent(currentIndex);
      searchField.setText(item.getText());
    }
  }
}
