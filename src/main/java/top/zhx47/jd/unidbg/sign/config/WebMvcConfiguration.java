package top.zhx47.jd.unidbg.sign.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //添加fastjson的配置信息，比如是否要格式化返回的json数据；
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        config.setCharset(StandardCharsets.UTF_8);
        config.setSerializerFeatures(
                //将字符串类型字段的空值输出为空字符串
                SerializerFeature.WriteNullStringAsEmpty,
                //json格式化
                SerializerFeature.PrettyFormat,
                //输出map中value为null的数据
                SerializerFeature.WriteMapNullValue,
                //输出boolean 为 false
                SerializerFeature.WriteNullBooleanAsFalse,
                //输出list 为 []
                SerializerFeature.WriteNullListAsEmpty,
                //输出number 为 0
                SerializerFeature.WriteNullNumberAsZero,
                //对map进行排序
                SerializerFeature.MapSortField
        );
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //设置支持的媒体类型
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        //将convert添加到converters
        converters.add(0, converter);

        //在convert中添加配置信息
        converter.setFastJsonConfig(config);

        //解决返回字符串带双引号问题
//        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
//        stringHttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
//        stringHttpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
        converters.add(0, converter);
    }
}
 