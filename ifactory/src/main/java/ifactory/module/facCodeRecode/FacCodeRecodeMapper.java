package ifactory.module.facCodeRecode;

import java.util.List;

public interface FacCodeRecodeMapper {
	
	public void insert(FacCodeRecodeExample facCodeRecodeExample);
	
	public void delete(FacCodeRecodeExample facCodeRecodeExample);
	
	public void update(FacCodeRecodeExample facCodeRecodeExample);
	
	public FacCodeRecodeExample select(FacCodeRecodeExample facCodeRecodeExample);
	
	public List<FacCodeRecodeExample> selects(FacCodeRecodeExample facCodeRecodeExample);

}
