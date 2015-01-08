package fr.efrei.ficherasenaud.tp.network;

import fr.efrei.ficherasenaud.tp.Parameters;
import fr.efrei.ficherasenaud.tp.events.ImmigrationEvent;
import fr.efrei.paumier.common.networking.BaseRemoteCityBorder;
import fr.efrei.paumier.common.networking.MessageChannel;
import fr.efrei.paumier.common.networking.MessageChannelHost;
import fr.efrei.paumier.common.selection.Selector;
import fr.efrei.paumier.common.time.EventQueue;

public class Remote extends BaseRemoteCityBorder {
	public Remote(MessageChannelHost host, EventQueue queue, Selector<MessageChannel> selector) {
		super(host, queue, selector);
	}

	@Override
	protected void receiveImmigrantFrom(MessageChannel sender, MigrationMessage immigrantMessage) {
		ImmigrationEvent event = new ImmigrationEvent(immigrantMessage.isInfected());
		Parameters.engine.register(event);
	}
}
