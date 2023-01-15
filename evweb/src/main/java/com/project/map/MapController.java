package com.project.map;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.charger.ChargerDTO;
import com.project.charger.ChargerService;
import com.project.station.StationDTO;
import com.project.station.StationService;

@Controller
public class MapController {
	MapService service;
	StationService stationService;
	ChargerService chargerService;
	MapAPIPull mapAPIPull;
	
	public MapController() {}
	@Autowired
	public MapController(MapService service, StationService stationService, ChargerService chargerService,
			MapAPIPull mapAPIPull) {
		super();
		this.service = service;
		this.stationService = stationService;
		this.chargerService = chargerService;
		this.mapAPIPull = mapAPIPull;
	}
	

	@RequestMapping("/map")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("map/main");
		List<StationDTO> stationList = stationService.stationList();
		List<String> companyList = new ArrayList<>();
		
		// 검색한 리스트에서 충전소운영기관 목록 받기
		for(StationDTO item :stationList){
	        String comName = item.getStation_company();
	        if(!companyList.contains(comName)) {
	        	companyList.add(comName);
	        }
	    }
		
		mv.addObject("stationList", stationList);
		mv.addObject("listCount", stationList.size());
		mv.addObject("companyList", companyList);
		mv.addObject("category","all");
		mv.addObject("lat",stationList.get(0).getMap_latitude());
		mv.addObject("longt",stationList.get(0).getMap_longtude());
		return mv;
	}

	// ajax로 충전소 정보 확인하기
	@RequestMapping(value = "/ajax/mapStation", produces = "application/json;charset=utf-8")
	@ResponseBody
	public StationDTO stationInfo(String stationId, Model model){
		StationDTO stationInfo = stationService.read(stationId);
		model.addAttribute("info", stationInfo);
		return stationInfo;
	}

	// 충전소 검색하기
	@RequestMapping("/map/search.do")
	public ModelAndView search(String category,String keyword, String park, String type) {
		ModelAndView mv = new ModelAndView("map/main");
		List<StationDTO> stationList = service.search(category, keyword);
		List<String> companyList = new ArrayList<String>();
		
		if (stationList.size() == 0) {
			stationList = stationService.stationList();
			mv.addObject("noResult", "0");
		} 		
		// 검색한 리스트에서 충전소운영기관 목록 받기
		for(StationDTO item :stationList){
	        String comName = item.getStation_company();
	        if(!companyList.contains(comName)) {
	        	companyList.add(comName);
	        }
	    }

		mv.addObject("stationList", stationList);
		mv.addObject("category",category);
		mv.addObject("companyList", companyList);
		mv.addObject("keyword",keyword);
		mv.addObject("lat",stationList.get(0).getMap_latitude());
		mv.addObject("longt",stationList.get(0).getMap_longtude());
		return mv;
	}
	

	// ajax로 충전소 정보 검색하기(체크박스)
	@RequestMapping(value = "/ajax/map/search.do", produces = "application/json;charset=utf-8")
	@ResponseBody
	public List<StationDTO> chckList(String category,String keyword, String park, String quick) {
		List<StationDTO> stationList;
		List<ChargerDTO> chargerList;
		if (category == null || keyword == null) {
			stationList = stationService.stationList();
		}
		else {
			stationList = service.search(category, keyword);
		}
		
		List<StationDTO> resultListParkY = new ArrayList<>();// 주차여부 검색
		List<StationDTO> resultTypeQuick = new ArrayList<>();// 급속여부
		
		// 충전타입(01:DC차데모,	02: AC완속,	03: DC차데모+AC3상,04: DC콤보,05: DC차데모+DC콤보,06: DC차데모+AC3상+DC콤보,	07: AC3상)
		// 01, 04 : 급속
		// 02, 07 : 완속
		// 03, 05, 06 : 둘 다 포함
		chargerList = chargerService.chargerList();
		
		System.out.println("검색 :"+category+"/"+keyword);
		System.out.println(park+","+quick);
		
		for(StationDTO item :stationList) {
			String parkType = item.getParking_free();
			if (parkType.equals("Y")) {
				resultListParkY.add(item);
			} 
		}
		// 주차체크
		if (park.equals("Y")) {
			System.out.println(resultListParkY.size()); 
			return resultListParkY;
		}
		else{
			System.out.println(stationList.size()); 
			return stationList;
		}
		
		
		
	}
}
