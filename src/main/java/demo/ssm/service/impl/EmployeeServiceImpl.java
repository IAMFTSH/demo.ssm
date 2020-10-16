package demo.ssm.service.impl;

import demo.ssm.entity.Employee;
import demo.ssm.mapper.EmployeeMapper;
import demo.ssm.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 邝明山
 * @since 2020-10-16
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
