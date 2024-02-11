package br.com.makersweb.makersfood.domain.utils;

import java.util.UUID;

/**
 * @author aaristides
 */
public final class IdUtils {

    private IdUtils() {
    }

    public static String uuid() {
        return UUID.randomUUID().toString().toLowerCase();
    }
}
