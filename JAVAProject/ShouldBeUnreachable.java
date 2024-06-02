import java.net.URI;
import java.net.URL;

//CS304 Issue link: https://github.com/assertj/assertj-core/issues/2196
public class ShouldBeUnreachable {

    private static final String SHOULD_BE_UNREACHABLE = "%nExpecting actual:%n  <%s>%nbe unreachable but it's not";

    /** .
     * Verifies that the actual {@code URI} is unreachable.
     * @param actual the URI we want to test its unreachability
     * @throws AssertionError if the actual URI is reachable.
     */
    public static String shouldBeUnreachable(URI actual){
        String result = "";
        result = result.concat("%nExpecting actual:%n  <").concat(actual.toString()).concat(">%nbe unreachable but it's not");
        return result;
    }

    /** .
     * Verifies that the actual {@code URL} is unreachable.
     * <p>
     * Examples:
     * <pre><code class='java'> // These assertions succeed:
     * assertThat(new URL("http://www.baidu")).isUnreachable();
     * assertThat(new URL("http://www.hltv")).isUnreachable();
     *
     * // this assertion fails:
     * assertThat(new URL("http://www.hltv.org")).isUnreachable();
     *
     * @param actual the URL we want to test its unreachability
     * @throws AssertionError if the actual URL is reachable.
     */
    public static String shouldBeUnreachable(URL actual){
        String result = "";
        result = result.concat("%nExpecting actual:%n  <").concat(actual.toString()).concat(">%nbe unreachable but it's not");
        return result;
    }
}
