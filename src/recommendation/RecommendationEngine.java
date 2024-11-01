package recommendation;

import calculation.StockCalculator;
import model.StockInfo;
import storage.DataStorage;

import java.util.*;
import java.util.stream.Collectors;

public class RecommendationEngine {
  private final DataStorage dataStorage;

  public RecommendationEngine(DataStorage dataStorage) {
    this.dataStorage = dataStorage;
  }

  public List<StockInfo> recommendStocks() {
    List<StockInfo> stockData = dataStorage.loadOrCollectThreeMonthData().stream()
        .filter(this::isDataValid)  // 파일 및 유효성 검사 추가
        .collect(Collectors.toList());

    // 종목별로 그룹화하여 상위 4개 스코어 계산
    Map<String, List<StockInfo>> groupedStocks = stockData.stream()
        .collect(Collectors.groupingBy(StockInfo::getIsuCd));

    return groupedStocks.entrySet().stream()
        .map(entry -> calculateStockScore(entry.getKey(), entry.getValue()))
        .filter(Objects::nonNull)
        .sorted(Comparator.comparingDouble(StockInfo::getScore).reversed())
        .limit(4)
        .collect(Collectors.toList());
  }

  private boolean isDataValid(StockInfo stock) {
    return !stock.getFlucRt().isEmpty() && !stock.getAccTrdVol().isEmpty() && !stock.getMktCap().isEmpty();
  }

  private StockInfo calculateStockScore(String isuCd, List<StockInfo> stockInfos) {
    List<StockInfo> validStocks = stockInfos.stream().filter(this::isDataValid).collect(Collectors.toList());

    if (validStocks.isEmpty()) return null;

    double avgFlucRate = validStocks.stream()
        .mapToDouble(stock -> StockCalculator.safeParseDouble(stock.getFlucRt()))
        .average().orElse(0.0);

    double avgVolume = validStocks.stream()
        .mapToDouble(stock -> StockCalculator.safeParseDouble(stock.getAccTrdVol()))
        .average().orElse(0.0);

    double mktCap = validStocks.stream()
        .mapToDouble(stock -> StockCalculator.safeParseDouble(stock.getMktCap()))
        .max().orElse(0.0);

    double score = avgFlucRate * 0.5 + avgVolume * 0.3 + mktCap * 0.2;

    StockInfo representative = validStocks.get(0);
    representative.setScore(score);
    return representative;
  }

  public void displayRecommendedStocks() {
    List<StockInfo> recommendedStocks = recommendStocks();
    if (recommendedStocks.isEmpty()) {
      System.out.println("추천할 종목이 없습니다.");
    } else {
      System.out.println("추천 종목 목록:");
      for (StockInfo stock : recommendedStocks) {
        System.out.printf("종목명: %s, 스코어: %.2f\n", stock.getIsuNm(), stock.getScore());
      }
    }
  }
}
