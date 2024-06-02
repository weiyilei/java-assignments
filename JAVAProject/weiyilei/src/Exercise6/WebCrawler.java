import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class WebCrawler {
    public static void main(String[] args){
        // timeout connection after 500 miliseconds
        System.setProperty("sun.net.client.defaultConnectTimeout", "500");
        System.setProperty("sun.net.client.defaultReadTimeout",    "1000");

        // initial web page
        String s = args[0];

        // list of web pages to be examined
        Queue<String> queue = new Queue<String>();
        queue.enqueue(s);

        // existence symbol table of examined web pages
        SET<String> set = new SET<String>();
        set.add(s);

        // breadth first search crawl of web
        while (!queue.isEmpty()) {
            String v = queue.dequeue();
            StdOut.println(v);

            In in = new In(v);

            // only needed in case website does not respond
            if (!in.exists()) continue;

            String input = in.readAll();

            /*************************************************************
             *  Find links of the form: http://xxx.yyy.zzz
             *  \\w+ for one or more alpha-numeric characters
             *  \\. for dot
             *  could take first two statements out of loop
             *************************************************************/
            String  regexp  = "http://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(input);

            // find and print all matches
            while (matcher.find()) {
                String w = matcher.group();
                if (!set.contains(w)) {
                    queue.enqueue(w);
                    set.add(w);
                }
            }
        }
    }
}
