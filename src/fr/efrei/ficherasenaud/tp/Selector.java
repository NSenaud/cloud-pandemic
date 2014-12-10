package fr.efrei.ficherasenaud.tp;

import java.util.List;

public interface Selector<TItem> {
	TItem selectAmong(List<TItem> choices);
}
