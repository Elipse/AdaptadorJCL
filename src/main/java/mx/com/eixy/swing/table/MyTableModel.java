package mx.com.eixy.swing.table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import mx.com.eixy.util.MyList;

public class MyTableModel<T> extends AbstractTableModel implements PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyList<T> lista;
	private List<String> headers;

	/**
	 * @param <T>
	 * 
	 */

	public MyTableModel(MyList<T> lista, Class<T> clazz) {
		this.lista = lista;
		this.headers = new ArrayList<>();
		this.lista.addPropertyChangeListener(this);

		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.getAnnotation(Ignore.class) == null) {
				this.headers.add(field.getName());
			}
		}
	}

	@Override
	public String getColumnName(int column) {

		return this.headers.get(column);
	}

	@Override
	public int getRowCount() {
		return this.lista.size();
	}

	@Override
	public int getColumnCount() {
		return this.headers.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		T member = lista.get(rowIndex);
		Field[] fields = member.getClass().getDeclaredFields();

		Object object = null;

		for (Field field : fields) {
			field.setAccessible(true);
			if (this.headers.get(columnIndex).equals(field.getName())) {
				try {
					object = field.get(member);
					break;
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		return object;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		String propertyName = evt.getPropertyName();
		
		fireTableDataChanged();
		
		if (true) {
			return;
		}

		switch (propertyName) {
		case "addedRow":
			fireTableRowsInserted((int) evt.getNewValue(), (int) evt.getNewValue());
			break;
		case "removedRow":
			fireTableRowsDeleted((int) evt.getNewValue(), (int) evt.getNewValue());
			break;
		default:
			fireTableDataChanged();
			break;
		}
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Ignore {

	}

}
