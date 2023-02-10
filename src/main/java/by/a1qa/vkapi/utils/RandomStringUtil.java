package by.a1qa.vkapi.utils;

import by.a1qa.vkapi.utils.logger.CustomLogger;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

@UtilityClass
public class RandomStringUtil {

    private static final int MAX_TEXT_LENGTH = 100;
    private static final int MIN_POSSIBLE_TEXT_LENGTH = 1;
    private static Random random = new Random();

    private static String getRandomString(int maxLength) {
        return RandomStringUtils.random(random.nextInt(maxLength) + MIN_POSSIBLE_TEXT_LENGTH,
                true, true);
    }

    public static String getRandomText() {
        String result = getRandomString(MAX_TEXT_LENGTH);
        CustomLogger.info(String.format("RandomStringUtil.getRandomText() : %s", result));
        return result;
    }
}
