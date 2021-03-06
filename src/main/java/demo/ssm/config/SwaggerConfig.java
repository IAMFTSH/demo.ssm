package demo.ssm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 邝明山
 * @since 2020-05-27
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
//http://localhost:8080/doc.htmlt
//http://localhost:8080/swagger-ui.html
public class SwaggerConfig {
    public static final String AUTHORIZATION_HEADER = "token";
    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
    @Bean
    public Docket docket(Environment environment){
        //Profiles profiles=Profiles.of("dev","test");  //dev环境和test环境
        //如果配置了相应的application.yaml环境
        //1.application.yaml中加入
/*        spring:
            profiles:
                active: dev
          这样会使用application-dev.yaml的配置比如dev端口设置为8888   application-pro.yaml 端口设置为   9999   上面值为dev的话，启动端口是8888
          2.
        建立新的配置文档    application-dev.yaml
        建立新的配置文档    application-pro.yaml
        3.
                把flag=environment.acceptsProfiles(profiles);替换调下面flag=true；

 */
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name(AUTHORIZATION_HEADER).description("user ticket")//Token 以及Authorization 为自定义的参数，session保存的名字是哪个就可以写成那个
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数
        boolean flag=true;

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("分组A")
                .enable(flag)   //是否启用Swagger  默认启用
                .select()
                //basePackage需要扫描的包，默认还会会扫描错误页面的controller和容器内的Controller
                //any全部，   没实验
                // none不扫描，   没实验
                // withClassAnnotation扫描类上的注解（arg是射对象）   没实验
                //withMethodAnnotation扫描方法的注解  懒得管了
                .apis(RequestHandlerSelectors.basePackage("demo.ssm.controller"))
                //过滤  只扫描请求路径中符合的
/*                .paths(PathSelectors.ant("/user/**"))*/
                .build()
                .globalOperationParameters(pars);
    }

    @Bean
    public Docket docket2(Environment environment){
        //Profiles profiles=Profiles.of("dev","test");  //dev环境和test环境
        boolean flag=true;  //如果当前环境符合上面两个值，flag=true

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("分组B")
                .enable(flag)   //是否启用Swagger  默认启用
                .select()
                //basePackage需要扫描的包，默认还会会扫描错误页面的controller和容器内的Controller
                //any全部，   没实验
                // none不扫描，   没实验
                // withClassAnnotation扫描类上的注解（arg是射对象）   没实验
                //withMethodAnnotation扫描方法的注解  懒得管了
                .apis(RequestHandlerSelectors.basePackage("com.springboot.model.controller"))
                //过滤  只扫描请求路径中符合的
/*                .paths(PathSelectors.ant("/user/**"))*/
                .build();
    }

    @Bean
    public Docket docket3(Environment environment){
        //Profiles profiles=Profiles.of("dev","test");  //dev环境和test环境
        boolean flag=true;  //如果当前环境符合上面两个值，flag=true

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("分组C")
                .enable(flag)   //是否启用Swagger  默认启用
                .select()
                //basePackage需要扫描的包，默认还会会扫描错误页面的controller和容器内的Controller
                //any全部，   没实验
                // none不扫描，   没实验
                // withClassAnnotation扫描类上的注解（arg是射对象）   没实验
                //withMethodAnnotation扫描方法的注解  懒得管了
                .apis(RequestHandlerSelectors.basePackage("com.springboot.model.controller"))
                //过滤  只扫描请求路径中符合的
/*                .paths(PathSelectors.ant("/user/**"))*/
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact=new Contact("FTSH","https://www.hao123.com/","2244456@qq.com");
        return  new ApiInfo(
                "title",
                "description",
                "version",
                "https://www.baidu.com/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
