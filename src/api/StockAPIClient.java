package api;

import model.StockInfo;
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
  private static final String API_URL = "http://data-dbg.krx.co.kr/svc/apis/sto/stk_bydd_trd.json";

  public List<StockInfo> searchStocks(String basDd) {
    List<StockInfo> stockList = new ArrayList<>();
    String accessToken = AuthClient.getAccessToken();

/*
    if (accessToken == null) {
      System.out.println("APIClient java - token이 null");
      return stockList;
    }
*/

//    System.out.println("APIClient java - token: " + accessToken);

    try {
      String fullUrl = API_URL + "?basDd=" + basDd;
      URL url = new URL(fullUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("AUTH_KEY", Config.getApiKey());

      BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      StringBuilder result = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        result.append(line);
      }
      reader.close();

//      System.out.println("응답 데이터 : " + result.toString());

      JSONObject jsonObject = new JSONObject(result.toString());
      JSONArray jsonArray = jsonObject.getJSONArray("OutBlock_1");
      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject stockObject = jsonArray.getJSONObject(i);

        StockInfo stockInfo = new StockInfo(
            stockObject.getString("BAS_DD"),
            stockObject.getString("ISU_CD"),
            stockObject.getString("ISU_NM"),
            stockObject.getString("MKT_NM"),
            stockObject.getString("SECT_TP_NM"),
            stockObject.getString("TDD_CLSPRC"),
            stockObject.getString("CMPPREVDD_PRC"),
            stockObject.getString("FLUC_RT"),
            stockObject.getString("TDD_OPNPRC"),
            stockObject.getString("TDD_HGPRC"),
            stockObject.getString("TDD_LWPRC"),
            stockObject.getString("ACC_TRDVOL"),
            stockObject.getString("ACC_TRDVAL"),
            stockObject.getString("MKTCAP"),
            stockObject.getString("LIST_SHRS")
        );

        stockList.add(stockInfo);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return stockList;
  }
}
