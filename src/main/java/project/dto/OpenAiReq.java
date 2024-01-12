package project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpenAiReq {
    private String model;
    private List<Message> messages;
    private int n;
    private double temperature;

    public OpenAiReq(String model, String prompt) {
        this.model = model;
        this.n = 1;
        this.temperature = 1;

        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Message {

        private String role;
        private String content;

    }
}
