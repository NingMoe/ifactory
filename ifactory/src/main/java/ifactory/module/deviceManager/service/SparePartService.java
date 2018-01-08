package ifactory.module.deviceManager.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import ifactory.module.common.PageModel;
import ifactory.module.deviceManager.entity.SparePartExample;
import ifactory.module.deviceManager.mapper.SparePartMapper;

@Service
public class SparePartService {
	
	@Autowired
	private SparePartMapper sparePartMapper;
	
	public void add(SparePartExample sparePartExample){
		sparePartMapper.insert(sparePartExample);
	}
	
	public void update(SparePartExample sparePartExample){
		sparePartMapper.update(sparePartExample);
	}
	
	public void delete(SparePartExample sparePartExample){
		sparePartMapper.delete(sparePartExample);
	}
	
	public SparePartExample select(SparePartExample sparePartExample){
		return sparePartMapper.select(sparePartExample);
	}
	
	public PageInfo<SparePartExample> selects(PageModel<SparePartExample> con){
		
		if (StringUtils.isEmpty(con.toString()))
            throw new IllegalArgumentException();
		
		PageInfo<SparePartExample> pageInfo = sparePartMapper.selects(con);
		
		return pageInfo;
	}

}
