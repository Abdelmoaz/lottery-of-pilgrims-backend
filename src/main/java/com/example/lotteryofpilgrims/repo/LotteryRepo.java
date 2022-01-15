package com.example.lotteryofpilgrims.repo;

import com.example.lotteryofpilgrims.entity.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryRepo extends JpaRepository<Lottery,Integer> {
}
