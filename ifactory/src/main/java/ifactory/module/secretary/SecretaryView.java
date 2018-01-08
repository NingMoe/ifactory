package ifactory.module.secretary;

public class SecretaryView extends Secretary{
	
	private String type;
	
	public SecretaryView(Secretary sec,String type){
		this.setId(sec.getId());
		this.setDeviceId(sec.getDeviceId());
		this.setSecretKey(sec.getSecretKey());
		this.setCompanyCode(sec.getCompanyCode());
		this.setFactory(sec.getFactory());
		this.setDepartment(sec.getDepartment());
		this.setAlias(sec.getAlias());
		this.setState(sec.getState());
		this.setStation(sec.getStation());
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
