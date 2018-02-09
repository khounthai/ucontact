package com.ril.entity;

import java.util.Comparator;

public class DonneeComparator implements Comparator<Donnee>{

	@Override
	public int compare(Donnee o1, Donnee o2) {
		return (int) (o1.getOrdre()-o2.getOrdre());
	}

}
