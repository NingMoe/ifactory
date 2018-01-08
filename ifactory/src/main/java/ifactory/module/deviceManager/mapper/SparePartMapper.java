package ifactory.module.deviceManager.mapper;

import java.util.List;

import com.github.pagehelper.PageInfo;

import ifactory.module.common.PageModel;
import ifactory.module.deviceManager.entity.SparePartExample;

public interface SparePartMapper {
	
	public void insert(SparePartExample sparePartExample);
	
	public void delete(SparePartExample sparePartExample);
	
	public void update(SparePartExample sparePartExample);
	
	public SparePartExample select(SparePartExample sparePartExample);
	
	public PageInfo<SparePartExample> selects(PageModel<SparePartExample> sparePartExample);

}
