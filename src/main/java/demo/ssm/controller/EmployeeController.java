package demo.ssm.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import demo.ssm.common.JsonResult;
import demo.ssm.entity.Employee;
import demo.ssm.exception.SysException;
import demo.ssm.mapper.EmployeeMapper;
import demo.ssm.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 邝明山
 * @since 2020-10-16
 */
@RestController
//@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeMapper employeeMapper;

    @ApiOperation("根据id获得用户")
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public JsonResult getById(String id) {
        return JsonResult.isOk(employeeService.getById(id));
    }

    @ApiOperation("分页查询，id，第几页，页面大小")
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public JsonResult getList(int pageNum, int pageSize) {
        Page<Employee> employeePage = new Page<>(pageNum, pageSize); //arg1  当前页    arg2   页大小
        employeeMapper.selectPage(employeePage, null);
        System.out.println("返回总数据数" + employeePage.getTotal());
        System.out.println("是否还有下一页" + employeePage.hasNext());
        return JsonResult.isOk(employeePage.getRecords());
    }

    @ApiOperation("测试统一异常处理,如果flag==1就出错")
    @RequestMapping(value = "TestException", method = RequestMethod.GET)
    public JsonResult TestException(int flag) throws SysException {
        if (flag == 1) {
            throw new SysException("测试统一异常处理");
        }
        return JsonResult.isOk();
    }

    @ApiOperation("通过Id删除")
    @RequestMapping(value = "deleteById", method = RequestMethod.DELETE)
    public void deleteById(int id) throws SysException {
        boolean result = employeeService.removeById(id);
        if (!result) {
            throw new SysException("员工Id不存在或已删除");
        }
    }

    @ApiOperation("通过Id修改员工")
    @RequestMapping(value = "updateById", method = RequestMethod.PUT)
    public void updateById(@RequestBody @Valid Employee employee,BindingResult bindingResult) throws SysException {
        if(bindingResult.hasErrors()||employee.getId()==null){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List errorList=new ArrayList();
            for(FieldError error: fieldErrors){
                errorList.add(error.getDefaultMessage());
            }
            if (employee.getId()==null){
                errorList.add("id不能为空");
            }
            throw new SysException(errorList.toString());
        }
        boolean result = employeeService.updateById(employee);
        if (!result) {
            throw new SysException("员工Id不存在");
        }
    }

    @ApiOperation("新增员工")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public void insert(@RequestBody @Valid Employee employee,BindingResult bindingResult) throws SysException {
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List errorList=new ArrayList();
            for(FieldError error: fieldErrors){
                errorList.add(error.getDefaultMessage());
            }
            throw new SysException(errorList.toString());
        }
        boolean result = employeeService.save(employee);
        if (!result) {
            throw new SysException("增加错误");
        }
    }
}

