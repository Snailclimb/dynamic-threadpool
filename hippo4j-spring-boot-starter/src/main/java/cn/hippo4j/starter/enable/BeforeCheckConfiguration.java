package cn.hippo4j.starter.enable;

import cn.hippo4j.starter.config.BootstrapProperties;
import cn.hippo4j.starter.core.ConfigEmptyException;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Before check configuration.
 *
 * @author chen.ma
 * @date 2021/11/28 22:44
 */
@AllArgsConstructor
@Configuration(proxyBeanMethods = false)
public class BeforeCheckConfiguration {

    @Bean
    public BeforeCheckConfiguration.BeforeCheck dynamicThreadPoolBeforeCheckBean(BootstrapProperties properties, ConfigurableEnvironment environment) {
        String namespace = properties.getNamespace();
        if (StrUtil.isBlank(namespace)) {
            throw new ConfigEmptyException(
                    "Web server failed to start. The dynamic thread pool namespace is empty.",
                    "Please check whether the [spring.dynamic.thread-pool.namespace] configuration is empty or an empty string."
            );
        }

        String itemId = properties.getItemId();
        if (StrUtil.isBlank(itemId)) {
            throw new ConfigEmptyException(
                    "Web server failed to start. The dynamic thread pool item id is empty.",
                    "Please check whether the [spring.dynamic.thread-pool.item-id] configuration is empty or an empty string."
            );
        }

        String serverAddr = properties.getServerAddr();
        if (StrUtil.isBlank(serverAddr)) {
            throw new ConfigEmptyException(
                    "Web server failed to start. The dynamic thread pool server addr is empty.",
                    "Please check whether the [spring.dynamic.thread-pool.server-addr] configuration is empty or an empty string."
            );
        }

        String applicationName = environment.getProperty("spring.application.name");
        if (StrUtil.isBlank(applicationName)) {
            throw new ConfigEmptyException(
                    "Web server failed to start. The dynamic thread pool application name is empty.",
                    "Please check whether the [spring.application.name] configuration is empty or an empty string."
            );
        }

        return new BeforeCheckConfiguration.BeforeCheck();
    }

    public class BeforeCheck {

    }

}
