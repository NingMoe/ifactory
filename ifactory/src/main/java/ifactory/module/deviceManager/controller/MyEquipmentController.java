package ifactory.module.deviceManager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import ifactory.module.common.BaseModel;
import ifactory.module.common.PageModel;
import ifactory.module.common.ResultCode;
import ifactory.module.deviceManager.entity.MyEquipmentExample;
import ifactory.module.deviceManager.entity.StateStatisticsExample;
import ifactory.module.deviceManager.service.MyEquipmentService;
import ifactory.utils.IfactoryException;

@Controller
@RequestMapping("/myEquipment")
public class MyEquipmentController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MyEquipmentService myEquipmentService;
	
	@GetMapping(path = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody BaseModel<MyEquipmentExample> add(@RequestBody MyEquipmentExample myEquipmentExample) {
		BaseModel<MyEquipmentExample> result = new BaseModel<MyEquipmentExample>();
		
		try{
			myEquipmentService.add(myEquipmentExample);
			result.setCode(ResultCode.SUCCESS);
        	result.setEntity(myEquipmentExample);
		} catch (IfactoryException e) {
			result.set(e.getResultCode(), e.getMessage());
        } catch (Exception e) {
        	logger.error("添加个人设备接口异常: ", e);
            result.setError();
        }
    	
    	return result;
    } 
	
	@GetMapping(path = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody BaseModel<MyEquipmentExample> update(@RequestBody MyEquipmentExample myEquipmentExample) {
    	BaseModel<MyEquipmentExample> result = new BaseModel<MyEquipmentExample>();
    	
    	try{
    		myEquipmentService.update(myEquipmentExample);
    		result.setCode(ResultCode.SUCCESS);
        	result.setEntity(myEquipmentExample);
    	}catch (IfactoryException e) {
    		result.set(e.getResultCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("更新个人设备接口异常: ", e);
            result.setError();
        }
    	
    	return result;
    }
	
	@GetMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody BaseModel<MyEquipmentExample> delete(@PathVariable(name = "id")Integer id) {
    	BaseModel<MyEquipmentExample> result = new BaseModel<MyEquipmentExample>();
    	
    	try{
    		MyEquipmentExample me = new MyEquipmentExample();
    		me.setId(id);
    		myEquipmentService.delete(me);
    		result.setCode(ResultCode.SUCCESS);
        	result.setEntity(me);
    	}catch (IfactoryException e) {
    		result.set(e.getResultCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("删除个人设备接口异常: ", e);
            result.setError();
        }
    	
    	return result;
    }
	
	@GetMapping(path = "/select", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody BaseModel<MyEquipmentExample> select(@RequestBody MyEquipmentExample myEquipmentExample) {
		BaseModel<MyEquipmentExample> result = new BaseModel<MyEquipmentExample>();
		
		try{
			MyEquipmentExample page = myEquipmentService.select(myEquipmentExample);
    		result.setCode(ResultCode.SUCCESS);
        	result.setEntity(page);
    	}catch (IfactoryException e) {
    		result.set(e.getResultCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("查询设备接口异常: ", e);
            result.setError();
        }
    	
    	return result;
    }
	
	//获取设备清单: 统计要显示的列表中设备的总条数，工作中的设备，故障中的设备
	@GetMapping(path = "/statisticalStatus/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody BaseModel<StateStatisticsExample> statisticalStatus(@PathVariable(name = "userId")String userId) {
    	BaseModel<StateStatisticsExample> result = new BaseModel<StateStatisticsExample>();
    	
    	try{
    		StateStatisticsExample page = myEquipmentService.statusDisplay(userId);
    		result.setCode(ResultCode.SUCCESS);
    		result.setEntity(page);
    	}catch (IfactoryException e) {
    		result.set(e.getResultCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("获取设备清单统计接口异常: ", e);
            result.setError();
        }
    	return result;
    }
	
	
	@GetMapping(path = "/selects", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody PageModel<MyEquipmentExample> selects(@RequestBody PageModel<MyEquipmentExample> myEquipmentExample) {
		PageModel<MyEquipmentExample> result = new PageModel<MyEquipmentExample>();
    	
        try{
        	PageInfo<MyEquipmentExample> page = myEquipmentService.selects(myEquipmentExample);
        	result.setCode(ResultCode.SUCCESS);
        	result.setPage(page);
        }catch (Exception e) {
            logger.error("获取个人设备列表接口异常: ", e);;
        }
    	
    	return result;
    }
	

}
