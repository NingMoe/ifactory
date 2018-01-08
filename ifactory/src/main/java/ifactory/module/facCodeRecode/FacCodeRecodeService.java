package ifactory.module.facCodeRecode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("FacCodeRecodeService")
public class FacCodeRecodeService {
	
	@Autowired
	private FacCodeRecodeMapper facCodeRecodeMapper;
	
	public void add(FacCodeRecodeExample fac){
		
		facCodeRecodeMapper.insert(fac);
		
	}
	
}
