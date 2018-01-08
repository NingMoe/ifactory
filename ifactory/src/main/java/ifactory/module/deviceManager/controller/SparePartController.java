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

import com.github.pagehelper.PageInfo;

import ifactory.module.common.BaseModel;
import ifactory.module.common.PageModel;
import ifactory.module.common.ResultCode;
import ifactory.module.deviceManager.entity.DeviceExample;
import ifactory.module.deviceManager.entity.SparePartExample;
import ifactory.module.deviceManager.service.SparePartService;
import ifactory.utils.IfactoryException;

@Controller
@RequestMapping("/sparePart")
public class SparePartController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SparePartService sparePartService;
	
	@GetMapping(path = "/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody BaseModel<SparePartExample> add(@RequestBody SparePartExample sparePartExample) {
		BaseModel<SparePartExample> result = new BaseModel<SparePartExample>();
		
		try{
			sparePartService.add(sparePartExample);
			result.setCode(ResultCode.SUCCESS);
        	result.setEntity(sparePartExample);
		} catch (IfactoryException e) {
			result.set(e.getResultCode(), e.getMessage());
        } catch (Exception e) {
        	logger.error("添加设备接口异常: ", e);
            result.setError();
        }
    	
    	return result;
    } 
	
	@GetMapping(path = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody BaseModel<SparePartExample> update(@RequestBody SparePartExample sparePartExample) {
    	BaseModel<SparePartExample> result = new BaseModel<SparePartExample>();
    	
    	try{
    		sparePartService.update(sparePartExample);
    		result.setCode(ResultCode.SUCCESS);
        	result.setEntity(sparePartExample);
    	}catch (IfactoryException e) {
    		result.set(e.getResultCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("更新设备接口异常: ", e);
            result.setError();
        }
    	
    	return result;
    }
	
	@GetMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody BaseModel<SparePartExample> delete(@RequestBody SparePartExample sparePartExample) {
    	BaseModel<SparePartExample> result = new BaseModel<SparePartExample>();
    	
    	try{
    		sparePartService.delete(sparePartExample);
    		result.setCode(ResultCode.SUCCESS);
        	result.setEntity(sparePartExample);
    	}catch (IfactoryException e) {
    		result.set(e.getResultCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("删除设备接口异常: ", e);
            result.setError();
        }
    	
    	return result;
    }
	
	@GetMapping(path = "/select", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody BaseModel<SparePartExample> select(@RequestBody SparePartExample sparePartExample) {
		BaseModel<SparePartExample> result = new BaseModel<SparePartExample>();
		
		try{
			SparePartExample page = sparePartService.select(sparePartExample);
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
	
	@GetMapping(path = "/selects", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody PageModel<SparePartExample> selects(@RequestBody PageModel<SparePartExample> sparePartExample) {
		PageModel<SparePartExample> result = new PageModel<SparePartExample>();
    	
        try{
        	PageInfo<SparePartExample> page = sparePartService.selects(sparePartExample);
        	result.setCode(ResultCode.SUCCESS);
        	result.setPage(page);
        }catch (Exception e) {
            logger.error("获取设备列表接口异常: ", e);;
        }
    	
    	return result;
    }

}
