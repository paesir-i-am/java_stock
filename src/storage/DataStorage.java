package storage;

import api.DailyDataCollector;
import model.StockInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
  private final DailyDataCollector collector;

  public DataStorage() {
    this.collector = new DailyDataCollector();
  }

  // 3개월 간의 데이터를 로드하고, 없는 경우 자동 수집
  public List<StockInfo> loadOrCollectThreeMonthData() {
    List<StockInfo> dataList = new ArrayList<>();
    LocalDate today = LocalDate.now();
    LocalDate threeMonthsAgo = today.minusMonths(3);

    for (LocalDate date = threeMonthsAgo; date.isBefore(today); date = date.plusDays(1)) {
      String formattedDate = date.toString().replace("-", "");
      String fileName = "data/data_" + formattedDate + ".csv";
      File file = new File(fileName);

      // 파일이 없는 경우 데이터를 수집
      if (!file.exists()) {
        System.out.println("파일이 없습니다. 데이터 수집 중: " + fileName);
        collector.collectAndSaveData(date);
        file = new File(fileName);  // 파일이 새로 생겼는지 확인
      }

      // 파일이 존재할 경우 로드
      if (file.exists()) {
        dataList.addAll(loadDataFromCSV(fileName));
        System.out.println("파일을 성공적으로 로드했습니다: " + fileName);
      } else {
        System.out.println("비영업일로 데이터가 없습니다: " + fileName);
      }
    }
    return dataList;
  }

  // CSV 파일에서 데이터를 로드
  private List<StockInfo> loadDataFromCSV(String fileName) {
    List<StockInfo> data = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line = reader.readLine(); // 첫 줄 헤더 건너뜀
      while ((line = reader.readLine()) != null) {
        StockInfo stockInfo = parseCSVLineToStockInfo(line);
        data.add(stockInfo);
      }
    } catch (IOException e) {
      System.out.println("파일을 읽는 중 오류 발생: " + fileName);
      e.printStackTrace();
    }
    return data;
  }

  // CSV 데이터를 StockInfo 객체로 변환하는 메서드
  private StockInfo parseCSVLineToStockInfo(String line) {
    String[] fields = line.split(",");
    return new StockInfo(fields[0], fields[1], fields[2], fields[3], fields[4],
        fields[5], fields[6], fields[7], fields[8], fields[9],
        fields[10], fields[11], fields[12], fields[13], fields[14]);
  }
}
