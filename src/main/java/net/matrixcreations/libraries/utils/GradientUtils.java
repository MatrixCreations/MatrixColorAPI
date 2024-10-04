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

        boolean bold = false;
        boolean italic = false;
        boolean underlined = false;
        boolean strikethrough = false;
        boolean obfuscated = false;

        int length = text.length();
        for (int i = 0; i < length; i++) {
            char currentChar = text.charAt(i);

            if (currentChar == '§') {
                i++;
                char formatCode = text.charAt(i);
                switch (formatCode) {
                    case 'l': bold = true; break;
                    case 'o': italic = true; break;
                    case 'n': underlined = true; break;
                    case 'm': strikethrough = true; break;
                    case 'k': obfuscated = true; break;
                    case 'r':
                        bold = italic = underlined = strikethrough = obfuscated = false;
                        break;
                }
                continue;
            }

            float ratio = (float) i / length;
            TextColor color = ColorInterpolater.interpolateColor(start, end, ratio);

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
}