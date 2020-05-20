package rest.api.spring.boot.cache.job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.api.spring.boot.cache.job.annotation.cache.CacheableKey;
import rest.api.spring.boot.cache.job.cron.job.JobClearCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PathDefault {

    private JobClearCache jobClearCache;

    @Cacheable(value = "pathDefault")
    @GetMapping(path = "/")
    public ResponseEntity getCache() {
        Map toJson = new HashMap();
        toJson.put("nome", "jakson");

        jobClearCache.evictSingleCacheValue("pathDefault","getCacheV2");

        return ResponseEntity.ok(toJson);
    }

    @CacheableKey(value = "pathDefault")
    @GetMapping(path = "/v2")
    public ResponseEntity getCacheV2(HttpServletRequest request, HttpServletResponse response) {
        Map toJson = new HashMap();
        toJson.put("idade", "22");
        return ResponseEntity.ok(toJson);
    }
}
