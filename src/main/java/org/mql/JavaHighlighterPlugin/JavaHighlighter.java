package org.mql.JavaHighlighterPlugin;

import java.awt.Color;
import java.util.List;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import org.mql.jcodeeditor.highlighting.Highlighter;
import org.mql.jcodeeditor.highlighting.Token;
import org.mql.jcodeeditor.highlighting.TokenType;
import org.mql.jcodeeditor.highlighting.Tokenizer;

public class JavaHighlighter implements Highlighter {
	private Tokenizer tokenizer;
	private StyledDocument doc;
	private Style keywordStyle;
	private Style numberStyle;
	private Style identifierStyle;
	private Style defaultStyle;
	private Style commentStyle;
	private Style stringStyle;

	private Color commentColor = new Color(63, 127, 95);
	private Color identifierColor = new Color(143, 86, 4);
	private Color stringColor = new Color(42, 0, 255);
	private Color keywordColor = new Color(127, 0, 85);
	private Color numberColor = new Color(127, 0, 85);

	public JavaHighlighter() {
		

//		highlight(); 

	}

	@Override
	public void highlight() {
		String code = "";
		try {
			code = doc.getText(0, doc.getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		doc.setCharacterAttributes(0, doc.getLength(), defaultStyle, true);
		List<Token> tokens = tokenizer.tokenize(code);
		for (Token token : tokens) {
			if (token.getType().equals(TokenType.KEYWORD)) {
				doc.setCharacterAttributes(token.getStart(), token.getSize(), keywordStyle, true);
			}
			if (token.getType().equals(TokenType.IDENTIFIER)) {
				doc.setCharacterAttributes(token.getStart(), token.getSize(), identifierStyle, true);
			}
			if (token.getType().equals(TokenType.COMMENT)) {
				doc.setCharacterAttributes(token.getStart(), token.getSize(), commentStyle, true);
			}
			if (token.getType().equals(TokenType.STRING)) {
				doc.setCharacterAttributes(token.getStart(), token.getSize(), stringStyle, true);
			}
		}
	}

	@Override
	public void setTokenizer(Tokenizer tokenizer) {
		this.tokenizer = tokenizer;
	}

	@Override
	public void setDocument(StyledDocument d) {
		this.doc = d;
		defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

//		keywordStyle = StyleContext.getDefaultStyleContext().addStyle(null, null);
		keywordStyle = doc.addStyle("KeywordStyle", defaultStyle);
		StyleConstants.setForeground(keywordStyle, keywordColor);
		StyleConstants.setBold(keywordStyle, true);

		
//		numberStyle = StyleContext.getDefaultStyleContext().addStyle(null, null);
		numberStyle = doc.addStyle("NumbersStyle", defaultStyle);
		StyleConstants.setForeground(numberStyle, numberColor);

//		identifierStyle  = StyleContext.getDefaultStyleContext().addStyle(null, null);
		identifierStyle = doc.addStyle("Iden	tifier", defaultStyle);
		StyleConstants.setForeground(identifierStyle, identifierColor);

//		commentStyle  = StyleContext.getDefaultStyleContext().addStyle(null, null);
		commentStyle = doc.addStyle("CommentsStyle", defaultStyle);
		StyleConstants.setForeground(commentStyle, commentColor);

//		stringStyle  = StyleContext.getDefaultStyleContext().addStyle(null, null);
		stringStyle = doc.addStyle("stringStyle", defaultStyle);
		StyleConstants.setForeground(stringStyle, stringColor);
		this.doc.addDocumentListener(new DocumentChangeListener(this));
	}
}
