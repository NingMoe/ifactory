package ifactory.module.push;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecsubscriptionService {
	
	@Autowired
	private SecsubscriptionMapper secMapper;
	
	
	public Integer insert(Secsubscription record){
		return secMapper.insert(record);
	}

	public List<Secsubscription> query(SecsubscriptionExample condition){
		return secMapper.selectByExample(condition);
	}
	
	public Integer update(Secsubscription record){
		return secMapper.updateByPrimaryKey(record);
	}
	
	public Integer delete(SecsubscriptionExample condition){
		return secMapper.deleteByExample(condition);
	}
}
