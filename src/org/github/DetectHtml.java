package org.github;

/**
 * Detect HTML markup in a string
 * This will detect tags or entities
 *
 * @author dbennett455@gmail.com - David H. Bennett
 */

import java.util.regex.Pattern;

public class DetectHtml {

    /**
     * Matches:
     * <code>
     * <td valign="top" align="center" width="598" height="46">
     * </code>
     */
    // adapted from post by Phil Haack and modified to match better
    private static final String TAG_START =
            "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)\\>";

    /**
     * Matches:
     * <code>
     * </td>
     * </code>
     */
    private static final String TAG_END =
            "\\</\\w+\\>";


    /**
     * Matches:
     * <code>
     * <img style="padding-top:112px" height="92" src="/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png" />
     * </code>
     */
    private static final String TAG_SELF_CLOSING =
            "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)/\\>";
    private static final String HTML_ENTITY =
            "&[a-zA-Z][a-zA-Z0-9]+;";
    private static final Pattern htmlPattern = Pattern.compile(
            "(" + TAG_START + ".*" + TAG_END + ")|(" + TAG_SELF_CLOSING + ")|(" + HTML_ENTITY + ")",
            Pattern.DOTALL
    );

    private DetectHtml() {

    }

    /**
     * Will return true if s contains HTML markup tags or entities.
     *
     * @param s String to test
     * @return true if string contains HTML
     */
    public static boolean isHtml(String s) {
        boolean ret = false;
        if (s != null) {
            ret = htmlPattern.matcher(s).find();
        }
        return ret;
    }

}
