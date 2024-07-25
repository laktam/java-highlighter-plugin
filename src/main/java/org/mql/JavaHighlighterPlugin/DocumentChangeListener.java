package org.mql.JavaHighlighterPlugin;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



public class DocumentChangeListener implements DocumentListener {
	private Highlighter highlighter;

	public DocumentChangeListener(Highlighter highlighter) {
		this.highlighter = highlighter;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// defer the style application until after the current event dispatch is
		// complete.
		SwingUtilities.invokeLater(() -> {
			highlighter.highlight();
		});
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// defer the style application until after the current event dispatch is
		// complete.
		SwingUtilities.invokeLater(() -> {
			highlighter.highlight();
		});
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}

}
