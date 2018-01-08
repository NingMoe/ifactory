package ifactory.module.deviceManager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ifactory.module.common.BaseModel;
import ifactory.module.common.ResultCode;
import ifactory.module.deviceManager.entity.DeviceMaintainExample;
import ifactory.module.deviceManager.service.DeviceMaintainService;
import ifactory.utils.IfactoryException;

@Controller
@RequestMapping("/deviceMaintain")
public class DeviceMaintainController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DeviceMaintainService deviceMaintainService;
	
	@GetMapping(path = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody BaseModel<DeviceMaintainExample> add(@RequestBody DeviceMaintainExample deviceMaintainExample) {
		BaseModel<DeviceMaintainExample> result = new BaseModel<DeviceMaintainExample>();
		
		try{
			deviceMaintainService.add(deviceMaintainExample);
			result.setCode(ResultCode.SUCCESS);
        	result.setEntity(deviceMaintainExample);
		} catch (IfactoryException e) {
			result.set(e.getResultCode(), e.getMessage());
        } catch (Exception e) {
        	logger.error("添加个人设备维修接口异常: ", e);
            result.setError();
        }
    	
    	return result;
    } 
	
	@GetMapping(path = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody BaseModel<DeviceMaintainExample> update(@RequestBody DeviceMaintainExample deviceMaintainExample) {
    	BaseModel<DeviceMaintainExample> result = new BaseModel<DeviceMaintainExample>();
    	
    	try{
    		deviceMaintainService.update(deviceMaintainExample);
    		result.setCode(ResultCode.SUCCESS);
        	result.setEntity(deviceMaintainExample);
    	}catch (IfactoryException e) {
    		result.set(e.getResultCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("更新个人设备维修接口异常: ", e);
            result.setError();
        }
    	
    	return result;
    }
	
	@GetMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody BaseModel<DeviceMaintainExample> delete(@RequestBody DeviceMaintainExample deviceMaintainExample) {
    	BaseModel<DeviceMaintainExample> result = new BaseModel<DeviceMaintainExample>();
    	
    	try{
    		deviceMaintainService.delete(deviceMaintainExample);
    		result.setCode(ResultCode.SUCCESS);
        	result.setEntity(deviceMaintainExample);
    	}catch (IfactoryException e) {
    		result.set(e.getResultCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("删除个人设备维修接口异常: ", e);
            result.setError();
        }
    	
    	return result;
    }
	
	@GetMapping(path = "/selects", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody BaseModel<DeviceMaintainExample> select(@RequestBody DeviceMaintainExample deviceMaintainExample) {
		BaseModel<DeviceMaintainExample> result = new BaseModel<DeviceMaintainExample>();
		
		try{
			DeviceMaintainExample page = deviceMaintainService.select(deviceMaintainExample);
    		result.setCode(ResultCode.SUCCESS);
        	result.setEntity(page);
    	}catch (IfactoryException e) {
    		result.set(e.getResultCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("查询设备接维修口异常: ", e);
            result.setError();
        }
    	
    	return result;
    }

}
