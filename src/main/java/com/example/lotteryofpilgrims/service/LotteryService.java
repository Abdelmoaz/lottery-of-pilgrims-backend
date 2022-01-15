package com.example.lotteryofpilgrims.service;

import com.example.lotteryofpilgrims.entity.Lottery;
import org.springframework.web.multipart.MultipartFile;

public interface LotteryService {

    void saveLottery(Lottery lottery);
    Lottery getLottery(int id);
    boolean uploadFile (MultipartFile file) throws Exception;
}
