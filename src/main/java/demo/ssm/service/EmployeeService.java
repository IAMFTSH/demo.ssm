package demo.ssm.service;

import demo.ssm.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 邝明山
 * @since 2020-10-16
 */
public interface EmployeeService extends IService<Employee> {
    public boolean sendSMS(String s);

}
