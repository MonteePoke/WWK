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
    private boolean needTree;

    public static BaseStageDto allInclusive(){
        return BaseStageDto
                .builder()
                .needMenu(true)
                .needTree(true)
                .build();
    }

    public static BaseStageDto allOff(){
        return BaseStageDto
                .builder()
                .needMenu(false)
                .needTree(false)
                .build();
    }
}
