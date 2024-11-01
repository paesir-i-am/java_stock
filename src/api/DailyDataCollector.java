package api;

import model.StockInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class DailyDataCollector {
  private final StockAPIClient apiClient;

  public DailyDataCollector() {
    this.apiClient = new StockAPIClient();
  }

  // 특정 날짜의 데이터를 수집해 CSV 파일로 저장
  public void collectAndSaveData(LocalDate date) {
    String formattedDate = date.toString().replace("-", "");  // YYYYMMDD 형식
    List<StockInfo> stockData = apiClient.searchStocks(formattedDate);

    if (stockData.isEmpty()) {
      System.out.println("해당 날짜에 데이터가 없습니다: " + date);
      return;
    }

    saveDataToCSV(stockData, formattedDate);
  }

  // 데이터를 CSV 파일로 저장
  private void saveDataToCSV(List<StockInfo> stockData, String date) {
    String directory = "data";
    File dir = new File(directory);
    if (!dir.exists()) dir.mkdirs();
    String fileName = directory + "/data_" + date + ".csv";
    try (FileWriter writer = new FileWriter(fileName)) {
      // CSV 헤더 작성
      writer.write("BAS_DD,ISU_CD,ISU_NM,MKT_NM,SECT_TP_NM,TDD_CLSPRC,CMPPREVDD_PRC,FLUC_RT,TDD_OPNPRC,TDD_HGPRC,TDD_LWPRC,ACC_TRDVOL,ACC_TRDVAL,MKTCAP,LIST_SHRS\n");

      // CSV 내용 작성
      for (StockInfo stock : stockData) {
        writer.write(stock.toCSVString() + "\n");  // StockInfo 클래스에 toCSVString 메서드 추가 필요
      }
//      System.out.println("데이터 저장 완료: " + fileName);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
