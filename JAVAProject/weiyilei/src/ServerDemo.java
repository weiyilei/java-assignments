import org.junit.internal.runners.statements.RunAfters;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ServerDemo {
    final static String INVALID = "Invalid command";

    final static List<Faculty> facultyList = new ArrayList<>();

    private static class Faculty {
        String name;
        String p;
        String dep;

        public String getName() {
            return name;
        }

        public String getP() {
            return p;
        }

        public String getDep() {
            return dep;
        }

        Faculty(String name, String p, String dep) {
            this.name = name;
            this.p = p;
            this.dep = dep;
        }

        @Override
        public String toString() {
            return String.format("%s, %s, %s", name, p, dep);
        }
    }

    public static String handleNameCommand(String s) {

        String content = facultyList.stream()
                .filter(e -> e.getName().equals(s))
                .map((e)->e.toString())
                .collect(Collectors.joining("\n"));
        return content;
    }

    public static String handleFirstLetterCommand(String s) {
        String content = facultyList.stream()
                .filter(e -> e.getName().toLowerCase().charAt(0) == s.toLowerCase().charAt(0))
                .map((e)->e.toString())
                .collect(Collectors.joining("\n"));
        return content;
    }

    public static String handleDepCommand(String s) {
        String content = facultyList.stream()
                .filter(e -> e.getDep().toLowerCase().contains(s.toLowerCase()))
                .map((e)->e.toString())
                .collect(Collectors.joining("\n"));
        return content;
    }

    private static class MyThread extends Thread{
        int n;
        Socket socket;
        MyThread(int n, Socket socket){
            this.n = n;
            this.socket = socket;
        }
        public void run(){
            String inputLine, outputLine;
            // Wait for input
//            while(true){
                try {
                    PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                    if ((inputLine = in.readLine()) != null) {
                        inputLine = inputLine.replace("\n", "");
                        inputLine = inputLine.trim();
                        System.out.println(this.n +"Received: " + inputLine);
                        String result = " ";

                        int idx = inputLine.indexOf(' ');
                        if (idx == -1) {
                            result = "Invalid command";
                        }

                        String command = inputLine.substring(0, idx);
                        String content = inputLine.substring(idx + 1);
                        switch (command.toUpperCase()) {
                            case "NAME":
                                result = handleNameCommand(content);
                                break;
                            case "FIRSTLETTER":
                                result = handleFirstLetterCommand(content);
                                break;
                            case "DEP":
                                result = handleDepCommand(content);
                                break;
                            default:
                                result = "Invalid command";
                        }
                        out.println(result);
                        System.out.println("Command processed" + this.n);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            }
        }
    }

    public static void main(String[] args) throws IOException {
        try(FileInputStream fis = new FileInputStream("FacultyList.csv");
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr)){
            String line;
            while((line = br.readLine()) != null){
                String[] strs = line.replace("\n", "").split(",");
                facultyList.add(new Faculty(strs[0].trim(), strs[1].trim(), strs[2].trim()));
            }
        }

//        HashMap<String, Function<ArrayList<Integer>, ArrayList<Integer>>> serviceProcess = new HashMap<>();
//        serviceProcess.put("GetEven", (l) -> {
//            l.removeIf((e) -> e % 2 != 0);
//            return l;
//        });
//        serviceProcess.put("GetOdd", (l) -> {
//            l.removeIf((e) -> e % 2 == 0);
//            return l;
//        });
//        Predicate<Integer> isPrime = (e) -> {
//            if (e == 1 || e == 0)
//                return false;
//            for (int i = 2; i <= Math.sqrt(e); i++) {
//                if (e % i == 0)
//                    return false;
//            }
//            return true;
//        };
//        serviceProcess.put("GetPrime", (l) -> {
//            l.removeIf(isPrime.negate());
//            return l;
//        });
//        serviceProcess.put("GetBiggerThanFive", (l) -> {
//            l.removeIf((e) -> e <= 5);
//            return l;
//        });
//        serviceProcess.put("Reset", (l) -> {
//            for (int i = 0; i < 10000; i++) {
//                l.add(i);
//            }
//            return l;
//        });
        int portNumber = 8888;
        PrintWriter out = null;
        BufferedReader in = null;
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        Set<Socket> set = new HashSet<>();
        Thread[] array = new Thread[8];
        int n = 0;


        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Server is OK, is waiting for connect...");
            while (true) {
                clientSocket = serverSocket.accept();
                if(!set.contains(clientSocket)){
                    set.add(clientSocket);
                    array[n] = new MyThread(n, clientSocket);
                    array[n].start();
                    System.out.println("Hava a connect" + n);
                    n++;
                }
            }

        } catch (IOException e) {
            System.out.println(
                    "Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (clientSocket != null) {
                clientSocket.close();
            }
            if (serverSocket != null) {
                serverSocket.close();
            }

        }

    }


}
