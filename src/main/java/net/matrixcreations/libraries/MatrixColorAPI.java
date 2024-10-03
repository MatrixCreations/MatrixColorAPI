package net.matrixcreations.libraries;

import net.matrixcreations.libraries.utils.GradientUtils;
import net.matrixcreations.libraries.utils.HexColorUtils;
import net.matrixcreations.libraries.utils.LegacyCodeUtils;
import net.matrixcreations.libraries.utils.SolidColorUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class MatrixColorAPI {

    public static void main(String[] args) {
        logFormatExamples();
    }

    public static String process(String text) {
        text = GradientUtils.processGradients(text);
        text = SolidColorUtils.processSolidColors(text);
        text = HexColorUtils.processHexColors(text);
        return LegacyCodeUtils.processLegacyCodes(text);
    }

    public static List<String> process(List<String> texts) {
        return texts.stream().map(MatrixColorAPI::process).collect(Collectors.toList());
    }

    private static void logFormatExamples() {
        List<String> examples = Arrays.asList(
                "&#FFD700Normal text",
                "&#FF0000&lBold red text",
                "<GRADIENT:#FFD700>Hello this is gradient text</GRADIENT:#FF0000>",
                "<GRADIENT:#FFD700>&lHello this is bold gradient text</GRADIENT:#FF0000>",
                "<SOLID:#FFD700>Hello this is solid yellow text",
                "<SOLID:#FFD700>&lHello this is solid bold yellow text"
        );

        // Process and log each format example
        ColorUtils.process(examples).forEach(MatrixColorAPI::sendConsoleMessage);
    }

    public static void sendConsoleMessage(String message) {
        try {
            // Load the Bukkit class dynamically (without direct dependency)
            Class<?> bukkitClass = Class.forName("org.bukkit.Bukkit");

            // Get the getConsoleSender method from Bukkit class
            Method getConsoleSenderMethod = bukkitClass.getMethod("getConsoleSender");

            // Invoke the method to get the console sender
            Object consoleSender = getConsoleSenderMethod.invoke(null);

            // Now find the sendMessage method from the console sender object
            Method sendMessageMethod = consoleSender.getClass().getMethod("sendMessage", String.class);

            // Invoke the sendMessage method on the console sender object
            sendMessageMethod.invoke(consoleSender, message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
