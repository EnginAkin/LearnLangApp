package tr.com.english.learnlang.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse {
    private String message;
    private Boolean successful;
    private List<Object> elements;

    public GeneralResponse(String message, Boolean successful, Object element) {
        this.message = message;
        this.successful = successful;
        this.elements = new ArrayList<>();
        elements.add(element);
    }

    public GeneralResponse(String message, Boolean successful) {
        this.message = message;
        this.successful = successful;
    }
}
