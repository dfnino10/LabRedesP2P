package client;

import client.strategy.RequestStrategyImplAnyInteresting;
import common.TorrentMetadata;

import java.io.IOException;

public class TorrentLoaderImpl implements TorrentLoader {

  private final TorrentsStorage myTorrentsStorage;

  public TorrentLoaderImpl(TorrentsStorage torrentsStorage) {
    myTorrentsStorage = torrentsStorage;
  }

  @Override
  public SharedTorrent loadTorrent(LoadedTorrent loadedTorrent) throws IOException {

    final String hexInfoHash = loadedTorrent.getTorrentHash().getHexInfoHash();
    SharedTorrent old = myTorrentsStorage.getTorrent(hexInfoHash);
    if (old != null) {
      return old;
    }

    TorrentMetadata torrentMetadata;
    try {
      torrentMetadata = loadedTorrent.getMetadata();
    } catch (IllegalStateException e) {
      myTorrentsStorage.remove(hexInfoHash);
      throw e;
    }

    final SharedTorrent sharedTorrent = new SharedTorrent(torrentMetadata, loadedTorrent.getPieceStorage(),
            new RequestStrategyImplAnyInteresting(),
            loadedTorrent.getTorrentStatistic(), loadedTorrent.getEventDispatcher());

    old = myTorrentsStorage.putIfAbsentActiveTorrent(hexInfoHash, sharedTorrent);
    if (old != null) {
      return old;
    }
    return sharedTorrent;
  }
}