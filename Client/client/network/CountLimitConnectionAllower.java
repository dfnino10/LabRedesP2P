package client.network;

import client.PeersStorage;
import network.NewConnectionAllower;

import java.util.concurrent.atomic.AtomicInteger;

import static common.Constants.DEFAULT_MAX_CONNECTION_COUNT;

/**
 * this implementation allows fixed count of open connection simultaneously
 */
public class CountLimitConnectionAllower implements NewConnectionAllower {

  private final PeersStorage myPeersStorage;

  private final AtomicInteger myMaxConnectionCount = new AtomicInteger();

  public CountLimitConnectionAllower(PeersStorage peersStorage) {
    this.myPeersStorage = peersStorage;
    myMaxConnectionCount.set(DEFAULT_MAX_CONNECTION_COUNT);

  }

  public void setMyMaxConnectionCount(int newMaxCount) {
    myMaxConnectionCount.set(newMaxCount);
  }

  @Override
  public boolean isNewConnectionAllowed() {
    return myPeersStorage.getSharingPeers().size() < myMaxConnectionCount.get();
  }
}