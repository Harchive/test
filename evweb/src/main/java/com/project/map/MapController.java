package com.project.map;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
		List<StationDTO> companyList = stationService.companyList();
		mv.addObject("stationList", stationList);
		mv.addObject("listCount", stationList.size());
		mv.addObject("companyList", companyList);
		return mv;
	}

	// ajax로 충전기정보 업데이트하기
	@RequestMapping(value = "/ajax/mapStation", produces = "application/json;charset=utf-8")
	@ResponseBody
	public StationDTO stationInfo(String stationId, Model model){
		StationDTO stationInfo = stationService.read(stationId);
		model.addAttribute("info", stationInfo);
		return stationInfo;
	}
}
