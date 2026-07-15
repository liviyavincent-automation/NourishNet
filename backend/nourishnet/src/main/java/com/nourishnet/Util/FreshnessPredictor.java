package com.nourishnet.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class FreshnessPredictor {

    public static String predictFreshness(String foodType, String preparationTime) {

        LocalDateTime preparedTime =
                LocalDateTime.parse(preparationTime);

        LocalDateTime currentTime =
                LocalDateTime.now();

        long hours =
                Duration.between(preparedTime, currentTime).toHours();

        switch (foodType.toLowerCase()) {

    case "cooked food":
        if (hours <= 4)
            return "Fresh";
        else if (hours <= 8)
            return "Consume Soon";
        else
            return "Expired";

    case "packed food":
        if (hours <= 24)
            return "Fresh";
        else if (hours <= 72)
            return "Consume Soon";
        else
            return "Expired";

    case "groceries":
        if (hours <= 168)   // 7 days
            return "Fresh";
        else if (hours <= 336) // 14 days
            return "Consume Soon";
        else
            return "Expired";

    default:
        return "Unknown";
}
    }
}