import java.io.IOException;

/**
   The common interface for file and directory nodes.
*/
public interface FileSystemNode {
   void accept (FileSystemVisitor v) throws IOException;
}
