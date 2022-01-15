package com.example.lotteryofpilgrims.service;

import com.example.lotteryofpilgrims.entity.Candidate;
import com.example.lotteryofpilgrims.entity.Lottery;
import com.example.lotteryofpilgrims.repo.LotteryRepo;
import com.example.lotteryofpilgrims.util.FilesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class LotteryServiceImpl implements LotteryService {

    @Autowired
    LotteryRepo lotteryRepo;
    @Autowired
    CandidateService candidateService;


    @Override
    public void saveLottery(Lottery lottery) {
        log.info("Saving new candidate to database {}", lottery);
        lotteryRepo.save(lottery);
    }

    @Override
    public Lottery getLottery(int id) {
        log.info("Fetching lottery {}", id);
        Optional<Lottery> lottery = lotteryRepo.findById(id);
        if (lottery.isPresent()) {
            return lottery.get();
        }
        return null;
    }

    @Override
    public boolean uploadFile(MultipartFile multipartFile) throws Exception {
        log.info("start uploading file '{}", multipartFile.getOriginalFilename(), "'");

        // read file
        LineIterator it = FileUtils.lineIterator(FilesUtil.convertMultipartFileToFile(multipartFile), "UTF-8");
        List<Candidate> candidates = new ArrayList<>();
        Lottery lottery = new Lottery();
//        try {
        while (it.hasNext()) {
            String line = it.nextLine();
            if (!candidateService.validateCandidateDate(line)) {
                return false;
            } else {
                Candidate currentCandidate = candidateService.formatCandidateData(line);
                currentCandidate.setLottery(lottery);
                candidates.add(currentCandidate);
            }
        }
        // save lottery with candidates
        lotteryRepo.save(lottery);
        candidateService.saveAllCandidate(candidates);

//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return true;
    }


}
