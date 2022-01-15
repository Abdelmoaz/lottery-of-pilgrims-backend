package com.example.lotteryofpilgrims.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.example.lotteryofpilgrims.entity.Candidate;

public class PerformLottry {
	
	private int total_number_of_pilgrims;
	private int discrimination_percentage;
	private int discrimination_age;
	List<Candidate> candidates;
	private List<Candidate> acceptedCandidateList = new ArrayList<>();
	private List<Candidate> candidateHaveAgeLessThanDiscriminationAge= new ArrayList<>();
	private List<Candidate> candidateHaveAgeMoreThanDiscriminationAge= new ArrayList<>();
	private int moreListSize;
	private int lessListSize;
	
	public PerformLottry(int total_number_of_pilgrims, int discrimination_percentage, int discrimination_age,List<Candidate> candidates) {
		super();
		this.total_number_of_pilgrims = total_number_of_pilgrims;
		this.discrimination_percentage = discrimination_percentage;
		this.discrimination_age = discrimination_age;
		this.candidates = candidates;
	}
	
	
	public List<Candidate> getAcceptedList() {
		if (candidates.size() <= total_number_of_pilgrims) {
			addListToAcceptedList(candidates);
		}else {
			fillAcceptedListRandomly();
		}
		
		return acceptedCandidateList;
	}
	
	private void fillMoreAndLessList(List<Candidate> candidates) {
		for(Candidate candite : candidates){
    		if(candite.getAge() >= discrimination_age) {
    			candidateHaveAgeMoreThanDiscriminationAge.add(candite);
    		}else {
    			candidateHaveAgeLessThanDiscriminationAge.add(candite);
    		}
    	}
		moreListSize = candidateHaveAgeMoreThanDiscriminationAge.size();
		lessListSize = candidateHaveAgeLessThanDiscriminationAge.size();
	}
	
	private int convertPrecentageToNumber(int numberToConvert,int totalOfCandients) {
    	return numberToConvert*100/totalOfCandients;
    }
    
    private Set<Integer> getRandomeIndex(int listSize, int numbersNeeded) {
    	Random randomValue = new Random();
   
    	Set<Integer> generated = new LinkedHashSet<>();
    	while (generated.size() < numbersNeeded)
    	{
    	    Integer next = randomValue.nextInt(listSize) ;
    	    generated.add(next);
    	}
    	return generated;
    }
    private void addListToAcceptedList(List<Candidate> list) {
    	acceptedCandidateList.addAll(list);
    }
    
    private void addCandidateToAcceptedList(Candidate candidate) {
    	acceptedCandidateList.add(candidate);
    }
    
    private void fillAcceptedListRandomly() {
		int convertedNumber = convertPrecentageToNumber(total_number_of_pilgrims,discrimination_percentage);
		fillMoreAndLessList(candidates);
		if(moreListSize <= convertedNumber) {
			int restCandidates = total_number_of_pilgrims - moreListSize;
			Set<Integer> setOfRandomIndexForLess = getRandomeIndex(lessListSize, restCandidates);
			fillListFromRandomList(setOfRandomIndexForLess, candidateHaveAgeLessThanDiscriminationAge);
			addListToAcceptedList(candidateHaveAgeMoreThanDiscriminationAge);
			
		}else {
		   Set<Integer> setOfRandomIndexForMore = getRandomeIndex(moreListSize, convertedNumber);
		   int restCandidates = total_number_of_pilgrims - convertedNumber;
		   Set<Integer> setOfRandomIndexForLess = getRandomeIndex(lessListSize, restCandidates);
		   fillListFromRandomList(setOfRandomIndexForMore, candidateHaveAgeMoreThanDiscriminationAge);
		   fillListFromRandomList(setOfRandomIndexForLess, candidateHaveAgeLessThanDiscriminationAge);
		}
    }
    
    private void fillListFromRandomList(Set<Integer> randomList, List<Candidate> candidateList) {
    	for(Integer index : randomList) {
			   Candidate candidate= candidateList.get(index);
			   addCandidateToAcceptedList(candidate);
		   }
    }
}

