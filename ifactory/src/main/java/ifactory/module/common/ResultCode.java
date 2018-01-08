package ifactory.module.common;

public class ResultCode {
    public static final int SUCCESS = 1000;
    public static final int FAILED = 1001;
    
    public final static int EQUIP_EXIST = 1003;// 设备已存在
    
    public final static int SUB_EXIST = 1004;//已订阅该设备，请勿重复绑定
    
    public final static int SUB_NOT_EXIST = 1005;//
    
    public final static int NOT_EXIST = 2001;//未注册
    
    public final static int OUT_OF_INDEX = 2002;//页码超出范围
    
    public final static int LOGIN_NOACOUNT = 2009;// 登录名不存在
	public final static int LOGIN_WRONGPASSWORD = 2010;// 登录名密码错误
	
	public final static int RESET_PASSWORD_CHECK_FAILED = 3001;//找回密码时密码校验失败
	public final static int MODIFY_PASSWORD_CHECK_FAILED = 4001;//重置密码时密码校验失败
	
	public final static int TOO_MUCH_PHOTO_SIZE = 5001;//图片尺寸过大
	public final static int PET_NOT_FOUND = 5002; //宠物信息不存在
	public final static int WRONG_PHOTO_TYPE = 5003;//图片文件有误
	
}
