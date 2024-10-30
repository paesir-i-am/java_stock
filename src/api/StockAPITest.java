package api;

import java.util.List;

public class StockAPITest {
  public static void main(String[] args) {
    StockAPIClient apiClient = new StockAPIClient();
    String basDd = "20241028";

    List<String> result = apiClient.searchStocks(basDd);

    if(result.isEmpty()) {
      System.out.println("검색결과가 없습니다.");
    } else {
      System.out.println("검색결과");
      for(String s : result) {
        System.out.println(s);
      }
    }
  }
}
