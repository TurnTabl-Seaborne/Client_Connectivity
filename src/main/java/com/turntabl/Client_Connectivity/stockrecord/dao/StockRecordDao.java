package com.turntabl.Client_Connectivity.stockrecord.dao;

import com.turntabl.Client_Connectivity.stockrecord.model.StockRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRecordDao extends JpaRepository<StockRecord,Integer> {
    StockRecord findAllByStockRecordId(Integer id);
}
