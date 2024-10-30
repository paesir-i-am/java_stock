package utils;

import java.io.InputStream;
import java.util.Properties;

public class Config {
  public static String getApiKey() {
    Properties prop = new Properties();
    try (InputStream input = Config.class.getClassLoader().getResourceAsStream("Resources/config.properties")) {
      if (input == null) {
        System.out.println("config.properties 파일을 찾을 수 없습니다.");
        return null;
      }
      prop.load(input);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return prop.getProperty("apikey");
  }
}
