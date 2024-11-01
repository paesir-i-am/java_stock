package news;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

public class NewsFetcher {
  private List<String> newsUrls;

  public NewsFetcher() {
    newsUrls = new ArrayList<>();
  }

  public List<NewsItem> fetchNews(String query) {
    List<NewsItem> newsItems = new ArrayList<>();
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

        newsItems.add(new NewsItem(title, summary, url));
        newsUrls.add(url);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return newsItems;
  }

  public List<String> getNewsUrls() {
    return newsUrls;
  }

  public static class NewsItem {
    private final String title;
    private final String summary;
    private final String url;

    public NewsItem(String title, String summary, String url) {
      this.title = title;
      this.summary = summary;
      this.url = url;
    }

    public String getTitle() {
      return title;
    }

    public String getSummary() {
      return summary;
    }

    public String getUrl() {
      return url;
    }
  }
}
