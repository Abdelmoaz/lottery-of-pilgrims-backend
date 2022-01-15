package com.example.lotteryofpilgrims.repo;

import com.example.lotteryofpilgrims.entity.Candidate;
import com.example.lotteryofpilgrims.entity.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepo extends JpaRepository<Candidate,Integer> {

    List<Candidate> findByLottery(Lottery lottery);
}
