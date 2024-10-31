package api;

import java.io.File;
import java.time.LocalDate;

public class DDCTest {
  public static void main(String[] args) {
    DailyDataCollector collector = new DailyDataCollector();
    LocalDate yesterday = LocalDate.now().minusDays(1);  // 어제 날짜 데이터 수집
    collector.collectAndSaveData(yesterday);

    String fileName = "data/data_" + yesterday.toString().replace("-", "") + ".csv";
    File file = new File(fileName);

    if (file.exists()) {
      System.out.println("Data collected and file saved: " + fileName);
    } else {
      System.out.println("Data collection failed or file not saved.");
    }
  }
}
