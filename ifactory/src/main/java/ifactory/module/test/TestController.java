package ifactory.module.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import ifactory.module.common.BaseModel;
import ifactory.module.common.PageModel;
import ifactory.module.common.ResultCode;
import ifactory.module.push.PushService;
import ifactory.module.push.Secsubscription;
import ifactory.module.push.SecsubscriptionExample;
import ifactory.module.push.SecsubscriptionService;
import ifactory.mqtt.handlers.CallService;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/Test")
public class TestController {

    
    @Autowired
    private TestService testService;
    
    @Autowired
	private SecsubscriptionService secsubscriptionService;
    
    @Autowired
	private PushService pushService;
    
    @Autowired
    private CallService callService;
    
    @GetMapping(path = "/add/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody BaseModel<TestModel> add(@PathVariable(name = "name")String name) {
    	BaseModel<TestModel> result = new BaseModel<TestModel>();
    	
    	TestModel a = new TestModel();
    	a.setName(name);
    	testService.add(a);
    	
    	result.setCode(ResultCode.SUCCESS);
    	result.setEntity(a);
    	
    	return result;
    }
	
    @GetMapping(path = "/update/{id}/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody BaseModel<TestModel> update(@PathVariable(name = "id")Integer id,
    		@PathVariable(name = "name")String name) {
        
    	BaseModel<TestModel> result = new BaseModel<TestModel>();
    	
    	TestModel a = new TestModel();
    	a.setId(id);
    	a.setName(name);
    	testService.update(a);


    	result.setCode(ResultCode.SUCCESS);
    	result.setEntity(a);
    	
    	return result;
    }
	
    @GetMapping(path = "/select", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody PageModel<TestModel> selects() {
    	//查询条件的设置应该是由客户端传过来
    	PageModel<TestModel> con = new PageModel<TestModel>();
    	con.setPageNum(1);
        con.setPageSize(10);
    	
    	PageInfo<TestModel> page = testService.selects(con);
        
    	PageModel<TestModel> result = new PageModel<TestModel>();
    	
    	result.setCode(ResultCode.SUCCESS);
    	result.setPage(page);
    	
    	return result;
    }
	
    @GetMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody BaseModel<TestModel> delete(@PathVariable(name = "id")Integer id) {
    	BaseModel<TestModel> result = new BaseModel<TestModel>();
    	
    	testService.delete(id);
    	
    	result.setCode(ResultCode.SUCCESS);
    	
    	return result;
    }
    
    @GetMapping(path = "/push", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody BaseModel<TestModel> push(String id) {
    	SecsubscriptionExample example = new SecsubscriptionExample();
    	example.or().andDeviceIdEqualTo("1a1018970aa3dd31726");
    	List<Secsubscription> subList = secsubscriptionService.query(example);
    	for(Secsubscription item : subList){
    		pushService.push("1a1018970aa3dd31726", JSONObject.fromObject(item).toString());
    	}
//    	Callrecord callCmd =  new Callrecord();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//		callCmd.setRfid("gg");
//		callCmd.setCallTime(new Date());
//		callCmd.setCallType("02");
//		callCmd.setSecretKey("de5c");
//		callService.insert(callCmd);
    	return null;
    }
    
    
}
