package ifactory.mqtt.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifactory.module.report.Secclockrecord;
import ifactory.module.report.SecclockrecordMapper;

@Service
public class ReportService2 {

	@Autowired
	private SecclockrecordMapper secClockRecordMapper;
	
	public Integer insert(Secclockrecord record){
		return secClockRecordMapper.insert(record);
	}
}
