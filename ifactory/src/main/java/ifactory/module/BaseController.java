package ifactory.module;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 通用方法的封装
 * 
 * @author 朱雷 2015-04-23
 *
 */
public class BaseController {

	protected Logger log = LoggerFactory.getLogger(getClass());
	
	public void printWriter(Object o, HttpServletResponse response) {

		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			try {
				if (o != null) {
					out.print(o);
				} else {
					out.print("操作失败，请重新再试");
				}
			} catch (Exception e) {
				log.error("oops,printWriter got an exception: ", e);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			log.error("");
		}
	}
}