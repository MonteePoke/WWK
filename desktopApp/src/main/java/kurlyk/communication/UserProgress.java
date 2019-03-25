package kurlyk.communication;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class UserProgress {
    private Map<Long, Integer> progress = new HashMap<>();
}
