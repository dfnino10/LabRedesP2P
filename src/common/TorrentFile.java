package common;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TorrentFile {

  public final List<String> relativePath;
  public final long size;

  public final Optional<String> md5Hash;

  public TorrentFile( List<String> relativePath, long size, String md5Hash) {
    this.relativePath = new ArrayList<String>(relativePath);
    this.size = size;
    this.md5Hash = Optional.of(md5Hash);
  }

  public String getRelativePathAsString() {
    String delimiter = File.separator;
    final Iterator<String> iterator = relativePath.iterator();
    StringBuilder sb = new StringBuilder();
    if (iterator.hasNext()) {
      sb.append(iterator.next());
      while (iterator.hasNext()) {
        sb.append(delimiter).append(iterator.next());
      }
    }
    return sb.toString();
  }

}