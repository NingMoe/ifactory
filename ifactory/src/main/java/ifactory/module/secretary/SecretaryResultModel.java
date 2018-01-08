package ifactory.module.secretary;

import java.util.List;

public class SecretaryResultModel {
	
	private Integer resultCode;
	
	private List<SecretaryView> viewList;

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public List<SecretaryView> getViewList() {
		return viewList;
	}

	public void setViewList(List<SecretaryView> viewList) {
		this.viewList = viewList;
	}

}
