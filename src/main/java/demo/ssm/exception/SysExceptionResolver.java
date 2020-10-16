package demo.ssm.exception;

import demo.ssm.common.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.io.IOException;
import java.net.BindException;

/**
 *
 * 异常处理器
 */
public class SysExceptionResolver implements HandlerExceptionResolver {

    /**
     * 处理异常业务逻辑
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv;
        System.out.println(ex.toString());
        // 获取到异常对象
        Exception e = null;
        if (ex instanceof SysException) {
            e = (SysException) ex;
        }
        else if(ex instanceof ValidationException){
            e = (ValidationException) ex;
        }
        else {
            e = new SysException("系统正在维护....");
        }

        response.setContentType("application/json;charset=utf-8");
        mv = new ModelAndView(new MappingJackson2JsonView());
        mv.addObject(JsonResult.error(e.getMessage()));
        return mv;
    }

}














