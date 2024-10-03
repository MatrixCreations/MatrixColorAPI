package net.matrixcreations.libraries.interpolaters;

import net.kyori.adventure.text.format.TextColor;

public class ColorInterpolater {

    public static TextColor interpolateColor(TextColor start, TextColor end, float ratio) {
        int red = (int) (start.red() + (end.red() - start.red()) * ratio);
        int green = (int) (start.green() + (end.green() - start.green()) * ratio);
        int blue = (int) (start.blue() + (end.blue() - start.blue()) * ratio);

        return TextColor.color(red, green, blue);
    }
}
