package stock.mapper;

import stock.po.StockInfo;

import java.util.List;

/**
 * Created by heiqie on 2017/1/18.
 */
public interface StockInfoMapper {
    int insertStockInfo(StockInfo stockInfo);

    List<StockInfo> selectAllStockInfo();
}
