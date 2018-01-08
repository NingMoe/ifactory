package ifactory.module.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

	@Autowired
	private SecclockrecordMapper secClockRecordMapper;
	
	public Integer insert(Secclockrecord record){
		return secClockRecordMapper.insert(record);
	}
}
