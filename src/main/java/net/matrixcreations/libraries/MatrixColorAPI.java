package net.matrixcreations.libraries;

import net.matrixcreations.libraries.utils.*;

import java.util.List;
import java.util.stream.Collectors;

public final class MatrixColorAPI {

    public static String process(String text) {
        text = GradientUtils.processGradients(text);
        text = SolidColorUtils.processSolidColors(text);
        text = HexColorUtils.processHexColors(text);
        text = WithoutHexUtils.process(text);
        return LegacyCodeUtils.processLegacyCodes(text) ;
    }

    public static List<String> process(List<String> texts) {
        return texts.stream().map(MatrixColorAPI::process).collect(Collectors.toList());
    }
}
