package client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientApp 
{
	// Client object.
	SimpleClient client = new SimpleClient();{

		// This is the interface the client will listen on (you might need something
		// else than localhost here because other peers cannot connect to localhost).
		try {
			InetAddress[] address = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());


			//Start download. Thread is blocked here
			try {
				for(InetAddress add : address) {
					client.downloadTorrent("/path/to/filed.torrent",  "/path/to/output/directory",  add);
				}
				//download finished
			} catch (Exception e) {
				//download failed, see exception for details
				e.printStackTrace();
			}
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}

		//If you don't want to seed the torrent you can stop client
		client.stop();
	}

}
