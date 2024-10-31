package ui;

import api.StockAPIClient;
import model.StockInfo;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchPanel extends JPanel {
  private JTextField searchField;
  private JComboBox<String> searchResults;
  private StockAPIClient apiClient;

  public SearchPanel() {
    apiClient = new StockAPIClient();

    setLayout(new BorderLayout());

    // Search input field
    searchField = new JTextField();
    searchField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        String query = searchField.getText();
        if (!query.isEmpty()) {
          updateSearchResults(query);
        } else {
          searchResults.removeAllItems();
        }
      }
    });

    // Dropdown search results
    searchResults = new JComboBox<>();
    searchResults.setPreferredSize(new Dimension(200, 25));

    add(searchField, BorderLayout.NORTH);
    add(searchResults, BorderLayout.SOUTH);
  }

  private void updateSearchResults(String query) {
    // Get result using API
    List<StockInfo> results = apiClient.searchStocks(query);

    // Update the results to comboBox
    searchResults.removeAllItems();
    for (StockInfo stock : results) {
      searchResults.addItem(stock.getIsuNm());  // 예시로 종목명만 표시
    }
  }
}
