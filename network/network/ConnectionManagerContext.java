package network;

import java.util.concurrent.ExecutorService;

public interface ConnectionManagerContext extends ChannelListenerFactory {

  ExecutorService getExecutor();

}