package kurlyk.transfer;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLabWorkDto {
    private Long userId;
    private Long labWorkId;
}
