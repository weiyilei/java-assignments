import java.io.*;

public class VisitorTester {
   public static void main (String[] args) throws IOException {
      DirectoryNode node = new DirectoryNode( new File( "C:\\Users\\86177\\Desktop\\JAVA2\\LEC10\\" ) );
      node.accept( new PrintVisitor() );
   }   
}
