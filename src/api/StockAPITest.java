package api;

import model.StockInfo;

import java.util.List;

public class StockAPITest {
  public static void main(String[] args) {
    StockAPIClient apiClient = new StockAPIClient();
    String basDd = "20241029";

    List<StockInfo> result = apiClient.searchStocks(basDd);

    if(result.isEmpty()) {
      System.out.println("검색결과가 없습니다.");
    } else {
      System.out.println("검색결과");
      for(StockInfo s : result) {
        System.out.println(s);
      }
    }
  }
}
