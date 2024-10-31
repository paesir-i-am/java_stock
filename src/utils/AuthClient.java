package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class AuthClient {
  private static final String TOKEN_URL = "https://ktrs.krx.co.kr/openapi/svc/auth/token";

  // Access Token 발급 메서드
  public static String getAccessToken() {
    try {
      String apiKey = Config.getApiKey();
      String body = "grant_type=client_credentials&apikey=" + apiKey;
      JSONObject response = sendPostRequest(TOKEN_URL, body, "application/x-www-form-urlencoded");

      System.out.println("token 발급 성공");
      System.out.println("response: " + response.toString());

      String accessToken = response.optString("access_token","");
      if(accessToken.isEmpty()) {
        System.out.println("token isEmpty");
      } else{
        System.out.println("token : " + accessToken);
      }
        return accessToken;
//      return response.getString("access_token");
    } catch (Exception e) {
      e.printStackTrace();


      System.out.println("token 발급실패");
      return null;
    }
  }

  // 공통 HTTP POST 메서드
  private static JSONObject sendPostRequest(String urlStr, String body, String contentType) throws Exception {
    URL url = new URL(urlStr);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", contentType);
    conn.setDoOutput(true);

    try (OutputStream os = conn.getOutputStream()) {
      os.write(body.getBytes());
    }

    return parseResponse(conn);
  }

  // 공통 HTTP GET 메서드
  public static JSONObject sendGetRequest(String urlStr, String accessToken) throws Exception {
    URL url = new URL(urlStr);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Authorization", "Bearer " + accessToken);

    return parseResponse(conn);
  }

  // 응답 파싱 메서드
  private static JSONObject parseResponse(HttpURLConnection conn) throws Exception {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
      StringBuilder result = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        result.append(line);
      }
      return new JSONObject(result.toString());
    }
  }
}
