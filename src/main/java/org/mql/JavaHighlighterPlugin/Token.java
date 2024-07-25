package org.mql.JavaHighlighterPlugin;

public class Token {
    TokenType type;
    String value;
    int start;
    int size;
    
    Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }
    
    
    
    public Token(TokenType type, String value, int start, int size) {
		super();
		this.type = type;
		this.value = value;
		this.start = start;
		this.size = size;
	}



	public TokenType getType() {
		return type;
	}
    
    public String getValue() {
		return value;
	}
    
    public int getSize() {
		return size;
	}
    
    public int getStart() {
		return start;
	}
    
    @Override
    public String toString() {
    	return "" + value + " : " + type;
    }
}