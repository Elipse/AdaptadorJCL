package mx.com.eixy.utilities.swing;

import java.awt.EventQueue;
import java.util.stream.Stream;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;

import mx.com.eixy.swing.table.MyTableModel;
import mx.com.eixy.util.MyList;

public class MyJTable<T> {

	private JTable jTable;

	private MyList<T> myList;
	private MyTableModel<T> myTableModel;

	public MyJTable(JTable jTable, Class<T> clas) {

		this.jTable = jTable;

		myList = new MyList<>();
		myTableModel = new MyTableModel<>(myList, clas);
		jTable.setModel(myTableModel);
		jTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		SwingKeys.add(jTable, JComponent.WHEN_FOCUSED, "control DELETE", this::deleteRows);

		jTable.getModel().addTableModelListener(event -> fireStateChanged(event));
		jTable.getSelectionModel().addListSelectionListener(event -> fireStateChanged(event));

	}

	private void fireStateChanged(TableModelEvent event) {
		event.getFirstRow();

	}

	private void fireStateChanged(ListSelectionEvent event) {

	}

	private Object modelChanged() {
		// TODO Auto-generated method stub
		return null;
	}

	private final EventListenerList listenerList = new EventListenerList();

	public void addChangeListener(ChangeListener l) {
		listenerList.add(ChangeListener.class, l);
	}

	public void removeChangeListener(ChangeListener l) {
		listenerList.remove(ChangeListener.class, l);
	}

	// Notify all listeners that have registered interest for
	// notification on this event type. The event instance
	// is lazily created using the parameters passed into
	// the fire method.
	protected void fireStateChanged() {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		int indice = jTable.getSelectedRow();
		if (indice < 0) {
			return;
		}
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == ChangeListener.class) {
				// Lazily create the event:

				T bean = myList.get(indice);
				ChangeEvent changeEvent = new ChangeEvent(bean);
				((ChangeListener) listeners[i + 1]).stateChanged(changeEvent);

			}
		}
	}

	public void add(T t) {

		myList.add(t);

		int lastRow = myList.size() - 1;

		EventQueue.invokeLater(() -> jTable.setRowSelectionInterval(lastRow, lastRow));
	}

	private void deleteRows() {

		int[] indices = jTable.getSelectedRows();

		for (int i = indices.length - 1; i >= 0; i--) {
			myList.remove(indices[i]);
		}

		int firstRow = indices[0];
		if (firstRow > myList.size() - 1) {
			firstRow = myList.size() - 1;
		}

		jTable.setRowSelectionInterval(firstRow, firstRow);

		// EventQueue.invokeLater(() -> jTable.setRowSelectionInterval(i,i));
	}

	public JTable getjTable() {
		return jTable;
	}

	public void setjTable(JTable jTable) {
		this.jTable = jTable;
	}

	public MyList<T> getMyList() {
		return myList;
	}

	public void setMyList(MyList<T> myList) {
		this.myList = myList;
	}

	public MyTableModel<T> getMyTableModel() {
		return myTableModel;
	}

	public void setMyTableModel(MyTableModel<T> myTableModel) {
		this.myTableModel = myTableModel;
	}

	public Stream<T> stream() {
		return myList.stream();
	}

	public void grabFocus() {
		jTable.grabFocus();
		jTable.setRowSelectionInterval(0, 0);

	}

	public T getSelectedRow() {
		int i = jTable.getSelectedRow();
		if (i >= 0) {
			return myList.get(i);
		}
		return null;
	}

}
