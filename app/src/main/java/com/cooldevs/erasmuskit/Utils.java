package com.cooldevs.erasmuskit;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * Utility class
 */
final class Utils {

    static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    static String toPossessive(String noun) {
        String possessive = (noun.charAt(noun.length() - 1) == 's') ? "'" : "'s";
        return noun + possessive;
    }
}
