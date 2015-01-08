package fr.efrei.ficherasenaud.tp.network;

import java.io.IOException;

import fr.efrei.ficherasenaud.tp.Parameters;
import fr.efrei.paumier.common.networking.MultipointMessageNode;

public class Server {
	MultipointMessageNode multipointServer;
	
	private Available available;
	private Remote remote;

	public Server(String IP, int port) {
		try {
			this.multipointServer = new MultipointMessageNode(port);
			this.multipointServer.addRemoteNode(IP, port);
			
			this.available = new Available();
			this.remote = new Remote(this.multipointServer, Parameters.engine, available);
			Parameters.remote = this.remote;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
