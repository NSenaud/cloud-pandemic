package fr.efrei.ficherasenaud.tp;

import fr.efrei.paumier.common.networking.BaseRemoteCityBorder;
import fr.efrei.paumier.common.networking.MessageChannel;
import fr.efrei.paumier.common.networking.MessageChannelHost;
import fr.efrei.paumier.common.selection.Selector;
import fr.efrei.paumier.common.time.EventQueue;

public class Remote extends BaseRemoteCityBorder {

	public Remote(MessageChannelHost host, EventQueue queue,
			Selector<MessageChannel> selector) {
		super(host, queue, selector);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void receiveImmigrantFrom(MessageChannel sender,
			MigrationMessage immigrantMessage) {
		// TODO Auto-generated method stub

	}
}
