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
        boolean isBold = text.contains("§l") || text.contains("&l");
        boolean isItalic = text.contains("§o") || text.contains("&o");
        boolean isUnderlined = text.contains("§n") || text.contains("&n");
        boolean isStrikethrough = text.contains("§m") || text.contains("&m");
        boolean isObfuscated = text.contains("§k") || text.contains("&k");

        // Remove formatting markers from the actual text before processing
        text = text.replace("§l", "").replace("&l", "");
        text = text.replace("§o", "").replace("&o", "");
        text = text.replace("§n", "").replace("&n", "");
        text = text.replace("§m", "").replace("&m", "");
        text = text.replace("§k", "").replace("&k", "");

        int length = text.length();
        for (int i = 0; i < length; i++) {
            float ratio = (float) i / length;  // Changed ratio calculation
            TextColor color = ColorInterpolater.interpolateColor(start, end, ratio);

            TextComponent.Builder component = Component.text()
                    .content(String.valueOf(text.charAt(i)))
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
