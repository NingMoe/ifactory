package ifactory.mqtt.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifactory.module.call.Callrecord;
import ifactory.module.call.CallrecordMapper;

@Service
public class CallService {
	
	@Autowired
	private CallrecordMapper callMapper;
	
	public Integer insert(Callrecord record){
		return callMapper.insert(record);
	}
}
