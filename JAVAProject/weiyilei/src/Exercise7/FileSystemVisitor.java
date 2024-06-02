import java.io.IOException;

/**
   The visitor interface type for visiting file system nodes.
*/
public interface FileSystemVisitor {
   /**
      Visits a file node.
      @param node the file node
   */
   void visitFileNode (FileNode node) throws IOException;

   /**
      Visits a directory node.
      @param node the directory node
   */
   void visitDirectoryNode (DirectoryNode node) throws IOException;
}
