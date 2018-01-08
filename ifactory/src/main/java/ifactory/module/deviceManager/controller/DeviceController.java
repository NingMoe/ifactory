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
import ifactory.module.deviceManager.entity.DeviceExample;
import ifactory.module.deviceManager.entity.StateStatisticsExample;
import ifactory.module.deviceManager.service.DeviceService;
import ifactory.utils.IfactoryException;

@Controller
@RequestMapping("/device")
public class DeviceController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DeviceService deviceService;
	
	@GetMapping(path = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody BaseModel<DeviceExample> add(@RequestBody DeviceExample deviceExample) {
		BaseModel<DeviceExample> result = new BaseModel<DeviceExample>();
		
		try{
			deviceService.add(deviceExample);
			result.setCode(ResultCode.SUCCESS);
        	result.setEntity(deviceExample);
		} catch (IfactoryException e) {
			result.set(e.getResultCode(), e.getMessage());
        } catch (Exception e) {
        	logger.error("添加设备接口异常: ", e);
            result.setError();
        }
    	
    	return result;
    } 
	
	@GetMapping(path = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody BaseModel<DeviceExample> update(@RequestBody DeviceExample deviceExample) {
    	BaseModel<DeviceExample> result = new BaseModel<DeviceExample>();
    	
    	try{
    		deviceService.update(deviceExample);
    		result.setCode(ResultCode.SUCCESS);
        	result.setEntity(deviceExample);
    	}catch (IfactoryException e) {
    		result.set(e.getResultCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("更新设备接口异常: ", e);
            result.setError();
        }
    	
    	return result;
    }
	
	@GetMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody BaseModel<DeviceExample> delete(@RequestBody DeviceExample deviceExample) {
    	BaseModel<DeviceExample> result = new BaseModel<DeviceExample>();
    	
    	try{
    		deviceService.delete(deviceExample);
    		result.setCode(ResultCode.SUCCESS);
        	result.setEntity(deviceExample);
    	}catch (IfactoryException e) {
    		result.set(e.getResultCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("删除设备接口异常: ", e);
            result.setError();
        }
    	
    	return result;
    }
	
	@GetMapping(path = "/select", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody BaseModel<DeviceExample> select(@RequestBody DeviceExample deviceExample) {
		BaseModel<DeviceExample> result = new BaseModel<DeviceExample>();
		
		try{
    		DeviceExample page = deviceService.select(deviceExample);
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
	@GetMapping(path = "/statisticalStatus", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody BaseModel<StateStatisticsExample> statisticalStatus() {
    	BaseModel<StateStatisticsExample> result = new BaseModel<StateStatisticsExample>();
    	
    	try{
    		StateStatisticsExample de = deviceService.statusDisplay();
    		result.setCode(ResultCode.SUCCESS);
    		result.setEntity(de);
    	}catch (IfactoryException e) {
    		result.set(e.getResultCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("获取设备清单统计接口异常: ", e);
            result.setError();
        }
    	return result;
    }
	
	
	@GetMapping(path = "/selects", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody PageModel<DeviceExample> selects(@RequestBody PageModel<DeviceExample> deviceExampleModel) {
		PageModel<DeviceExample> result = new PageModel<DeviceExample>();
    	
        try{
        	PageInfo<DeviceExample> page = deviceService.selects(deviceExampleModel);
        	result.setCode(ResultCode.SUCCESS);
        	result.setPage(page);
        }catch (Exception e) {
            logger.error("获取设备列表接口异常: ", e);;
        }
    	
    	return result;
    }
}
