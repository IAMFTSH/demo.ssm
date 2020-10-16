package demo.ssm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import demo.ssm.common.RedisUtil;
import demo.ssm.entity.Employee;
import demo.ssm.mapper.EmployeeMapper;
import demo.ssm.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

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
    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean sendSMS(String mobile) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G6u7HfYLHYjLw5x3jgb", "m8EuaQohDq1XaL66g9mNGdR5isbpA1");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", mobile);
        //签名
        request.putQueryParameter("SignName", "ABC商城");
        //模板
        request.putQueryParameter("TemplateCode", "SMS_204126876");
        //构建验证码
        HashMap<String, Object> TemplateParamMap = new HashMap<>();
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            //每次随机出一个数字（0-9）
            int r = random.nextInt(10);
            //把每次随机出的数字拼在一起
            code = code + r;
        }
        TemplateParamMap.put("code", code);
        redisUtil.setString("mobile"+mobile,code);
        //验证码
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(TemplateParamMap));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
