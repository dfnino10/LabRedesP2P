package network;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public interface ServerChannelRegister {

  /**
   * Create new channel and bind to specified selector
   *
   * @param selector specified selector
   * @return new created server channel
   */
  ServerSocketChannel channelFor(Selector selector) throws IOException;

}