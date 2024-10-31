package model;

public class StockInfo {
  private String basDd; //기준일자
  private String isuCd; //종목코드
  private String isuNm; //종목명
  private String mktNm; //시장구분
  private String sectTpNm;  //소속부
  private String tddClsPrc; //종가
  private String cmpPrevDdPrc;  //대비
  private String flucRt;  //등락률
  private String tddOpnPrc; //시가
  private String tddHgPrc;  //고가
  private String tddLwPrc;  //저가
  private String accTrdVol; //거래량
  private String accTrdVal; //거래대금
  private String mktCap;  //시가총액
  private String listShrs;  //상장주식수

  // 생성자, getter 및 setter
  public StockInfo(String basDd, String isuCd, String isuNm, String mktNm, String sectTpNm, String tddClsPrc, String cmpPrevDdPrc, String flucRt, String tddOpnPrc, String tddHgPrc, String tddLwPrc, String accTrdVol, String accTrdVal, String mktCap, String listShrs) {
    this.basDd = basDd;
    this.isuCd = isuCd;
    this.isuNm = isuNm;
    this.mktNm = mktNm;
    this.sectTpNm = sectTpNm;
    this.tddClsPrc = tddClsPrc;
    this.cmpPrevDdPrc = cmpPrevDdPrc;
    this.flucRt = flucRt;
    this.tddOpnPrc = tddOpnPrc;
    this.tddHgPrc = tddHgPrc;
    this.tddLwPrc = tddLwPrc;
    this.accTrdVol = accTrdVol;
    this.accTrdVal = accTrdVal;
    this.mktCap = mktCap;
    this.listShrs = listShrs;
  }

  @Override
  public String toString() {
    return String.format(
        "BAS_DD (기준일자): %s, ISU_CD (종목코드): %s, ISU_NM (종목명): %s, MKT_NM (시장구분): %s, SECT_TP_NM (소속부): %s, " +
            "TDD_CLSPRC (종가): %s, CMPPREVDD_PRC (대비): %s, FLUC_RT (등락률): %s, TDD_OPNPRC (시가): %s, TDD_HGPRC (고가): %s, " +
            "TDD_LWPRC (저가): %s, ACC_TRDVOL (거래량): %s, ACC_TRDVAL (거래대금): %s, MKTCAP (시가총액): %s, LIST_SHRS (상장주식수): %s",
        basDd, isuCd, isuNm, mktNm, sectTpNm, tddClsPrc, cmpPrevDdPrc, flucRt, tddOpnPrc, tddHgPrc, tddLwPrc, accTrdVol, accTrdVal, mktCap, listShrs
    );
  }

  public String getBasDd() {
    return basDd;
  }

  public void setBasDd(String basDd) {
    this.basDd = basDd;
  }

  public String getIsuCd() {
    return isuCd;
  }

  public void setIsuCd(String isuCd) {
    this.isuCd = isuCd;
  }

  public String getIsuNm() {
    return isuNm;
  }

  public void setIsuNm(String isuNm) {
    this.isuNm = isuNm;
  }

  public String getMktNm() {
    return mktNm;
  }

  public void setMktNm(String mktNm) {
    this.mktNm = mktNm;
  }

  public String getSectTpNm() {
    return sectTpNm;
  }

  public void setSectTpNm(String sectTpNm) {
    this.sectTpNm = sectTpNm;
  }

  public String getTddClsPrc() {
    return tddClsPrc;
  }

  public void setTddClsPrc(String tddClsPrc) {
    this.tddClsPrc = tddClsPrc;
  }

  public String getCmpPrevDdPrc() {
    return cmpPrevDdPrc;
  }

  public void setCmpPrevDdPrc(String cmpPrevDdPrc) {
    this.cmpPrevDdPrc = cmpPrevDdPrc;
  }

  public String getFlucRt() {
    return flucRt;
  }

  public void setFlucRt(String flucRt) {
    this.flucRt = flucRt;
  }

  public String getTddOpnPrc() {
    return tddOpnPrc;
  }

  public void setTddOpnPrc(String tddOpnPrc) {
    this.tddOpnPrc = tddOpnPrc;
  }

  public String getTddHgPrc() {
    return tddHgPrc;
  }

  public void setTddHgPrc(String tddHgPrc) {
    this.tddHgPrc = tddHgPrc;
  }

  public String getTddLwPrc() {
    return tddLwPrc;
  }

  public void setTddLwPrc(String tddLwPrc) {
    this.tddLwPrc = tddLwPrc;
  }

  public String getAccTrdVol() {
    return accTrdVol;
  }

  public void setAccTrdVol(String accTrdVol) {
    this.accTrdVol = accTrdVol;
  }

  public String getAccTrdVal() {
    return accTrdVal;
  }

  public void setAccTrdVal(String accTrdVal) {
    this.accTrdVal = accTrdVal;
  }

  public String getMktCap() {
    return mktCap;
  }

  public void setMktCap(String mktCap) {
    this.mktCap = mktCap;
  }

  public String getListShrs() {
    return listShrs;
  }

  public void setListShrs(String listShrs) {
    this.listShrs = listShrs;
  }
}
