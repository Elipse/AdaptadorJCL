package mx.com.eixy.util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class MyList<E> extends ArrayList<E> {

	private static final long serialVersionUID = 1L;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}

	@Override
	public boolean add(E row) {
		boolean b = super.add(row);

		this.pcs.firePropertyChange("addedRow", -1, this.size() - 1);
		return b;
	}
	
	@Override
	public E remove(int index) {
		
		E e = super.remove(index);
		
		this.pcs.firePropertyChange("removedRow", -1, index);
		return e;
	}

}
