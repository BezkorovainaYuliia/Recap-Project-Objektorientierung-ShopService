import lombok.With;

import java.time.ZonedDateTime;
import java.util.List;
@With
public record Order(
        String id,
        List<Product> products,
        Status status,
        ZonedDateTime zonedDateTime
) {
}
