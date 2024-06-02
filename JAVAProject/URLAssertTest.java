import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import static org.junit.Assert.*;

public class URLAssertTest {

    //CS304 (manually written) Issue link: https://github.com/assertj/assertj-core/issues/2196
    @Test
    public void testShouldBeReachableWhenUnreachable() throws MalformedURLException {
        URL url = new URL("http://www.baidu");
        assertEquals("%nExpecting actual:%n  <http://www.baidu>%nbe reachable but it's not", URLAssert.assertIsReachable(url));
    }

    //CS304 (manually written) Issue link: https://github.com/assertj/assertj-core/issues/2196
    @Test
    public void testShouldBeReachableWhenReachable() throws MalformedURLException {
        URL url = new URL("http://www.baidu.com");
        assertNull(URLAssert.assertIsReachable(url));
    }

    //CS304 (manually written) Issue link: https://github.com/assertj/assertj-core/issues/2196
    @Test
    public void testShouldBeUnreachableWhenReachable() throws MalformedURLException{
        URL url = new URL("http://www.hltv.org");
        assertEquals("%nExpecting actual:%n  <http://www.hltv.org>%nbe unreachable but it's not", URLAssert.assertIsUnreachable(url));
    }

    //CS304 (manually written) Issue link: https://github.com/assertj/assertj-core/issues/2196
    @Test
    public void testShouldBeUnreachableWhenUnreachable() throws MalformedURLException{
        URL url = new URL("http://www.hltv");
        assertNull(URLAssert.assertIsUnreachable(url));
    }
}
