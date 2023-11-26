// NEW
package models;

import java.awt.Color;

import java.util.List;
import java.util.ArrayList;

public abstract class ColorGenerator {
    public static List<Color> generateDistinctColors(int nbColors) {
        List<Color> distinctColors = new ArrayList<>();

        // Calculate the step size for equally dividing the color space
        int stepSize = 256 / (int) Math.ceil(Math.pow(nbColors, 1.0 / 3.0));

        for (int r = 0; r < 256; r += stepSize) {
            for (int g = 0; g < 256; g += stepSize) {
                for (int b = 0; b < 256; b += stepSize) {
                    if (distinctColors.size() < nbColors) {
                        Color color = new Color(r, g, b);
                        distinctColors.add(color);
                    }
                }
            }
        }

        return distinctColors;
    }
}
// END NEW