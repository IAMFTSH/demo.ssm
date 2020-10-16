package demo.ssm.common;

/**
 * 状态码 200 成功 500 表示未知错误
 * 
 * @author jsjxy
 *
 */
public class JsonResult {
	/**
	 * 状态码
	 */
	private Integer status;
	/**
	 * 返回信息
	 */
	private String msg;
	/**
	 * 如果有返回，则存放该返回的数据
	 */
	private Object data;

	public JsonResult() {
	}

	public JsonResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public static JsonResult isOk() {
		return new JsonResult(200, "success", null);
	}

	public static JsonResult isOk(String msg, Object data) {
		return new JsonResult(200, msg, data);
	}


	public static JsonResult isOk(Object data) {
		return new JsonResult(200, "success", data);
	}

	public static JsonResult errorUnKnow(String msg) {
		return new JsonResult(500, "UnKnow error", null);
	}

	public static JsonResult error(String msg,Object data) {
		return new JsonResult(500, msg, data);
	}
	public static JsonResult error(String msg) {
		return new JsonResult(500, msg, null);
	}
	public static JsonResult errorUnAuthentication(String msg,Object data) {
		return new JsonResult(401, msg, data);
	}

	public static JsonResult errorUnAuthentication(String msg) {
		return new JsonResult(401, msg, null);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
