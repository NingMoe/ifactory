package ifactory.module.secretary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;

import ifactory.module.common.ResultCode;
import ifactory.module.push.Secsubscription;
import ifactory.module.push.SecsubscriptionExample;
import ifactory.module.push.SecsubscriptionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/secretary")
public class SecretaryController {

	@Autowired
	private SecretaryService secretaryService;
	
	@Autowired
	private SecsubscriptionService secsubscriptionService;

	// 手机端注册
	@ApiOperation(value = "注册", notes = "前台封装成用户对象")
	@ApiImplicitParam(name = "userView", value = "用户详细实体user", required = true, dataType = "Secretary")
	@RequestMapping(value = "/signIn", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Integer signIn(@RequestBody Secretary secretary) {
		SecretaryExample condition = new SecretaryExample();
		condition.or().andSecretKeyEqualTo(secretary.getSecretKey());
		List<Secretary> list = secretaryService.query(condition);
		if (list != null && list.size() > 0) {
			return ResultCode.EQUIP_EXIST;
		} else {
			secretaryService.insert(secretary);
			return ResultCode.SUCCESS;
		}
	}

	// 手机端注销
	@RequestMapping(value = "/cancel", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Integer cancel(@RequestBody Secretary secretary) {
		SecretaryExample condition = new SecretaryExample();
		condition.or().andDeviceIdEqualTo(secretary.getDeviceId());
		List<Secretary> list = secretaryService.query(condition);
		if (list != null && list.size() > 0) {
			Secretary mobile = list.get(0);
			mobile.setState("-1");
			secretaryService.equidUpdate(secretary);
			return ResultCode.SUCCESS;
		} else {
			return ResultCode.FAILED;
		}
	}

	// 手机端信息修改
	@RequestMapping(value = "/update", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Integer update(@RequestBody Secretary secretary) {
		SecretaryExample condition = new SecretaryExample();
		condition.or().andDeviceIdEqualTo(secretary.getDeviceId());
		List<Secretary> list = secretaryService.query(condition);
		if (list != null && list.size() > 0) {
			secretaryService.equidUpdate(secretary);
			return ResultCode.SUCCESS;
		} else {
			return ResultCode.FAILED;
		}
	}

	// 手机端添加订阅
	@RequestMapping(value = "/sub", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Integer sub(@RequestBody Secsubscription sec) {
		try {
			SecsubscriptionExample example = new SecsubscriptionExample();
			example.or().andDeviceIdEqualTo(sec.getDeviceId()).andSecretKeyEqualTo(sec.getSecretKey());
			List<Secsubscription> list = secsubscriptionService.query(example);
			if(list!=null&&list.size()>0){
				return ResultCode.SUB_EXIST;
			}else{
				if(sec.getDepartment()==null){
					sec.setDepartment("");
				}
				if(sec.getCompanyCode()==null){
					sec.setCompanyCode("");
				}
				if(sec.getFactory()==null){
					sec.setFactory("");
				}
				if(sec.getStation()==null){
					sec.setStation("");
				}
				secsubscriptionService.insert(sec);
			}
			return ResultCode.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.FAILED;
		}
	}
	
	// 获得手机端订阅列表
	@RequestMapping(value = "/getSubList", method = {RequestMethod.POST,RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public SecretaryResultModel getSubList(@RequestBody Secretary secretary,String forExample,Integer pageIndex,Integer num) {
		SecretaryResultModel model = new SecretaryResultModel();
		try {
			SecsubscriptionExample condition = new SecsubscriptionExample();
			condition.or().andDeviceIdEqualTo(secretary.getDeviceId());
			//根据factory字段进行多字段模糊查询
			List<Secsubscription> list = secsubscriptionService.query(condition);
			Integer startIndex = (pageIndex-1)*num;
			Integer endIndex = pageIndex*num-1;
			List<SecretaryView> list2 = new ArrayList<>();
			for(Secsubscription item:list){
				SecretaryExample example = new SecretaryExample();
				example.or().andSecretKeyEqualTo(item.getSecretKey());
				List<Secretary> subList =  secretaryService.query(example);
				for(Secretary sec :subList){
					SecretaryView view = new SecretaryView(sec,item.getType());
					if(!StringUtils.isEmpty(forExample)){
						if(sec.getDepartment().contains(forExample)||sec.getFactory().contains(forExample)||sec.getStation().contains(forExample)||sec.getCompanyCode().contains(forExample)){
							list2.add(view);
						}
					}else{
						list2.add(view);
					}
				} 
			}
			if(startIndex>list.size()||startIndex<0){
				model.setViewList(null);
				model.setResultCode(ResultCode.OUT_OF_INDEX);
				return model;
			}else if(endIndex>list.size()){
				model.setViewList(list2.subList(startIndex, list2.size()));
				model.setResultCode(ResultCode.SUCCESS);
			}else{
				model.setViewList(list2.subList(startIndex, endIndex));
				model.setResultCode(ResultCode.SUCCESS);
			}
			
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			model.setResultCode(ResultCode.FAILED);
			return model;
		}
	}
	
	@RequestMapping(value = "/updateSub", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Integer updateSub(@RequestBody Secsubscription sec) {
		try {
			SecsubscriptionExample example = new SecsubscriptionExample();
			example.or().andDeviceIdEqualTo(sec.getDeviceId()).andSecretKeyEqualTo(sec.getSecretKey());
			List<Secsubscription> list = secsubscriptionService.query(example);
			if(list!=null&&list.size()>0){
				list.get(0).setType(sec.getType());
				secsubscriptionService.update(list.get(0));
			}else{
				return ResultCode.SUB_NOT_EXIST;
			}
			return ResultCode.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.FAILED;
		}
	}
	
	@RequestMapping(value = "/deleteSub", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Integer deleteSub(@RequestBody Secsubscription sec) {
		try {
			SecsubscriptionExample example = new SecsubscriptionExample();
			example.or().andDeviceIdEqualTo(sec.getDeviceId()).andSecretKeyEqualTo(sec.getSecretKey());
			List<Secsubscription> list = secsubscriptionService.query(example);
			if(list!=null&&list.size()>0){
				secsubscriptionService.delete(example);
			}else{
				return ResultCode.SUB_NOT_EXIST;
			}
			return ResultCode.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.FAILED;
		}
	}
	
	@RequestMapping(value = "/query", method = { RequestMethod.POST,
			RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public SecretaryResultModel query(@RequestBody Secsubscription sec) {
		SecretaryResultModel model = new SecretaryResultModel();
		try {
			SecretaryExample condition = new SecretaryExample();
			condition.or().andSecretKeyEqualTo(sec.getSecretKey());
			List<Secretary> list = secretaryService.query(condition);
			List<SecretaryView> viewList = new ArrayList<>();
			if(list!=null&&list.size()>0){
				for(Secretary item :list){
					SecretaryView view = new SecretaryView(item,"");
					viewList.add(view);
				} 
				model.setViewList(viewList);
				model.setResultCode(ResultCode.SUCCESS);
				return model;
			}else{
				model.setResultCode(ResultCode.NOT_EXIST);
				return model;
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.setResultCode(ResultCode.FAILED);
			return model;
		}
	}
	
//	@RequestMapping(value = "/getEquipList", method = { RequestMethod.POST,
//			RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public SecretaryResultModel getEquipList(@RequestBody Secretary secretary,Integer pageIndex,Integer num) {
//		SecretaryResultModel model = new SecretaryResultModel();
//		String wrongStates = "09,10,12,13";
//	    String okStates = "02,03,05,06";
//		try {
//			SecretaryExample condition = new SecretaryExample();
//			condition.or().andSecretKeyNotEqualTo("-1");
//			if(secretary.getDepartment()!=null){
//				condition.or().andDepartmentLike(secretary.getDepartment());
//			}
//			if(secretary.getCompanyCode()==null){
//				condition.or().andCompanyCodeLike(secretary.getCompanyCode());
//			}
//			if(secretary.getFactory()==null){
//				condition.or().andFactoryLike(secretary.getFactory());
//			}
//			if(secretary.getStation()==null){
//				condition.or().andStationLike(secretary.getStation());
//			}
//			Integer startIndex = (pageIndex-1)*num;
//			Integer endIndex = pageIndex*num-1;
//			List<Secretary> list = secretaryService.query(condition);
//			List<SecretaryView> viewList = new ArrayList<>();
//			if(list!=null&&list.size()>0){
//				for(Secretary item :list){
//					SecretaryView view = new SecretaryView(item,"");
//					if(wrongStates.indexOf(view.getState())>-1){
//						view.setState("2");
//					}else if(okStates.indexOf(view.getState())>-1){
//						view.setState("1");
//					}
//					viewList.add(view);
//				} 
//				if(startIndex>list.size()||startIndex<0){
//					model.setViewList(null);
//					model.setResultCode(ResultCode.OUT_OF_INDEX);
//				}else if(endIndex>list.size()){
//					model.setViewList(viewList.subList(startIndex, viewList.size()));
//					model.setResultCode(ResultCode.SUCCESS);
//				}else{
//					model.setViewList(viewList.subList(startIndex, endIndex));
//					model.setResultCode(ResultCode.SUCCESS);
//				}
//			}else{
//				model.setResultCode(ResultCode.NOT_EXIST);
//			}
//			return model;
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.setResultCode(ResultCode.FAILED);
//			return model;
//		}
//	}
	
	
}
