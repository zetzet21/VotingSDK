package zetzet.workspace.sdk_voting_t1.kano;

import org.springframework.stereotype.Component;

@Component
public class KanoClassification {

    public String classify(String positiveResponse, String negativeResponse) {
        if ("Very satisfied".equals(positiveResponse)) {
            if ("Very dissatisfied".equals(negativeResponse)) {
                return "Attractive";  // Удовольствие
            } else if ("Dissatisfied".equals(negativeResponse)) {
                return "Performance";  // Ожидаемые
            } else if ("Neutral".equals(negativeResponse)) {
                return "Indifferent";  // Безразличные
            } else if ("Satisfied".equals(negativeResponse)) {
                return "Reverse";  // Обратные
            } else if ("Very satisfied".equals(negativeResponse)) {
                return "Must-be";  // Основные
            }
        } else if ("Satisfied".equals(positiveResponse)) {
            if ("Dissatisfied".equals(negativeResponse)) {
                return "Performance";  // Ожидаемые
            } else if ("Neutral".equals(negativeResponse)) {
                return "Indifferent";  // Безразличные
            } else if ("Satisfied".equals(negativeResponse)) {
                return "Reverse";  // Обратные
            } else if ("Very satisfied".equals(negativeResponse)) {
                return "Must-be";  // Основные
            }
        } else if ("Neutral".equals(positiveResponse)) {
            if ("Neutral".equals(negativeResponse)) {
                return "Indifferent";  // Безразличные
            } else if ("Satisfied".equals(negativeResponse)) {
                return "Reverse";  // Обратные
            } else if ("Very satisfied".equals(negativeResponse)) {
                return "Must-be";  // Основные
            }
        } else if ("Dissatisfied".equals(positiveResponse)) {
            if ("Satisfied".equals(negativeResponse)) {
                return "Reverse";  // Обратные
            } else if ("Very satisfied".equals(negativeResponse)) {
                return "Must-be";  // Основные
            }
        } else if ("Very dissatisfied".equals(positiveResponse)) {
            if ("Very satisfied".equals(negativeResponse)) {
                return "Must-be";  // Основные
            }
        }

        return "Unknown";
    }
}
