package fr.efrei.paumier.common.selection;

import java.util.List;

public interface Selector<TItem> {
	TItem selectAmong(List<TItem> choices);
}
