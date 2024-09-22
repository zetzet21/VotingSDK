package zetzet.workspace.sdk_voting_t1.kano;

import org.springframework.stereotype.Component;

@Component
public class KanoClassification {

    public String classify(String positiveResponse, String negativeResponse) {
        if ("Very satisfied".equals(positiveResponse)) {
            if ("Very dissatisfied".equals(negativeResponse)) {
                return "Important";  // Важаная
            } else if ("Dissatisfied".equals(negativeResponse)
                        || "Neutral".equals(negativeResponse)
                        || "Satisfied".equals(negativeResponse)
            ) {
                return "Required";  // Обязательная
            } else if ("Very satisfied".equals(negativeResponse)) {
                return "Questionable";  // Сомнительная
            }
        }


        if ("Satisfied".equals(positiveResponse)
            || "Neutral".equals(positiveResponse)
            || "Dissatisfied".equals(positiveResponse)
        ) {
            if ("Very dissatisfied".equals(negativeResponse)) {
                return "Interesting";  // Интересная
            } else if ("Dissatisfied".equals(negativeResponse)
                        || "Neutral".equals(negativeResponse)
                        || "Satisfied".equals(negativeResponse)
            ) {
                return "Indifference";  // Безраличная
            } else if ("Very satisfied".equals(negativeResponse)) {
                return "Undesirable";  // Нежелательная
            }
        }

        if ("Very dissatisfied".equals(positiveResponse)) {
            if ("Very dissatisfied".equals(negativeResponse)) {
                return "Undesirable";  // Нежелательная
            }
            else {
                return "Questionable";  // Сомнительная
            }
        }

        return "Unknown";
    }
}
