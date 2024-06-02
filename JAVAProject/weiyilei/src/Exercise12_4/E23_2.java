package Exercise12_4;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 This program demonstrates how to use a socket to communicate
 with a web server. Supply the name of the host and the
 resource on the command-line, for example
 java WebGet horstmann.com index.html
 */
public class E23_2 {
    public static void main (String[] args) throws IOException {
        // Get command-line arguments
        String host = "horstmann.com" ;   // default host
        String resource = "/";                 // default resource

        if (args.length >= 1) host = args[0];
        if (args.length >= 2) resource = args[1];

        System.out.printf( "Getting %s from %s\n", resource, host );

        // Open socket
        final int HTTP_PORT = 80;
        try (Socket s = new Socket( host, HTTP_PORT)) {
            // Get streams
            InputStream instream = s.getInputStream();
            OutputStream outstream = s.getOutputStream();

            // Turn streams into scanners and writers
            Scanner in = new Scanner( instream);
            PrintWriter out = new PrintWriter( outstream);

            // Send command
            String command = "GET " + resource + " HTTP/1.1\n" +
                    "Host: " + host + "\n\n";
            out.print( command );
            out.flush();

            // Read server response
            while (in.hasNextLine()) {
                String input = in.nextLine();
                System.out.println( input);
            }
        } // The try-with-resources statement closes the socket
    }
}
