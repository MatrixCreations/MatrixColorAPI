package net.matrixcreations.libraries.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SolidColorUtils {

    private static final Pattern SOLID_PATTERN = Pattern.compile("<SOLID:#([A-Fa-f0-9]{6})>(.*?)(?:</SOLID>|$)");

    public static String processSolidColors(String text) {
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
}
