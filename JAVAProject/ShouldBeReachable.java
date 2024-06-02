import java.net.URI;
import java.net.URL;

//CS304 Issue link: https://github.com/assertj/assertj-core/issues/2196
public class ShouldBeReachable {

    private static final String SHOULD_BE_REACHABLE = "%nExpecting actual:%n  <%s>%nbe reachable but it's not";

    /** .
     * Verifies that the actual {@code URI} is reachable.
     * @param actual the URI we want to test its reachability
     * @throws AssertionError if the actual URI is unreachable.
     */
    public static String shouldBeReachable(URI actual){
        String result = "";
        result = result.concat("%nExpecting actual:%n  <").concat(actual.toString()).concat(">%nbe reachable but it's not");
        return result;
    }

    /** .
     * Verifies that the actual {@code URL} is reachable.
     * <p>
     * Examples:
     * <pre><code class='java'> // These assertions succeed:
     * assertThat(new URL("http://www.baidu.com")).isReachable();
     * assertThat(new URL("http://www.bing.com")).isReachable();
     *
     * // this assertion fails:
     * assertThat(new URL("http://www.hltv")).isReachable();
     *
     * @param actual the URL we want to test its reachability
     * @throws AssertionError if the actual URL is unreachable.
     */
    public static String shouldBeReachable(URL actual){
        String result = "";
        result = result.concat("%nExpecting actual:%n  <").concat(actual.toString()).concat(">%nbe reachable but it's not");
        return result;
    }
}
