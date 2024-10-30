package ui;

import api.StockAPIClient;

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

    //search input field
    searchField = new JTextField();
    searchField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        String query = searchField.getText();
        if(!query.isEmpty()) {
          updateSearchResults(query);
        }else {
          searchResults.removeAllItems();
        }
      }
    });

    //dropdown search results
    searchResults = new JComboBox<>();
    searchResults.setPreferredSize(new Dimension(200, 25));

    add(searchField, BorderLayout.NORTH);
    add(searchResults, BorderLayout.SOUTH);
  }

  private void updateSearchResults(String query) {
    //get result using API
    List<String> results = apiClient.searchStocks(query);

    //update the results to comboBox
    searchResults.removeAllItems();
    for(String result : results){
      searchResults.addItem(result);
    }
  }
}
