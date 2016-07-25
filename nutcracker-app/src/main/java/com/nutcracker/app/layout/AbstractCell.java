package com.nutcracker.app.layout;
import javafx.scene.control.ListCell;

public abstract class AbstractCell<T> extends ListCell<T> {
	
	@Override
	public void updateItem(T item, boolean empty) {
		super.updateItem(item, empty);
		
		if (null == item) {
			clearContent();
		} else {
			addContent(item);
		}
	}
	
	protected abstract void addContent(T item);
	
	private void clearContent() {
		setText(null);
		setGraphic(null);
	}
	
}
