package michalwds.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanetDTO {

    private String planetName;

    private long distanceFromSun;

    private double oneWayLightTimeToTheSun;

    private long lengthOfYears;

    private String planetType;

    private String planetInfo;

    private String planetImage;

}
