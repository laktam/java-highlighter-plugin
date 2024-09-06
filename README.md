# Java Highlighter Plugin
This plugin provides syntax highlighting support for Java files in the JCodeEditor. It highlights Java keywords, identifiers, comments, strings, and other tokens, making code easier to read and understand. The highlighting is dynamic, meaning it updates in real-time as the user edits the document. 
## Features 
- Keywords, comments, strings, identifiers, and numbers are highlighted in distinct colors.
- Real-time updates ensure dynamic reanalysis and highlighting as you type.
## Installation 
1. Clone the repository to your local machine: ```bash git clone https://github.com/laktam/java-highlighter-plugin.git ```
2. Build the plugin and add the resulting `.jar` file to the `plugins` folder of the JCodeEditor installation. 
3. Restart JCodeEditor, and the Java Highlighter plugin will be automatically detected and enabled.
## Usage 
1. Open a Java file (`.java`) in JCodeEditor.
2. The syntax highlighting will automatically activate for Java files.
3. As you edit the file, the plugin will dynamically highlight your code in real-time.
## Development 
here are the core components: 
1. **`JavaHighlighter.java`**: The main class responsible for applying syntax highlighting to the document.
2. **`JavaTokenizer.java`**: The tokenizer that breaks the Java code into tokens, such as keywords, comments, strings, identifiers, and numbers.
3. **`DocumentChangeListener.java`**: A document listener that triggers the highlighting process whenever changes are made to the document.
### Token Types
The following token types are recognized by the tokenizer:
   - `KEYWORD`
   - `IDENTIFIER`
   - `COMMENT`
   - `STRING`
   - `NUMBER`
   - `OPERATOR`
