package com.tencent.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 
* <p>Title: CustomerDateConverter</p>  
* <p>
*	Description: 
*   自定义转换器:
*   	1. 自定义类实现org.springframework.core.convert.converter.Converter;
*   
*   	2. 实现接口中的conver方法
*   
*   	3. 在spring-mvc.xml中管理转换器
*   	<!-- 4. 自定义参数配置 -->
	<bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
		<!-- 自定义转换器 -->
		<property name="converters">
			<set>
				<bean class="com.tencent.converter.CustomerDateConverter"></bean>
			</set>
		</property>
	</bean> 
*   	4. 注入到适配器中
*   	<!--1. 开启注解方式的处理器映射器、处理器适配器-->
*   	<mvc:annotation-driven conversion-service="conversionService"/>
* </p> 
* @author xianxian 
* @date 2019年10月16日
 */
public class CustomerDateConverter implements Converter<String, Date> {


	@Override
	public Date convert(String source) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
