package api;

import utils.AuthClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.Config;

import java.util.ArrayList;
import java.util.List;

public class StockAPIClient {
  private static final String API_URL = "http://data-dbg.krx.co.kr/svc/apis/idx/krx_dd_trd.json";

  public List<String> searchStocks(String basDd) {
    List<String> stockList = new ArrayList<>();
    String accessToken = AuthClient.getAccessToken();  // Access Token 가져오기

    if(accessToken == null) {
      System.out.println("APIClient java - token이 null");
      return stockList;
    }

    System.out.println("APIClient java - token: " + accessToken);

    try {
      String fullUrl = API_URL + "?basDd=" + basDd;
      URL url = new URL(fullUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("AUTH_KEY", Config.getApiKey());

      // 응답 처리
      BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      StringBuilder result = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        result.append(line);
      }
      reader.close();

      // JSON 파싱
      JSONObject jsonObject = new JSONObject(result.toString());
      JSONArray jsonArray = jsonObject.getJSONArray("OutBlock_1");
      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject stockObject = jsonArray.getJSONObject(i);
        String stockName = stockObject.getString("IDX_NM");
        stockList.add(stockName);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return stockList;
  }
}
