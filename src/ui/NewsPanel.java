package ui;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class NewsPanel extends JPanel {
  private DefaultListModel<String> newsListModel;
  private JList<String> newsList;
  private List<String> newsUrls;

  public NewsPanel() {
    setLayout(new BorderLayout());
    newsListModel = new DefaultListModel<>();
    newsList = new JList<>(newsListModel);
    newsList.setCellRenderer(new NewsCellRenderer());  // 커스텀 렌더러 설정
    newsUrls = new ArrayList<>();

    newsList.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int index = newsList.locationToIndex(e.getPoint());
          openWebPage(newsUrls.get(index));
        }
      }
    });

    add(new JScrollPane(newsList), BorderLayout.CENTER);
    loadNews("주식");
  }

  // 네이버 뉴스 스크래핑
  public void loadNews(String query) {
    try {
      String searchQuery = query + " 뉴스";
      Document doc = Jsoup.connect("https://search.naver.com/search.naver?query=" + searchQuery)
          .userAgent("Mozilla/5.0").get();

      Elements newsElements = doc.select(".news_tit");
      Elements summaries = doc.select(".news_dsc");

      for (int i = 0; i < newsElements.size(); i++) {
        String title = newsElements.get(i).text();
        String summary = summaries.get(i).text();
        String url = newsElements.get(i).absUrl("href");

        newsListModel.addElement("<html><b>" + title + "</b><br><p>" + summary + "</p></html>");
        newsUrls.add(url);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 웹 페이지 열기
  private void openWebPage(String url) {
    try {
      Desktop.getDesktop().browse(new URI(url));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 커스텀 셀 렌더러
  static class NewsCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
      JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
      label.setVerticalAlignment(SwingConstants.TOP);
      return label;
    }
  }
}
