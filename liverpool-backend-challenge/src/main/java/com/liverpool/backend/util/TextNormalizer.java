package com.liverpool.backend.util;

import java.text.Normalizer;

/**
 * Utility class used to normalize text values
 * for search operations.
 */
public class TextNormalizer {

    /**
     * Normalizes a text value removing accents
     * and converting to lowercase.
     *
     * @param text input text
     * @return normalized text
     */
  public static String normalize(String value) {
    if (value == null) return "";

    return Normalizer.normalize(value, Normalizer.Form.NFD)
        .replaceAll("\\p{M}", "")
        .toLowerCase()
        .replaceAll("[^a-z0-9\\s]", " ")
        .replaceAll("\\s+", " ")
        .trim();
  }
}
