import java.net.HttpURLConnection;
import java.net.URL;

//CS304 Issue link: https://github.com/assertj/assertj-core/issues/2196
public class URLAssert {

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
    public static String assertIsReachable(URL actual) {
        //if (!Objects.equals(actual.getPath(), path)) throw failures.failure(info, shouldHavePath(actual, path));
        try{
            HttpURLConnection con = (HttpURLConnection) actual.openConnection();
            int state = con.getResponseCode();
            if (state != 200 && state != 403){
                return ShouldBeReachable.shouldBeReachable(actual);
            }
        }catch (Exception ex){
            return ShouldBeReachable.shouldBeReachable(actual);
        }
        return null;
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
     * @param info corresponding information about assertion
     * @param actual the URL we want to test its unreachability
     * @throws AssertionError if the actual URL is reachable.
     */
    public static String assertIsUnreachable(URL actual){
        try{
            HttpURLConnection con = (HttpURLConnection) actual.openConnection();
            int state = con.getResponseCode();
            if (state == 200 || state == 403){
                return ShouldBeUnreachable.shouldBeUnreachable(actual);
            }
        }catch (Exception ex){
            return null;
        }
        return null;
    }

}
