package calculation;

import model.StockInfo;
import java.util.List;

public class StockCalculator {

  // 평균 등락률 계산 메서드
  public static double calculateAverageFlucRate(List<StockInfo> stockData) {
    return stockData.stream()
        .mapToDouble(stock -> safeParseDouble(stock.getFlucRt()))
        .average()
        .orElse(0.0);
  }

  // 평균 거래량 계산 메서드
  public static double calculateAverageVolume(List<StockInfo> stockData) {
    return stockData.stream()
        .mapToDouble(stock -> safeParseDouble(stock.getAccTrdVol()))
        .average()
        .orElse(0.0);
  }

  // double 변환 메서드
  public static double safeParseDouble(String value) {
    try {
      return Double.parseDouble(value);
    } catch (NumberFormatException e) {
      System.out.println("변환 오류: " + value + ", 기본값 0.0 반환");
      return 0.0;
    }
  }
}
