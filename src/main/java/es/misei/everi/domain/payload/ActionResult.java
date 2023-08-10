package es.misei.everi.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionResult {
    private boolean result;
    private String message;

    public static ActionResult TRUE() {
        return new ActionResult(true, "Successful operation!");
    }

    public static ActionResult FALSE(String message) {
        return new ActionResult(false, message);
    }
}
