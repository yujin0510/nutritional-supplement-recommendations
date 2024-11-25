package com.test.nutri.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.nutri.entity.News;
import com.test.nutri.entity.vwDailyRecommend;
import com.test.nutri.entity.vwGenderAgeRecommend;
import com.test.nutri.entity.vwHealthRecommend;
import com.test.nutri.entity.vwOrganRecommend;
import com.test.nutri.repository.DailyRecommendRepository;
import com.test.nutri.repository.GenderAgeRecommendRepository;
import com.test.nutri.repository.HealthRecommendRepository;
import com.test.nutri.repository.OrganRecommendRepository;
import com.test.nutri.service.NewsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecommendController {
	
	private final GenderAgeRecommendRepository genderAgeRecommendRepository;
	private final HealthRecommendRepository healthRecommendRepository;
	private final OrganRecommendRepository organRecommendRepository;
	private final DailyRecommendRepository dailyRecommendRepository;
	
	@GetMapping("/survey")
	public String survey(Model model) {
		
		return "page/survey";
	}
	
	
	@GetMapping("/recommend")
	public String recommend(Model model) {
		
		List<vwGenderAgeRecommend> galist = genderAgeRecommendRepository.listAll(5);
		List<vwHealthRecommend> hlist = healthRecommendRepository.listAll(1);
		List<vwOrganRecommend> olist = organRecommendRepository.listAll(2);
		List<vwDailyRecommend> dlist = dailyRecommendRepository.listAll(1);
		
		
		model.addAttribute("galist", galist);
		model.addAttribute("hlist", hlist);
		model.addAttribute("olist", olist);
		model.addAttribute("dlist", dlist);
		
		return "page/recommend";
	}
	
}
