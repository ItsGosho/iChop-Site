package com.ichop.core.scheduled;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PlayerCacheCleaner {

    /*
    *
    * Clear all cached data for cacheNames players.
    * This operations happens every 5 minute.
    *
    * */
    @CacheEvict(allEntries = true,value = "players")
    @Scheduled(cron = "0 */5 * ? * *")
    public void clearPlayerCaches(){
        System.out.println("All caches for player has been removed.");
    }

}
