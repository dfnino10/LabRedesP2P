package network;

public interface AcceptAttachment {

  /**
   * @return channel listener factory for create listeners for new connections
   */
  ChannelListenerFactory getChannelListenerFactory();

}