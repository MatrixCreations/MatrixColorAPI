package net.matrixcreations.libraries.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.matrixcreations.libraries.interpolaters.ColorInterpolater;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WithoutHexUtils {

    // Patterns for handling colors without #
    private static final Pattern GRADIENT_PATTERN = Pattern.compile("<GRADIENT:([A-Fa-f0-9]{6})>(.*?)</GRADIENT:([A-Fa-f0-9]{6})>");
    private static final Pattern SOLID_PATTERN = Pattern.compile("<SOLID:([A-Fa-f0-9]{6})>(.*?)(?:</SOLID>|$)");

    public static String process(String text) {
        text = processGradients(text);
        text = processSolidColors(text);
        return processLegacyCodes(text);
    }

    private static String processGradients(String text) {
        Matcher matcher = GRADIENT_PATTERN.matcher(text);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String startColor = matcher.group(1);
            String content = matcher.group(2);
            String endColor = matcher.group(3);

            String gradientText = applyGradient(content, startColor, endColor);
            matcher.appendReplacement(buffer, Matcher.quoteReplacement(gradientText));
        }

        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private static String processSolidColors(String text) {
        Matcher matcher = SOLID_PATTERN.matcher(text);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String hexColor = matcher.group(1);
            String content = matcher.group(2);

            String solidColorText = applySolidColor(content, hexColor);
            matcher.appendReplacement(buffer, Matcher.quoteReplacement(solidColorText));
        }

        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private static String processLegacyCodes(String text) {
        return text.replace('&', '§');
    }

    private static String applyGradient(String text, String startColor, String endColor) {
        // Use the hex color strings without the '#' prefix directly
        TextColor start = TextColor.fromHexString("#" + startColor);
        TextColor end = TextColor.fromHexString("#" + endColor);

        StringBuilder result = new StringBuilder();
        int length = text.length();

        boolean isBold = false;
        boolean isItalic = false;
        boolean isUnderlined = false;
        boolean isStrikethrough = false;
        boolean isObfuscated = false;

        for (int i = 0; i < length; i++) {
            // Calculate the gradient color based on the position in the text
            float ratio = (float) i / (length - 1);
            TextColor color = ColorInterpolater.interpolateColor(start, end, ratio);

            char currentChar = text.charAt(i);
            if (currentChar == '§') {
                if (i + 1 < length) {
                    char formatCode = text.charAt(i + 1);
                    switch (formatCode) {
                        case 'l': isBold = true; break;
                        case 'o': isItalic = true; break;
                        case 'n': isUnderlined = true; break;
                        case 'm': isStrikethrough = true; break;
                        case 'k': isObfuscated = true; break;
                        case 'r': // Reset formatting
                            isBold = false;
                            isItalic = false;
                            isUnderlined = false;
                            isStrikethrough = false;
                            isObfuscated = false;
                            break;
                    }
                    i++;
                    continue;
                }
            }

            TextComponent.Builder component = Component.text()
                    .content(String.valueOf(currentChar))
                    .color(color);

            if (isBold) component.decorate(TextDecoration.BOLD);
            if (isItalic) component.decorate(TextDecoration.ITALIC);
            if (isUnderlined) component.decorate(TextDecoration.UNDERLINED);
            if (isStrikethrough) component.decorate(TextDecoration.STRIKETHROUGH);
            if (isObfuscated) component.decorate(TextDecoration.OBFUSCATED);

            result.append(LegacyComponentSerializer.legacySection().serialize(component.build()));
        }

        return result.toString();
    }

    private static String applySolidColor(String text, String hexColor) {
        TextColor color = TextColor.fromHexString("#" + hexColor);

        StringBuilder result = new StringBuilder();

        // Initialize variables to track formatting changes
        boolean bold = false;
        boolean italic = false;
        boolean underlined = false;
        boolean strikethrough = false;
        boolean obfuscated = false;

        int length = text.length();
        for (int i = 0; i < length; i++) {
            char currentChar = text.charAt(i);

            // Handle formatting codes
            if (currentChar == '§') {
                i++; // Move to next char which holds the format code
                char formatCode = text.charAt(i);
                switch (formatCode) {
                    case 'l': bold = true; break;
                    case 'o': italic = true; break;
                    case 'n': underlined = true; break;
                    case 'm': strikethrough = true; break;
                    case 'k': obfuscated = true; break;
                    case 'r': // Reset all formatting
                        bold = italic = underlined = strikethrough = obfuscated = false;
                        break;
                }
                continue; // Skip processing this char
            }

            // Build text component with solid color and proper formatting
            TextComponent.Builder component = Component.text()
                    .content(String.valueOf(currentChar))
                    .color(color);

            if (bold) component.decorate(TextDecoration.BOLD);
            if (italic) component.decorate(TextDecoration.ITALIC);
            if (underlined) component.decorate(TextDecoration.UNDERLINED);
            if (strikethrough) component.decorate(TextDecoration.STRIKETHROUGH);
            if (obfuscated) component.decorate(TextDecoration.OBFUSCATED);

            result.append(LegacyComponentSerializer.legacySection().serialize(component.build()));
        }

        return result.toString();
    }

    private static TextColor interpolateColor(TextColor start, TextColor end, float ratio) {
        int red = (int) (start.red() + (end.red() - start.red()) * ratio);
        int green = (int) (start.green() + (end.green() - start.green()) * ratio);
        int blue = (int) (start.blue() + (end.blue() - start.blue()) * ratio);

        return TextColor.color(red, green, blue);
    }
}
