package com.turntabl.Client_Connectivity.stockrecord.controller;


import com.turntabl.Client_Connectivity.exchangeorder.model.ExchangeOrder;
import com.turntabl.Client_Connectivity.stockrecord.dao.StockRecordDao;
import com.turntabl.Client_Connectivity.stockrecord.model.StockRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StockRecordController {

    @Autowired
    private final StockRecordDao stocks;

    public StockRecordController(StockRecordDao stocks) {
        this.stocks = stocks;
    }

    @GetMapping("/api/stocks")
    List<StockRecord> getAllStocks(){
        return stocks.findAll();
    }

    @GetMapping("/api/stocks/{id}")
    StockRecord getAllStock(@PathVariable Integer id){
        return stocks.findAllByStockRecordId(id);
    }

    @PostMapping("/api/stocks")
    StockRecord createStock(@RequestBody StockRecord stock){
        return  stocks.save(stock);
    }

    @PutMapping("/api/stocks/{id}")
    StockRecord updateStock(@PathVariable int id, @RequestBody StockRecord newStockRecord){

        StockRecord stockRecord = stocks.findAllByStockRecordId(id);
        stockRecord.setQuantity(newStockRecord.getQuantity());
        stockRecord.setTicker(newStockRecord.getTicker());
        stockRecord.setUser(newStockRecord.getUser());

        return stocks.save(stockRecord);
    }

}
