package rest.api.spring.boot.cache.job.cron.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class JobClearCache {

    private static final Logger logger = LoggerFactory.getLogger(JobClearCache.class);

    @Autowired
    private CacheManager cacheManager;

    @Scheduled(cron = "0 * * ? * *")
    public void clearCache() {
        cacheManager.getCacheNames().stream().forEach(this::evictAllCacheValues);
    }

    public void evictSingleCacheValue(String cacheName, String cacheKey) {
        logger.info("Clear Cache.");
        cacheManager.getCache(cacheName).evict(cacheKey);
    }

    public void evictAllCacheValues(String cacheName) {
        logger.info("Clear Cache.");
        cacheManager.getCache(cacheName).clear();
    }
}
