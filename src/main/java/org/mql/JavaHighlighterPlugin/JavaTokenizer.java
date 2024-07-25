package org.mql.JavaHighlighterPlugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaTokenizer implements Tokenizer{
	private String keywordsRegEx;
	private String commentsRegEx;
	private String identifierRegEx;
	private String stringRegEx;
	private Set<String> identifiers;

	private Pattern tokenPattern;

	public JavaTokenizer() {
		keywordsRegEx = "\\b(abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|extends|final|finally|float|for|goto|if|implements|import|instanceof|int|interface|long|native|new|null|package|private|protected|public|return|short|static|strictfp|super|switch|synchronized|this|throw|throws|transient|try|void|volatile|while)\\b";
		commentsRegEx = "//.*";
		identifierRegEx = "\\b[a-zA-Z_]\\w*\\b";
		stringRegEx = "\"[^\"]*\"";
		
		tokenPattern = Pattern.compile(keywordsRegEx + "|" +
				identifierRegEx +"(?=\\s*=)" + "|" +  // to find identifiers before "="
				identifierRegEx + "|" + // should detect here occurrences of identifiers
				stringRegEx + "|" +
				"\\b\\d+\\b|" + // numbers
				commentsRegEx + "|" + // comments
				"[+\\-*/=<>]|" + // operators
				"\\s+" // whitespace
		);
	}

	

	public JavaTokenizer(String keywordsRegEx, String identifierRegEx) {
		super();
		this.keywordsRegEx = keywordsRegEx;
		this.identifierRegEx = identifierRegEx;
	}





	public JavaTokenizer(String keywordsRegEx, String commentsRegEx, String identifierRegEx, String stringRegEx) {
		super();
		this.keywordsRegEx = keywordsRegEx;
		this.commentsRegEx = commentsRegEx;
		this.identifierRegEx = identifierRegEx;
		this.stringRegEx = stringRegEx;
	}



	public List<Token> tokenize(String code) {
		identifiers = new HashSet<String>();
		getIdentifiers(code);
		List<Token> tokens = new ArrayList<>();
		Matcher matcher = tokenPattern.matcher(code);

		while (matcher.find()) {
			String value = matcher.group();
			TokenType type = determineTokenType(value);
			tokens.add(new Token(type, value, matcher.start(), value.length()));
		}

		return tokens;
	}

	private void getIdentifiers(String code) {
		Pattern p = Pattern.compile(identifierRegEx +"(?=\\s*=)");
		Matcher matcher = p.matcher(code);

		while (matcher.find()) {
			identifiers.add(matcher.group());
		}
	}

	private TokenType determineTokenType(String value) {
		// order is important !!!
		if (value.startsWith("//"))
			return TokenType.COMMENT;
		if (value.matches("\".*\""))
			return TokenType.STRING;
		if (isKeyword(value))
			return TokenType.KEYWORD;
		if (isIdentifier(value))
			return TokenType.IDENTIFIER;
		if (value.matches("\\b\\d+\\b"))
			return TokenType.NUMBER;
		if (value.matches("[+\\-*/=<>]"))
			return TokenType.OPERATOR;
		if (value.trim().isEmpty())
			return TokenType.WHITESPACE;

		return TokenType.OTHER;
	}

	private boolean isKeyword(String value) {
		return value.matches(keywordsRegEx);
	}

	private boolean isIdentifier(String value) {
		if (identifiers.contains(value))
			return true;
		return false;
	}



	@Override
	public String getTargetExtension() {
		return ".java";
	}
}