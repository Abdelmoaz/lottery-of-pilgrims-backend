package com.example.lotteryofpilgrims.service;

import com.example.lotteryofpilgrims.entity.Candidate;
import com.example.lotteryofpilgrims.entity.Lottery;

import java.util.List;

public interface CandidateService {

    void saveCandidate(Candidate candidate);
    void saveAllCandidate(List<Candidate> candidates);
    Candidate getCandidate(int id);
    Candidate formatCandidateData(String line);
    List<Candidate> getAllCandidates(Lottery lottery);
    boolean validateCandidateDate(String data);

}
