package com.example.lotteryofpilgrims.service;

import com.example.lotteryofpilgrims.entity.Candidate;
import com.example.lotteryofpilgrims.entity.Lottery;
import com.example.lotteryofpilgrims.repo.CandidateRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    CandidateRepo candidateRepo;

    @Override
    public void saveCandidate(Candidate candidate) {

        log.info("Saving new candidate to database {}", candidate);
        candidateRepo.save(candidate);
    }

    @Override
    public void saveAllCandidate(List<Candidate> candidates) {
        for (Candidate candidate : candidates) {
            saveCandidate(candidate);
        }
    }

    @Override
    public Candidate getCandidate(int id) {
        log.info("Fetching candidate {}", id);
        Optional<Candidate> lottery = candidateRepo.findById(id);
        if (lottery.isPresent()) {
            return lottery.get();
        }
        return null;    }

    @Override
    public List<Candidate> getAllCandidates(Lottery lottery) {
        log.info("Fetching all candidates for lottery {}", lottery);
        List<Candidate> candidates = candidateRepo.findByLottery(lottery);
        return (candidateRepo.findByLottery(lottery) != null) ? candidates : new ArrayList<>();
    }

    @Override
    public boolean validateCandidateDate(String data) {

        if (data.equals("")) {
            return false;
        } else {
            String[] candidateData = data.split(",");
            if (candidateData.length != 3) {
                return false;
            }
            return validateCandidateId(candidateData[0]) && validateCandidateName(candidateData[1]) && validateCandidateAge(candidateData[2]);
        }
    }

    private boolean validateCandidateId(String id) {
        if (id == null || id.equals("")) {
            return false;
        }
        return validNumber(id);
    }

    private boolean validateCandidateName(String name) {
        if (name == null || name.equals("")) {
            return false;
        }
        return true;
    }

    private boolean validateCandidateAge(String age) {
        return validNumber(age);

    }


    private boolean validNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < 48 || s.charAt(i) > 57) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Candidate formatCandidateData(String line) {
        String[] candidateData = line.split(",");
        if (candidateData.length != 3) {
            return null;
        } else
            return new Candidate(Integer.valueOf(candidateData[0]), candidateData[1], Integer.valueOf(candidateData[2]), null);
    }

}
