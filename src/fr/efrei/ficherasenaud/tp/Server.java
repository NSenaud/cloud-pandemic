package fr.efrei.ficherasenaud.tp;

import java.net.InetSocketAddress;
import java.util.List;

import fr.efrei.paumier.common.networking.MessageChannel;
import fr.efrei.paumier.common.networking.MessageChannelHost;

public class Server implements MessageChannelHost {

	public Server() {
		// TODO Auto-generated constructor stub
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
