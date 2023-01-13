package com.project.map;

import java.util.List;

import com.project.station.StationDTO;

public interface MapDAO {
	int insert(MapStationDTO station);
	List<MapStationDTO> stationList();
	List<StationDTO> companyList();
	List<StationDTO> search(String category, String keyword);
	List<StationDTO> search(MapStationDTO mDto);
	List<MapStationDTO> findbynameAll(String stationName); 
	List<MapStationDTO> findByName(String category,String stationName); 
	StationDTO read(String stationId);
}
