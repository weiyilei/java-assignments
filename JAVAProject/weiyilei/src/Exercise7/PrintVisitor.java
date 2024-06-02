import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintVisitor implements FileSystemVisitor {
   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

   public void visitFileNode (FileNode node) throws IOException {
      for (int i = 0; i < level; i++) System.out.print( "-" );
      System.out.println( "File name:" + node.getFile().getName() );
      BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(node.getFile().toPath(), BasicFileAttributeView.class);
      BasicFileAttributes basicFileAttributes = basicFileAttributeView.readAttributes();
      for (int i = 0; i < level; i++) System.out.print( " " );
      System.out.println("--creation time:" + df.format(new Date(basicFileAttributes.creationTime().toMillis())));
      for (int i = 0; i < level; i++) System.out.print( " " );
      System.out.println("--last access time:" + df.format(new Date(basicFileAttributes.lastAccessTime().toMillis())));
      for (int i = 0; i < level; i++) System.out.print( " " );
      System.out.println("--last modified time:" + df.format(new Date(basicFileAttributes.lastModifiedTime().toMillis())));
      for (int i = 0; i < level; i++) System.out.print( " " );
      System.out.println("--size:" + basicFileAttributes.size());
      FileOwnerAttributeView ownerAttributeView = Files.getFileAttributeView(node.getFile().toPath(), FileOwnerAttributeView.class);
      for (int i = 0; i < level; i++) System.out.print( " " );
      System.out.println("--owner:" + ownerAttributeView.getOwner());
   }

   public void visitDirectoryNode (DirectoryNode node) throws IOException {
      for (int i = 0; i < level; i++) System.out.print( "-" );
      System.out.println( "Directory name:" + node.getDirectory().getName() );
      BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(node.getDirectory().toPath(), BasicFileAttributeView.class);
      BasicFileAttributes basicFileAttributes = basicFileAttributeView.readAttributes();
      for (int i = 0; i < level; i++) System.out.print( " " );
      System.out.println("--creation time:" + df.format(new Date(basicFileAttributes.creationTime().toMillis())));
      for (int i = 0; i < level; i++) System.out.print( " " );
      System.out.println("--last access time:" + df.format(new Date(basicFileAttributes.lastAccessTime().toMillis())));
      for (int i = 0; i < level; i++) System.out.print( " " );
      System.out.println("--last modified time:" + df.format(new Date(basicFileAttributes.lastModifiedTime().toMillis())));
      for (int i = 0; i < level; i++) System.out.print( " " );
      System.out.println("--size:" + basicFileAttributes.size());
      FileOwnerAttributeView ownerAttributeView = Files.getFileAttributeView(node.getDirectory().toPath(), FileOwnerAttributeView.class);
      for (int i = 0; i < level; i++) System.out.print( " " );
      System.out.println("--owner:" + ownerAttributeView.getOwner());
      level++;
      for (FileSystemNode c : node.getChildren())
         c.accept( this );
      level--;
   }

   private int level = 0;
}
