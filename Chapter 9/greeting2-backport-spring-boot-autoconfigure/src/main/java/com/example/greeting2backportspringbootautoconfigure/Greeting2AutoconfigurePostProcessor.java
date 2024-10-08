package com.example.greeting2backportspringbootautoconfigure;

import com.example.greeting.GreetingApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.util.ClassUtils;

@Slf4j
public class Greeting2AutoconfigurePostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        boolean hasClass = ClassUtils.isPresent("com.example.greeting.GreetingApplication",
                Greeting2AutoconfigurePostProcessor.class.getClassLoader());
        if (!hasClass) {
            log.info("GreetingApplication is NOT present in CLASSPATH.");
            return;
        }

        if (beanFactory.containsBeanDefinition("greetingApplication")) {
            log.info("We already have a greetingApplication bean registered.");
            return;
        }
        log.info("register-------------------");
        register(beanFactory);
    }

    private void register(ConfigurableListableBeanFactory beanFactory) {

        if (beanFactory instanceof BeanDefinitionRegistry) {
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(GreetingApplication.class);

            ((BeanDefinitionRegistry) beanFactory)
                    .registerBeanDefinition("greetingApplication",
                            beanDefinition);
        }
        else {
            beanFactory.registerSingleton("greetingApplication",
                    new GreetingApplication());
        }
    }
}
