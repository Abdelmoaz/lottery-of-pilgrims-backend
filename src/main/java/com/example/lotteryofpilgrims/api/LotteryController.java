package com.example.lotteryofpilgrims.api;

import com.example.lotteryofpilgrims.entity.Candidate;
import com.example.lotteryofpilgrims.entity.Lottery;
import com.example.lotteryofpilgrims.service.CandidateService;
import com.example.lotteryofpilgrims.service.LotteryService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class LotteryController {

    @Autowired
    LotteryService lotteryService;
    @Autowired
    CandidateService candidateService;

    @PostMapping("/lottery/upload")
    ResponseEntity<?> uploadLottery(@RequestParam("file") MultipartFile file) {

        try {
            if (!lotteryService.uploadFile(file)) {
                // invalid file data
                return new ResponseEntity<>("Please type candidate info in valid format", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("file uploaded successfully ", HttpStatus.OK);
    }


    @GetMapping("/lottery/candidate")
    ResponseEntity<LotteryWithCandidates> getAllCandidatesByLottery(@RequestParam int lotteryId) throws Exception {
        Lottery lottery = lotteryService.getLottery(lotteryId);
        if (lottery == null) {
            Exception e = new RuntimeException("Lottery does not exist");
            throw e;
        }

        LotteryWithCandidates lotteryWithCandidates = new LotteryWithCandidates();
        lotteryWithCandidates.setLottery(lottery);
        lotteryWithCandidates.setCandidates(candidateService.getAllCandidates(lottery));
        return new ResponseEntity<>(lotteryWithCandidates, HttpStatus.OK);
    }

    @GetMapping("/candidate")
    ResponseEntity<LotteryWithCandidates> getCandidatesById(@RequestParam int candidateId) throws Exception {
        Candidate candidate = candidateService.getCandidate(candidateId);
        if (candidate == null) {
            Exception e = new RuntimeException("candidate does not exist");
            throw e;
        }

        LotteryWithCandidates lotteryWithCandidates = new LotteryWithCandidates();
        lotteryWithCandidates.setLottery(candidate.getLottery());
        lotteryWithCandidates.setCandidates(Arrays.asList(candidate));
        return new ResponseEntity<>(lotteryWithCandidates, HttpStatus.OK);
    }



   
    public class LotteryWithCandidates {
        Lottery lottery;
        List<Candidate> candidates;
		public Lottery getLottery() {
			return lottery;
		}
		public void setLottery(Lottery lottery) {
			this.lottery = lottery;
		}
		public List<Candidate> getCandidates() {
			return candidates;
		}
		public void setCandidates(List<Candidate> candidates) {
			this.candidates = candidates;
		}
        
    }
}
