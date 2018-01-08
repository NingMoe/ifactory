package ifactory.mqtt.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifactory.module.statistics.Sectimesum;
import ifactory.module.statistics.SectimesumMapper;

@Service
public class StatisticsService2 {
	
	@Autowired
	private SectimesumMapper secTimeSumMapper;
	
	public void insert(Sectimesum sum){
		secTimeSumMapper.insert(sum);
	}

}
