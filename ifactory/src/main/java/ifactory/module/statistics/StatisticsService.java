package ifactory.module.statistics;

import org.springframework.beans.factory.annotation.Autowired;

public class StatisticsService {
	
	@Autowired
	private SectimesumMapper secTimeSumMapper;
	
	public void insert(Sectimesum sum){
		secTimeSumMapper.insert(sum);
	}

}
