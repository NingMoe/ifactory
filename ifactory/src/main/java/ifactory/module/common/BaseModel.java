package ifactory.module.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseModel<T> implements Serializable {

    private int code;
    private String comment;
    private T entity; 

    public BaseModel() {
        this.code = ResultCode.SUCCESS;
        this.comment = "请求完成";
    }

    BaseModel(int code) {
        this.code = code;
    }

    public BaseModel(String comment) {
        this(ResultCode.SUCCESS);
        this.comment = comment;
    }

    public BaseModel(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }
   

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public void set(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    public void setError() {
        this.code = ResultCode.FAILED;
        this.comment = "系统异常";
    }
}
