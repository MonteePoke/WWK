package kurlyk.transfer;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabWorkDto {
    private Long id;
    private String name;
    private Integer atemptsNumber;
}
