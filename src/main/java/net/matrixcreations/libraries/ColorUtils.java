package net.matrixcreations.libraries;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ColorUtils {

    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");
    private static final Pattern GRADIENT_PATTERN = Pattern.compile("<GRADIENT:#([A-Fa-f0-9]{6})>(.*?)</GRADIENT:#([A-Fa-f0-9]{6})>");
    private static final Pattern SOLID_PATTERN = Pattern.compile("<SOLID:#([A-Fa-f0-9]{6})>(.*?)(?:</SOLID>|$)");
    private static final Pattern LEGACY_PATTERN = Pattern.compile("&([0-9a-fk-or])");

    public static String process(String text) {
        text = processGradients(text);
        text = processSolidColors(text);
        text = processHexColors(text);
        return processLegacyCodes(text);
    }

    public static List<String> process(List<String> texts) {
        return texts.stream().map(ColorUtils::process).collect(Collectors.toList());
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

    private static String processHexColors(String text) {
        Matcher matcher = HEX_PATTERN.matcher(text);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String hexColor = matcher.group(1);
            String replacement = "§x§" + String.join("§", hexColor.split(""));
            matcher.appendReplacement(buffer, replacement);
        }

        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private static String processLegacyCodes(String text) {
        return text.replace('&', '§');
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
            TextColor color = interpolateColor(start, end, ratio);

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


    private static String applySolidColor(String text, String hexColor) {
        TextColor color = TextColor.fromHexString("#" + hexColor);

        boolean isBold = text.contains("§l") || text.contains("&l");
        boolean isItalic = text.contains("§o") || text.contains("&o");
        boolean isUnderlined = text.contains("§n") || text.contains("&n");
        boolean isStrikethrough = text.contains("§m") || text.contains("&m");
        boolean isObfuscated = text.contains("§k") || text.contains("&k");

        text = text.replace("§l", "").replace("&l", "");
        text = text.replace("§o", "").replace("&o", "");
        text = text.replace("§n", "").replace("&n", "");
        text = text.replace("§m", "").replace("&m", "");
        text = text.replace("§k", "").replace("&k", "");

        TextComponent.Builder component = Component.text()
                .content(text)
                .color(color);

        if (isBold) component.decorate(TextDecoration.BOLD);
        if (isItalic) component.decorate(TextDecoration.ITALIC);
        if (isUnderlined) component.decorate(TextDecoration.UNDERLINED);
        if (isStrikethrough) component.decorate(TextDecoration.STRIKETHROUGH);
        if (isObfuscated) component.decorate(TextDecoration.OBFUSCATED);

        return LegacyComponentSerializer.legacySection().serialize(component.build());
    }

    private static TextColor interpolateColor(TextColor start, TextColor end, float ratio) {
        int red = (int) (start.red() + (end.red() - start.red()) * ratio);
        int green = (int) (start.green() + (end.green() - start.green()) * ratio);
        int blue = (int) (start.blue() + (end.blue() - start.blue()) * ratio);

        return TextColor.color(red, green, blue);
    }
}