package recommendation;

import model.StockInfo;
import storage.DataStorage;

import java.util.List;

public class RecommendationTest {
  public static void main(String[] args) {
    DataStorage storage = new DataStorage();
    RecommendationEngine engine = new RecommendationEngine(storage);

    // displayRecommendedStocks 메서드 호출
    engine.displayRecommendedStocks();
  }
}
