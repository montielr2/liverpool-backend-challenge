package com.liverpool.backend.util;

import java.text.Normalizer;

public class TextNormalizer {

    public static String normalize(String value) {
        if (value == null)
            return "";

        return Normalizer.normalize(value, Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase().replaceAll("[^a-z0-9\\s]", " ").replaceAll("\\s+", " ").trim();
    }
}
