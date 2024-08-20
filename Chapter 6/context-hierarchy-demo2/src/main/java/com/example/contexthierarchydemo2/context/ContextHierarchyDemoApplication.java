package com.example.contexthierarchydemo2.context;


import com.example.contexthierarchydemo2.foo.FooConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@Slf4j
public class ContextHierarchyDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(ContextHierarchyDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ApplicationContext parentCtx = new AnnotationConfigApplicationContext(FooConfig.class);

		ClassPathXmlApplicationContext childCtx = new ClassPathXmlApplicationContext(
				new String[] {"applicationContext.xml"}, parentCtx);
		TestBean bean = parentCtx.getBean("testBeanX", TestBean.class);
		bean.hello();

		log.info("=============");

		bean = childCtx.getBean("testBeanX", TestBean.class);
		bean.hello();

		bean = childCtx.getBean("testBeanY", TestBean.class);
		bean.hello();
	}
}
