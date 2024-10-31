package storage;

import model.StockInfo;
import java.util.List;

public class DSTest {
  public static void main(String[] args) {
    DataStorage storage = new DataStorage();
    List<StockInfo> dataList = storage.loadOrCollectThreeMonthData();  // 자동 수집 기능 포함

    if (dataList.isEmpty()) {
      System.out.println("3개월 데이터가 없습니다.");
    } else {
      System.out.println("3개월 데이터 로드 성공");
      }
    }
  }

