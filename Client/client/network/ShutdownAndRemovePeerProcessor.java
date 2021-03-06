package client.network;

import client.Context;
import client.PeersStorage;
import client.peer.SharingPeer;
import common.PeerUID;
import common.TorrentLoggerFactory;

import org.slf4j.Logger;

import java.io.IOException;
import java.nio.channels.ByteChannel;

public class ShutdownAndRemovePeerProcessor implements DataProcessor {

  private static final Logger logger = TorrentLoggerFactory.getLogger(ShutdownAndRemovePeerProcessor.class);

  private final PeerUID myPeerUID;
  private final Context myContext;

  public ShutdownAndRemovePeerProcessor(PeerUID peerId, Context context) {
    myPeerUID = peerId;
    myContext = context;
  }

  @Override
  public DataProcessor processAndGetNext(ByteChannel socketChannel) throws IOException {
    DataProcessorUtil.closeChannelIfOpen(logger, socketChannel);
    logger.trace("try remove and unbind peer. Peer UID - {}", myPeerUID);
    removePeer();
    return null;
  }

  private void removePeer() {
    PeersStorage peersStorage = myContext.getPeersStorage();
    SharingPeer removedPeer = peersStorage.removeSharingPeer(myPeerUID);
    if (removedPeer == null) {
      logger.info("try to shutdown peer with id {}, but it is not found in storage", myPeerUID);
      return;
    }
    removedPeer.unbind(true);
  }

  @Override
  public DataProcessor handleError(ByteChannel socketChannel, Throwable e) throws IOException {
    return processAndGetNext(socketChannel);
  }
}