package rest.api.spring.boot.cache.job.config.cache;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

@Configuration
@EnableCaching
public class ConfigCache implements CacheManagerCustomizer<ConcurrentMapCacheManager>, KeyGenerator {

    @Override
    public void customize(ConcurrentMapCacheManager cacheManager) {
        cacheManager.setCacheNames(Arrays.asList("pathDefault"));
    }

    @Override
    public Object generate(Object target, Method method, Object... params) {
        String key = target.getClass().getSimpleName() + "_"
                + method.getName() + "_"
                + StringUtils.arrayToDelimitedString(params, "_");
        return key;
    }

    @Bean("configCacheKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new ConfigCache();
    }
}
