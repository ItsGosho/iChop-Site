package ichop.core.areas.player.domain.models.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class PlayerProfileViewModel {

    private String uuid;
    private String name;
    private Boolean isOnline;
    private Integer totalDeaths;
    private Integer totalPlayerKills;
    private Integer totalMobKills;
    private Integer totalDamageDealt;
    private Integer totalDamageTaken;
    private String siteUserUsername;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastJoin;

    public boolean getIsOnline() {
        return this.isOnline;
    }
}
