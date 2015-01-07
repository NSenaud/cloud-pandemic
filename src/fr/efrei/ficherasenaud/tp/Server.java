package fr.efrei.ficherasenaud.tp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import javax.swing.plaf.multi.MultiTabbedPaneUI;

import fr.efrei.paumier.common.networking.MessageChannel;
import fr.efrei.paumier.common.networking.MessageChannelHost;
import fr.efrei.paumier.common.networking.MultipointMessageNode;

public class Server implements MessageChannelHost {
	MultipointMessageNode multipointServer;

	public Server(int port) {
		try {
			this.multipointServer = new MultipointMessageNode(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MessageChannel> getChannels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageChannel addChannel(String host, int port) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageChannel addChannel(InetSocketAddress address) {
		// TODO Auto-generated method stub
		return null;
	}
}
