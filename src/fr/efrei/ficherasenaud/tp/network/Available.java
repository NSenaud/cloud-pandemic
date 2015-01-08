package fr.efrei.ficherasenaud.tp.network;

import java.util.List;

import fr.efrei.paumier.common.selection.Selector;
import fr.efrei.paumier.common.networking.MessageChannel;

public class Available implements Selector<MessageChannel> {

	public Available() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public MessageChannel selectAmong(List<MessageChannel> choices) {
		return choices.get(0);
	}
}
