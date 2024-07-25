package org.mql.JavaHighlighterPlugin;

import java.util.List;

public interface Tokenizer {
	public List<Token> tokenize(String code);
	public String getTargetExtension();
}
