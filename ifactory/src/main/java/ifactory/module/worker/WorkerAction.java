package ifactory.module.worker;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ifactory.module.secretary.Secretary;
import ifactory.module.secretary.SecretaryExample;
import ifactory.module.secretary.SecretaryService;

@Controller
@RequestMapping("/worker")
public class WorkerAction {
	
	@Autowired
	private SecretaryService secretaryService;
	
	@RequestMapping(value = "/signIn", method = {RequestMethod.POST,RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String signIn(@RequestBody Worker worker) {
		String secretKey = "";
		SecretaryExample condition = new SecretaryExample();
		condition.or().andDeviceIdEqualTo(worker.getDeviceId());
		List<Secretary> list = secretaryService.query(condition);
		if(list!=null&&list.size()>0){
			return "该设备已注册";
		}else{
			//secretaryService.insert(worker);
			//阿里云设备注册
		}
		return secretKey;
	}

}
