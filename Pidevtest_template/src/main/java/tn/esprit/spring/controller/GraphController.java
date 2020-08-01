package tn.esprit.spring.controller;



import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tn.esprit.spring.services.IAdService;


@Controller
public class GraphController {
	@Autowired
	IAdService iadService;

	
	
	@GetMapping("/displayBarGraph")
	public String barGraph(Model model) {
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		surveyMap.put("Ads_Number_Views", iadService.Countads());
		surveyMap.put("Number_Ads_Today",iadService.AdsForToday());
	
		surveyMap.put("Average_Ads_Year", (int) iadService.AVG_Ads_Year());
		
		model.addAttribute("surveyMap", surveyMap);
		return "barGraph";
	}
	
	@GetMapping("/displayPieChart")
	public String pieChart(Model model) {
		
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		
		surveyMap.put("Comments_Max_Likes",(int)iadService.countAds());
        surveyMap.put("Average_Ads_Year", (int) iadService.AVG_Ads_Year());//countComments()
        
	    model.addAttribute("surveyMap", surveyMap);
		return "pieChart";
	}

}
