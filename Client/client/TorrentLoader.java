package client;

import java.io.IOException;

public interface TorrentLoader {

  /**
   * Creates or finds shared torrent instance for specified announceable torrent and return it
   *
   * @param loadedTorrent specified torrent
   * @return shared torrent instance associated with current announceable torrent
   * @throws IOException              if any io error occurs
   */
  SharedTorrent loadTorrent( LoadedTorrent loadedTorrent) throws IOException;

}