package ir.hamycook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse {
    private List<ErrorMessage> messages = new ArrayList<>();

    public void addErrorMessage(String label, String message) {
        messages.add(new ErrorMessage(label, message));
    }

    @AllArgsConstructor
    @Getter
    class ErrorMessage {
        private String label;
        private String message;
    }

}
