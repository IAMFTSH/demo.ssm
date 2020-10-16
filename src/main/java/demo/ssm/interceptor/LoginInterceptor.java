package demo.ssm.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import demo.ssm.common.JWT;
import demo.ssm.common.JsonResult;
import demo.ssm.entity.Employee;
import demo.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author 邝明山
 * on 2020/10/16 18:14
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    EmployeeService employeeService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        //token不存在
        if (null != token) {
            Employee employee = JWT.unsign(token, Employee.class);
            QueryWrapper<Employee> wrapper=new QueryWrapper();
            wrapper.eq("phone",employee.getPhone()).eq("password",employee.getPassword());
            Employee employeeFromDatabase = employeeService.getOne(wrapper);
            if (null!=employeeFromDatabase) {
                return true;
            }
        }else {
            responseMessage(response, response.getWriter(), JsonResult.errorUnAuthentication("没有登录"));
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private void responseMessage(HttpServletResponse response, PrintWriter out, JsonResult responseData) {
        response.setContentType("application/json; charset=utf-8");
        String json = JSONObject.toJSONString(responseData);
        out.print(json);
        out.flush();
        out.close();
    }
}
