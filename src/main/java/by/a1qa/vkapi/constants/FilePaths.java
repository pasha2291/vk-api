package by.a1qa.vkapi.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FilePaths {
    private static final String RESOURCES_PATH = "src/test/resources";
    private static final String TEST_DATA_PATH = String.format("%s/testdata", RESOURCES_PATH);
    public static final String CONFIG_FILE_PATH = String.format("%s/config.json", RESOURCES_PATH);
    public static final String CREDENTIALS_FILE_PATH = String.format("%s/credentials.json", TEST_DATA_PATH);
    public static final String PICTURE_FILE_PATH = String.format("%s/evil_mem.jpg", TEST_DATA_PATH);
}
