package net.matrixcreations.libraries.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.matrixcreations.libraries.interpolaters.ColorInterpolater;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GradientUtils {

    private static final Pattern GRADIENT_PATTERN = Pattern.compile("<GRADIENT:#([A-Fa-f0-9]{6})>(.*?)</GRADIENT:#([A-Fa-f0-9]{6})>");

    public static String processGradients(String text) {
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

    private static String applyGradient(String text, String startColor, String endColor) {
        TextColor start = TextColor.fromHexString("#" + startColor);
        TextColor end = TextColor.fromHexString("#" + endColor);

        StringBuilder result = new StringBuilder();
        int length = text.length();

        boolean isBold = false;
        boolean isItalic = false;
        boolean isUnderlined = false;
        boolean isStrikethrough = false;
        boolean isObfuscated = false;

        // Iterate through the text to check and apply formatting and gradient colors
        for (int i = 0; i < length; i++) {
            // Calculate the gradient color based on the position in the text
            float ratio = (float) i / (length - 1);  // Smooth transition across entire text
            TextColor color = ColorInterpolater.interpolateColor(start, end, ratio);

            // Check if any formatting should be applied for this part
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
                    i++; // Skip the formatting code
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
}