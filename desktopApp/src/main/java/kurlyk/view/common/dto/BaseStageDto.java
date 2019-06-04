package kurlyk.view.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseStageDto {
    private boolean needMenu;

    public static BaseStageDto allInclusive(){
        return BaseStageDto
                .builder()
                .needMenu(true)
                .build();
    }

    public static BaseStageDto allOff(){
        return BaseStageDto
                .builder()
                .needMenu(false)
                .build();
    }
}
