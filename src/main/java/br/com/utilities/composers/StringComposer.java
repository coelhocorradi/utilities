package br.com.utilities.composers;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author gustavo
 *
 */
public class StringComposer {

	private int countKeys;

	private int countBrackets;

	private int countParentheses;

	private int countSingleQuotes;

	private int countDoubleQuotes;

	private int countTabs;

	private List<Character> stack;

	private StringBuilder sb = null;

	private void removeFromStack(Character c) {
		if (stack.get(stack.size()).equals(c)) {
			stack.remove(stack.size());
		}
	}

	/**
	 * 
	 */
	public StringComposer() {
		stack = new LinkedList<Character>();
		countKeys = 0;
		countBrackets = 0;
		countParentheses = 0;
		countSingleQuotes = 0;
		countDoubleQuotes = 0;
		countTabs = 0;
		sb = new StringBuilder();
	}

	/**
	 * 
	 * @return
	 */
	public StringComposer score() {
		sb.append(".");
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public StringComposer comma() {
		sb.append(",");
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public StringComposer semicolon() {
		sb.append(";");
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public StringComposer openKeys() {
		sb.append("{");
		stack.add('{');
		countKeys++;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public StringComposer closeKeys() {
		sb.append("}");
		removeFromStack('{');
		countKeys--;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public StringComposer openBrackets() {
		sb.append("[");
		stack.add('[');
		countBrackets++;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public StringComposer closeBrackets() {
		sb.append("]");
		removeFromStack('[');
		countBrackets--;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public StringComposer openParentheses() {
		sb.append("(");
		stack.add('(');
		countParentheses++;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public StringComposer closeParentheses() {
		sb.append(")");
		removeFromStack('(');
		countParentheses--;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public StringComposer openSingleQuote() {
		sb.append("'");
		countSingleQuotes++;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public StringComposer closeSingleQuote() {
		sb.append("'");
		countSingleQuotes--;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public StringComposer openDoubleQuote() {
		sb.append("\"");
		countDoubleQuotes++;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public StringComposer closeDoubleQuote() {
		sb.append("\"");
		countDoubleQuotes--;
		return this;
	}

	public StringComposer tab() {
		countTabs++;
		return this;
	}

	public StringComposer tab(int t) {
		countTabs += t;
		return this;
	}

	public StringComposer untab() {
		countTabs--;
		if (countTabs < 0) {
			countTabs = 0;
		}
		return this;
	}

	public StringComposer untab(int t) {
		countTabs -= t;
		if (countTabs < 0) {
			countTabs = 0;
		}
		return this;
	}

	public StringComposer breakline() {
		sb.append(System.lineSeparator());
		for (int i = 0; i < countTabs; i++) {
			sb.append("\t");
		}
		return this;
	}

	private String insideBreakline() {
		String sb = System.lineSeparator();
		for (int i = 0; i < countTabs; i++) {
			sb += "\t";
		}
		return sb;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public StringComposer append(boolean b) {
		sb.append(b);
		return this;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public StringComposer keys(boolean b) {
		sb.append("{").append(b).append("}");
		return this;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public StringComposer brackets(boolean b) {
		sb.append("[").append(b).append("]");
		return this;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public StringComposer parentheses(boolean b) {
		sb.append("(").append(b).append(")");
		return this;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public StringComposer singleQuotes(boolean b) {
		sb.append("'").append(b).append("'");
		return this;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public StringComposer doubleQuotes(boolean b) {
		sb.append("\"").append(b).append("\"");
		return this;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public StringComposer appendln(boolean b) {
		return append(b).breakline();
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public StringComposer keysln(boolean b) {
		return keys(b).breakline();
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public StringComposer bracketsln(boolean b) {
		return brackets(b).breakline();
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public StringComposer parenthesesln(boolean b) {
		return parentheses(b).breakline();
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public StringComposer singleQuotesLn(boolean b) {
		return singleQuotes(b).breakline();
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public StringComposer doubleQuotesLn(boolean b) {
		return doubleQuotes(b).breakline();
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public StringComposer append(char c) {
		sb.append(c);
		return this;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public StringComposer keys(char c) {
		sb.append("{").append(c).append("}");
		return this;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public StringComposer brackets(char c) {
		sb.append("[").append(c).append("]");
		return this;
	}

	public StringComposer parentheses(char c) {
		sb.append("(").append(c).append(")");
		return this;
	}

	public StringComposer singleQuotes(char c) {
		sb.append("'").append(c).append("'");
		return this;
	}

	public StringComposer doubleQuotes(char c) {
		sb.append("\"").append(c).append("\"");
		return this;
	}

	public StringComposer appendln(char c) {
		return append(c).breakline();
	}

	public StringComposer keysln(char c) {
		return keys(c).breakline();
	}

	public StringComposer bracketsln(char c) {
		return brackets(c).breakline();
	}

	public StringComposer parenthesesln(char c) {
		return parentheses(c).breakline();
	}

	public StringComposer singleQuotesLn(char c) {
		return singleQuotes(c).breakline();
	}

	public StringComposer doubleQuotesLn(char c) {
		return doubleQuotes(c).breakline();
	}

	public StringComposer append(char[] str) {
		sb.append(str);
		return this;
	}

	public StringComposer keys(char[] str) {
		sb.append("{").append(str).append("}");
		return this;
	}

	public StringComposer brackets(char[] str) {
		sb.append("[").append(str).append("]");
		return this;
	}

	public StringComposer parentheses(char[] str) {
		sb.append("(").append(str).append(")");
		return this;
	}

	public StringComposer singleQuotes(char[] str) {
		sb.append("'").append(str).append("'");
		return this;
	}

	public StringComposer doubleQuotes(char[] str) {
		sb.append("\"").append(str).append("\"");
		return this;
	}

	public StringComposer appendln(char[] str) {
		sb.append(str);
		return breakline();
	}

	public StringComposer keysln(char[] str) {
		return keys(str).breakline();
	}

	public StringComposer bracketsln(char[] str) {
		return brackets(str).breakline();
	}

	public StringComposer parenthesesln(char[] str) {
		return parentheses(str).breakline();
	}

	public StringComposer singleQuotesLn(char[] str) {
		return singleQuotes(str).breakline();
	}

	public StringComposer doubleQuotesLn(char[] str) {
		return doubleQuotes(str).breakline();
	}

	public StringComposer append(char[] str, int offset, int len) {
		sb.append(str, offset, len);
		return this;
	}

	public StringComposer keys(char[] str, int offset, int len) {
		sb.append("{" + new String(str) + "}", offset, len + "{".length() + "}".length());
		return this;
	}

	public StringComposer brackets(char[] str, int offset, int len) {
		sb.append("[" + new String(str) + "]", offset, len + "[".length() + "]".length());
		return this;
	}

	public StringComposer parentheses(char[] str, int offset, int len) {
		sb.append("(" + new String(str) + ")", offset, len + "(".length() + ")".length());
		return this;
	}

	public StringComposer singleQuotes(char[] str, int offset, int len) {
		sb.append("'" + new String(str) + "'", offset, len + ("'".length() * 2));
		return this;
	}

	public StringComposer doubleQuotes(char[] str, int offset, int len) {
		sb.append("\"" + new String(str) + "\"", offset, len + ("\"".length() * 2));
		return this;
	}

	public StringComposer appendln(char[] str, int offset, int len) {
		String apd = insideBreakline();
		sb.append(new String(str) + apd, offset, len + apd.length());
		return this;
	}

	public StringComposer keysln(char[] str, int offset, int len) {
		String apd = insideBreakline();
		sb.append("{" + new String(str) + "}" + apd, offset, len + "{".length() + "}".length() + apd.length());
		return this;
	}

	public StringComposer bracketsln(char[] str, int offset, int len) {
		String apd = insideBreakline();
		sb.append("[" + new String(str) + "]" + apd, offset, len + "[".length() + "]".length() + apd.length());
		return this;
	}

	public StringComposer parenthesesln(char[] str, int offset, int len) {
		String apd = insideBreakline();
		sb.append("(" + new String(str) + ")" + apd, offset, len + "(".length() + ")".length() + apd.length());
		return this;
	}

	public StringComposer singleQuotesLn(char[] str, int offset, int len) {
		String apd = insideBreakline();
		sb.append("'" + new String(str) + "'" + apd, offset, len + ("'".length() * 2) + apd.length());
		return this;
	}

	public StringComposer doubleQuotesLn(char[] str, int offset, int len) {
		String apd = insideBreakline();
		sb.append("\"" + new String(str) + "\"" + apd, offset, len + ("\"".length() * 2) + apd.length());
		return this;
	}

	public StringComposer append(double d) {
		sb.append(d);
		return this;
	}

	public StringComposer keys(double d) {
		sb.append("{").append(d).append("}");
		return this;
	}

	public StringComposer brackets(double d) {
		sb.append("[").append(d).append("]");
		return this;
	}

	public StringComposer parentheses(double d) {
		sb.append("(").append(d).append(")");
		return this;
	}

	public StringComposer singleQuotes(double d) {
		sb.append("'").append(d).append("'");
		return this;
	}

	public StringComposer doubleQuotes(double d) {
		sb.append("\"").append(d).append("\"");
		return this;
	}

	public StringComposer appendln(double d) {
		sb.append(d).append(insideBreakline());
		return this;
	}

	public StringComposer keysln(double d) {
		return keys(d).breakline();
	}

	public StringComposer bracketsln(double d) {
		return brackets(d).breakline();
	}

	public StringComposer parenthesesln(double d) {
		return parentheses(d).breakline();
	}

	public StringComposer singleQuotesLn(double d) {
		return singleQuotes(d).breakline();
	}

	public StringComposer doubleQuotesLn(double d) {
		return doubleQuotes(d).breakline();
	}

	public StringComposer append(float f) {
		sb.append(f);
		return this;
	}

	public StringComposer keys(float f) {
		sb.append("{").append(f).append("}");
		return this;
	}

	public StringComposer brackets(float f) {
		sb.append("[").append(f).append("]");
		return this;
	}

	public StringComposer parentheses(float f) {
		sb.append("(").append(f).append(")");
		return this;
	}

	public StringComposer singleQuotes(float f) {
		sb.append("'").append(f).append("'");
		return this;
	}

	public StringComposer doubleQuotes(float f) {
		sb.append("\"").append(f).append("\"");
		return this;
	}

	public StringComposer appendln(float f) {
		return appendln(f).breakline();
	}

	public StringComposer keysln(float f) {
		return keys(f).breakline();
	}

	public StringComposer bracketsln(float f) {
		return brackets(f).breakline();
	}

	public StringComposer parenthesesln(float f) {
		return parentheses(f).breakline();
	}

	public StringComposer singleQuotesLn(float f) {
		return singleQuotes(f).breakline();
	}

	public StringComposer doubleQuotesLn(float f) {
		return doubleQuotes(f).breakline();
	}

	public StringComposer append(int i) {
		sb.append(i);
		return this;
	}

	public StringComposer keys(int i) {
		sb.append("{").append(i).append("}");
		return this;
	}

	public StringComposer brackets(int i) {
		sb.append("[").append(i).append("]");
		return this;
	}

	public StringComposer parentheses(int i) {
		sb.append("(").append(i).append(")");
		return this;
	}

	public StringComposer singleQuotes(int i) {
		sb.append("'").append(i).append("'");
		return this;
	}

	public StringComposer doubleQuotes(int i) {
		sb.append("\"").append(i).append("\"");
		return this;
	}

	public StringComposer appendln(int i) {
		return append(i).breakline();
	}

	public StringComposer keysln(int i) {
		return keys(i).breakline();
	}

	public StringComposer bracketsln(int i) {
		return brackets(i).breakline();
	}

	public StringComposer parenthesesln(int i) {
		return parentheses(i).breakline();
	}

	public StringComposer singleQuotesLn(int i) {
		return singleQuotes(i).breakline();
	}

	public StringComposer doubleQuotesLn(int i) {
		return doubleQuotes(i).breakline();
	}

	public StringComposer append(long l) {
		sb.append(l);
		return this;
	}

	public StringComposer keys(long l) {
		sb.append("{").append(l).append("}");
		return this;
	}

	public StringComposer brackets(long l) {
		sb.append("[").append(l).append("]");
		return this;
	}

	public StringComposer parentheses(long l) {
		sb.append("(").append(l).append(")");
		return this;
	}

	public StringComposer singleQuotes(long l) {
		sb.append("'").append(l).append("'");
		return this;
	}

	public StringComposer doubleQuotes(long l) {
		sb.append("\"").append(l).append("\"");
		return this;
	}

	public StringComposer appendln(long l) {
		return append(l).breakline();
	}

	public StringComposer keysln(long l) {
		return keys(l).breakline();
	}

	public StringComposer bracketsln(long l) {
		return brackets(l).breakline();
	}

	public StringComposer parenthesesln(long l) {
		return parentheses(l).breakline();
	}

	public StringComposer singleQuotesLn(long l) {
		return singleQuotes(l).breakline();
	}

	public StringComposer doubleQuotesLn(long l) {
		return doubleQuotes(l).breakline();
	}

	public StringComposer append(Object obj) {
		sb.append(obj);
		return this;
	}

	public StringComposer keys(Object obj) {
		sb.append("{").append(obj).append("}");
		return this;
	}

	public StringComposer brackets(Object obj) {
		sb.append("[").append(obj).append("]");
		return this;
	}

	public StringComposer parentheses(Object obj) {
		sb.append("(").append(obj).append(")");
		return this;
	}

	public StringComposer singleQuotes(Object obj) {
		sb.append("'").append(obj).append("'");
		return this;
	}

	public StringComposer doubleQuotes(Object obj) {
		sb.append("\"").append(obj).append("\"");
		return this;
	}

	public StringComposer appendln(Object obj) {
		return append(obj).breakline();
	}

	public StringComposer keysln(Object obj) {
		return keys(obj).breakline();
	}

	public StringComposer bracketsln(Object obj) {
		return brackets(obj).breakline();
	}

	public StringComposer parenthesesln(Object obj) {
		return parentheses(obj).breakline();
	}

	public StringComposer singleQuotesLn(Object obj) {
		return singleQuotes(obj).breakline();
	}

	public StringComposer doubleQuotesLn(Object obj) {
		return doubleQuotes(obj).breakline();
	}

	public StringComposer append(String str) {
		sb.append(str);
		return this;
	}

	public StringComposer keys(String str) {
		sb.append("{").append(str).append("}");
		return this;
	}

	public StringComposer brackets(String str) {
		sb.append("[").append(str).append("]");
		return this;
	}

	public StringComposer parentheses(String str) {
		sb.append("(").append(str).append(")");
		return this;
	}

	public StringComposer singleQuotes(String str) {
		sb.append("'").append(str).append("'");
		return this;
	}

	public StringComposer doubleQuotes(String str) {
		sb.append("\"").append(str).append("\"");
		return this;
	}

	public StringComposer appendln(String str) {
		sb.append(str).append(insideBreakline());
		return this;
	}

	public StringComposer keysln(String str) {
		return keys(str).breakline();
	}

	public StringComposer bracketsln(String str) {
		return brackets(str).breakline();
	}

	public StringComposer parenthesesln(String str) {
		return parentheses(str).breakline();
	}

	public StringComposer singleQuotesLn(String str) {
		return singleQuotes(str).breakline();
	}

	public StringComposer doubleQuotesLn(String str) {
		return doubleQuotes(str).breakline();
	}

	public StringComposer append(StringBuffer sb) {
		this.sb.append(sb);
		return this;
	}

	public StringComposer keys(StringBuffer sb) {
		this.sb.append("{").append(sb).append("}");
		return this;
	}

	public StringComposer brackets(StringBuffer sb) {
		this.sb.append("[").append(sb).append("]");
		return this;
	}

	public StringComposer parentheses(StringBuffer sb) {
		this.sb.append("(").append(sb).append(")");
		return this;
	}

	public StringComposer singleQuotes(StringBuffer sb) {
		this.sb.append("'").append(sb).append("'");
		return this;
	}

	public StringComposer doubleQuotes(StringBuffer sb) {
		this.sb.append("\"").append(sb).append("\"");
		return this;
	}

	public StringComposer appendln(StringBuffer sb) {
		sb.append(sb).append(insideBreakline());
		return this;
	}

	public StringComposer keysln(StringBuffer sb) {
		return keys(sb).breakline();
	}

	public StringComposer bracketsln(StringBuffer sb) {
		return brackets(sb).breakline();
	}

	public StringComposer parenthesesln(StringBuffer sb) {
		return parentheses(sb).breakline();
	}

	public StringComposer appendCodePoint(int codePoint) {
		sb.appendCodePoint(codePoint);
		return this;
	}

	public int capacity() {
		return this.capacity();
	}

	public char charAt(int index) {
		return sb.charAt(index);
	}

	public int codePointAt(int index) {
		return sb.codePointAt(index);
	}

	public int codePointBefore(int index) {
		return sb.codePointBefore(index);
	}

	public int codePointCount(int beginIndex, int endIndex) {
		return sb.codePointCount(beginIndex, endIndex);
	}

	public StringComposer delete(int start, int end) {
		sb.delete(start, end);
		return this;
	}

	public StringComposer deleteCharAt(int index) {
		sb.deleteCharAt(index);
		return this;
	}

	public void ensureCapacity(int minimumCapacity) {
		sb.ensureCapacity(minimumCapacity);
	}

	public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
		sb.getChars(srcBegin, srcEnd, dst, dstBegin);
	}

	public int indexOf(String str) {
		return this.sb.indexOf(str);
	}

	public int indexOf(String str, int fromIndex) {
		return this.sb.indexOf(str, fromIndex);
	}

	public StringComposer insert(int offset, boolean b) {
		sb.insert(offset, b);
		return this;
	}

	public StringComposer insertKeys(int offset, boolean b) {
		sb.insert(offset, "{" + b + "}");
		return this;
	}

	public StringComposer insertBrackets(int offset, boolean b) {
		sb.insert(offset, "[" + b + "]");
		return this;
	}

	public StringComposer insertParentheses(int offset, boolean b) {
		sb.insert(offset, "(" + b + ")");
		return this;
	}

	public StringComposer insertSingleQuotes(int offset, boolean b) {
		sb.insert(offset, "'" + b + "'");
		return this;
	}

	public StringComposer insertDoubleQuotes(int offset, boolean b) {
		sb.insert(offset, "\"" + b + "\"");
		return this;
	}

	public StringComposer insertln(int offset, boolean b) {
		sb.insert(offset, ((Boolean) b).toString() + insideBreakline());
		return this;
	}

	public StringComposer insertKeysLn(int offset, boolean b) {
		sb.insert(offset, "{" + ((Boolean) b).toString() + "}" + insideBreakline());
		return this;
	}

	public StringComposer insertBracketsLn(int offset, boolean b) {
		sb.insert(offset, "[" + ((Boolean) b).toString() + "]" + insideBreakline());
		return this;
	}

	public StringComposer insertParenthesesLn(int offset, boolean b) {
		sb.insert(offset, "(" + ((Boolean) b).toString() + ")" + insideBreakline());
		return this;
	}

	public StringComposer insertSingleQuotesLn(int offset, boolean b) {
		sb.insert(offset, "'" + ((Boolean) b).toString() + "'" + insideBreakline());
		return this;
	}

	public StringComposer insertDoubleQuotesLn(int offset, boolean b) {
		sb.insert(offset, "\"" + ((Boolean) b).toString() + "\"" + insideBreakline());
		return this;
	}

	public StringComposer insert(int offset, char c) {
		sb.insert(offset, c);
		return this;
	}

	public StringComposer insertKeys(int offset, char c) {
		sb.insert(offset, "{" + c + "}");
		return this;
	}

	public StringComposer insertBrackets(int offset, char c) {
		sb.insert(offset, "[" + c + "]");
		return this;
	}

	public StringComposer insertParentheses(int offset, char c) {
		sb.insert(offset, "(" + c + ")");
		return this;
	}

	public StringComposer insertSingleQuotes(int offset, char c) {
		sb.insert(offset, "'" + c + "'");
		return this;
	}

	public StringComposer insertDoubleQuotes(int offset, char c) {
		sb.insert(offset, "\"" + c + "\"");
		return this;
	}

	public StringComposer insertln(int offset, char c) {
		sb.insert(offset, insideBreakline()).insert(offset, c);
		return this;
	}

	public StringComposer insertKeysLn(int offset, char c) {
		sb.insert(offset, "{" + c + "}");
		return this;
	}

	public StringComposer insertBracketsLn(int offset, char c) {
		sb.insert(offset, "[" + c + "]");
		return this;
	}

	public StringComposer insertParenthesesLn(int offset, char c) {
		sb.insert(offset, "(" + c + ")");
		return this;
	}

	public StringComposer insertSingleQuotesLn(int offset, char c) {
		sb.insert(offset, "'" + c + "'");
		return this;
	}

	public StringComposer insertDoubleQuotesLn(int offset, char c) {
		sb.insert(offset, "\"" + c + "\"");
		return this;
	}

	public StringComposer insert(int offset, char[] str) {
		sb.insert(offset, str);
		return this;
	}

	public StringComposer insertKeys(int offset, char[] str) {
		sb.insert(offset, "{" + new String(str) + "}");
		return this;
	}

	public StringComposer insertBrackets(int offset, char[] str) {
		sb.insert(offset, "[" + new String(str) + "]");
		return this;
	}

	public StringComposer insertParentheses(int offset, char[] str) {
		sb.insert(offset, "(" + new String(str) + ")");
		return this;
	}

	public StringComposer insertSingleQuotes(int offset, char[] str) {
		sb.insert(offset, "'" + new String(str) + "'");
		return this;
	}

	public StringComposer insertDoubleQuotes(int offset, char[] str) {
		sb.insert(offset, "\"" + new String(str) + "\"");
		return this;
	}

	public StringComposer insertln(int offset, char[] str) {
		sb.insert(offset, insideBreakline()).insert(offset, str);
		return this;
	}

	public StringComposer insertKeysLn(int offset, char[] str) {
		sb.insert(offset, "{" + new String(str) + "}" + insideBreakline());
		return this;
	}

	public StringComposer insertBracketsLn(int offset, char[] str) {
		sb.insert(offset, "[" + new String(str) + "]" + insideBreakline());
		return this;
	}

	public StringComposer insertParenthesesLn(int offset, char[] str) {
		sb.insert(offset, "(" + new String(str) + ")" + insideBreakline());
		return this;
	}

	public StringComposer insertSingleQuotesLn(int offset, char[] str) {
		sb.insert(offset, "'" + new String(str) + "'" + insideBreakline());
		return this;
	}

	public StringComposer insertDoubleQuotesLn(int offset, char[] str) {
		sb.insert(offset, "\"" + new String(str) + "\"" + insideBreakline());
		return this;
	}

	public StringComposer insert(int index, char[] str, int offset, int len) {
		sb.insert(index, str, offset, len);
		return this;
	}

	public StringComposer insertKeys(int index, char[] str, int offset, int len) {
		sb.insert(index, "{" + new String(str) + "}", offset, len + "{".length() + "}".length());
		return this;
	}

	public StringComposer insertBrackets(int index, char[] str, int offset, int len) {
		sb.insert(index, "[" + new String(str) + "]", offset, len + "[".length() + "]".length());
		return this;
	}

	public StringComposer insertParentheses(int index, char[] str, int offset, int len) {
		sb.insert(index, "(" + new String(str) + ")", offset, len + "(".length() + ")".length());
		return this;
	}

	public StringComposer insertSingleQuotes(int index, char[] str, int offset, int len) {
		sb.insert(index, "'" + new String(str) + "'", offset, len + "'".length() * 2);
		return this;
	}

	public StringComposer insertDoubleQuotes(int index, char[] str, int offset, int len) {
		sb.insert(index, "\"" + new String(str) + "\"", offset, len + "\"".length() * 2);
		return this;
	}

	public StringComposer insertln(int index, char[] str, int offset, int len) {
		String apd = insideBreakline();
		sb.insert(index, new String(str) + apd, offset, len + apd.length());
		return this;
	}

	public StringComposer insertKeysLn(int index, char[] str, int offset, int len) {
		String apd = insideBreakline();
		sb.insert(index, "{" + new String(str) + "}" + apd, offset, len + "{".length() + "}".length() + apd.length());
		return this;
	}

	public StringComposer insertBracketsLn(int index, char[] str, int offset, int len) {
		String apd = insideBreakline();
		sb.insert(index, "[" + new String(str) + "]" + apd, offset, len + "[".length() + "]".length() + apd.length());
		return this;
	}

	public StringComposer insertParenthesesLn(int index, char[] str, int offset, int len) {
		String apd = insideBreakline();
		sb.insert(index, "(" + new String(str) + ")" + apd, offset, len + "(".length() + ")".length() + apd.length());
		return this;
	}

	public StringComposer insertSingleQuotesLn(int index, char[] str, int offset, int len) {
		String apd = insideBreakline();
		sb.insert(index, "'" + new String(str) + "'" + apd, offset, len + "'".length() * 2 + apd.length());
		return this;
	}

	public StringComposer insertDoubleQuotesLn(int index, char[] str, int offset, int len) {
		String apd = insideBreakline();
		sb.insert(index, "\"" + new String(str) + "\"" + apd, offset, len + "\"".length() * 2 + apd.length());
		return this;
	}

	public StringComposer insert(int dstOffset, CharSequence s) {
		sb.insert(dstOffset, s);
		return this;
	}

	public StringComposer insertKeys(int dstOffset, CharSequence s) {
		sb.insert(dstOffset, "{" + s + "}");
		return this;
	}

	public StringComposer insertBrackets(int dstOffset, CharSequence s) {
		sb.insert(dstOffset, "[" + s + "]");
		return this;
	}

	public StringComposer insertParentheses(int dstOffset, CharSequence s) {
		sb.insert(dstOffset, "(" + s + ")");
		return this;
	}

	public StringComposer insertSingleQuotes(int dstOffset, CharSequence s) {
		sb.insert(dstOffset, "'" + s + "'");
		return this;
	}

	public StringComposer insertDoubleQuotes(int dstOffset, CharSequence s) {
		sb.insert(dstOffset, "\"" + s + "\"");
		return this;
	}

	public StringComposer insertln(int dstOffset, CharSequence s) {
		sb.insert(dstOffset, s + insideBreakline());
		return this;
	}

	public StringComposer insertKeysLn(int dstOffset, CharSequence s) {
		sb.insert(dstOffset, "{" + s + "}" + insideBreakline());
		return this;
	}

	public StringComposer insertBracketsLn(int dstOffset, CharSequence s) {
		sb.insert(dstOffset, "[" + s + "]" + insideBreakline());
		return this;
	}

	public StringComposer insertParenthesesLn(int dstOffset, CharSequence s) {
		sb.insert(dstOffset, "(" + s + ")" + insideBreakline());
		return this;
	}

	public StringComposer insertSingleQuotesLn(int dstOffset, CharSequence s) {
		sb.insert(dstOffset, "'" + s + "'" + insideBreakline());
		return this;
	}

	public StringComposer insertDoubleQuotesLn(int dstOffset, CharSequence s) {
		sb.insert(dstOffset, "\"" + s + "\"" + insideBreakline());
		return this;
	}

	public StringComposer insert(int dstOffset, CharSequence s, int start, int end) {
		sb.insert(dstOffset, s, start, end);
		return this;
	}

	public StringComposer insertKeys(int dstOffset, CharSequence s, int start, int end) {
		sb.insert(dstOffset, "{" + s.toString() + "}", start, end + "{".length() + "}".length());
		return this;
	}

	public StringComposer insertBrackets(int dstOffset, CharSequence s, int start, int end) {
		sb.insert(dstOffset, "[" + s.toString() + "]", start, end + "[".length() + "]".length());
		return this;
	}

	public StringComposer insertParentheses(int dstOffset, CharSequence s, int start, int end) {
		sb.insert(dstOffset, "(" + s.toString() + ")", start, end + "(".length() + ")".length());
		return this;
	}

	public StringComposer insertSingleQuotes(int dstOffset, CharSequence s, int start, int end) {
		sb.insert(dstOffset, "'" + s.toString() + "'", start, end + "'".length() + "'".length());
		return this;
	}

	public StringComposer insertDoubleQuotes(int dstOffset, CharSequence s, int start, int end) {
		sb.insert(dstOffset, "\"" + s.toString() + "\"", start, end + "\"".length() + "\"".length());
		return this;
	}

	public StringComposer insertln(int dstOffset, CharSequence s, int start, int end) {
		String apd = insideBreakline();
		sb.insert(dstOffset, apd, start, start + apd.length()).insert(dstOffset, s, start, end);
		return this;
	}

	public StringComposer insertKeysLn(int dstOffset, CharSequence s, int start, int end) {
		String apd = insideBreakline();
		sb.insert(dstOffset, "{" + s.toString() + "}" + apd, start, end + "{".length() + "}".length() + apd.length());
		return this;
	}

	public StringComposer insertBracketsLn(int dstOffset, CharSequence s, int start, int end) {
		String apd = insideBreakline();
		sb.insert(dstOffset, "[" + s.toString() + "]" + apd, start, end + "[".length() + "]".length() + apd.length());
		return this;
	}

	public StringComposer insertParenthesesLn(int dstOffset, CharSequence s, int start, int end) {
		String apd = insideBreakline();
		sb.insert(dstOffset, "(" + s.toString() + ")" + apd, start, end + "(".length() + ")".length() + apd.length());
		return this;
	}

	public StringComposer insertSingleQuotesLn(int dstOffset, CharSequence s, int start, int end) {
		String apd = insideBreakline();
		sb.insert(dstOffset, "'" + s.toString() + "'" + apd, start, end + "'".length() + "'".length() + apd.length());
		return this;
	}

	public StringComposer insertDoubleQuotesLn(int dstOffset, CharSequence s, int start, int end) {
		String apd = insideBreakline();
		sb.insert(dstOffset, "\"" + s.toString() + "\"" + apd, start,
				end + "\"".length() + "\"".length() + apd.length());
		return this;
	}

	public StringComposer insert(int offset, double d) {
		sb.insert(offset, d);
		return this;
	}

	public StringComposer insertKeys(int offset, double d) {
		sb.insert(offset, "{" + Double.toString(d) + "}");
		return this;
	}

	public StringComposer insertBrackets(int offset, double d) {
		sb.insert(offset, "[" + Double.toString(d) + "]");
		return this;
	}

	public StringComposer insertParentheses(int offset, double d) {
		sb.insert(offset, "(" + Double.toString(d) + ")");
		return this;
	}

	public StringComposer insertSingleQuotes(int offset, double d) {
		sb.insert(offset, "'" + Double.toString(d) + "'");
		return this;
	}

	public StringComposer insertDoubleQuotes(int offset, double d) {
		sb.insert(offset, "\"" + Double.toString(d) + "\"");
		return this;
	}

	public StringComposer insertln(int offset, double d) {
		sb.insert(offset, Double.toString(d) + insideBreakline());
		return this;
	}

	public StringComposer insertKeysLn(int offset, double d) {
		sb.insert(offset, "{" + Double.toString(d) + "}" + insideBreakline());
		return this;
	}

	public StringComposer insertBracketsLn(int offset, double d) {
		sb.insert(offset, "[" + Double.toString(d) + "]" + insideBreakline());
		return this;
	}

	public StringComposer insertParenthesesLn(int offset, double d) {
		sb.insert(offset, "(" + Double.toString(d) + ")" + insideBreakline());
		return this;
	}

	public StringComposer insertSingleQuotesLn(int offset, double d) {
		sb.insert(offset, "'" + Double.toString(d) + "'" + insideBreakline());
		return this;
	}

	public StringComposer insertDoubleQuotesLn(int offset, double d) {
		sb.insert(offset, "\"" + Double.toString(d) + "\"" + insideBreakline());
		return this;
	}

	public StringComposer insert(int offset, float f) {
		sb.insert(offset, f);
		return this;
	}

	public StringComposer insertKeys(int offset, float f) {
		sb.insert(offset, "{" + Float.toString(f) + "}");
		return this;
	}

	public StringComposer insertBrackets(int offset, float f) {
		sb.insert(offset, "[" + Float.toString(f) + "]");
		return this;
	}

	public StringComposer insertParentheses(int offset, float f) {
		sb.insert(offset, "(" + Float.toString(f) + ")");
		return this;
	}

	public StringComposer insertSingleQuotes(int offset, float f) {
		sb.insert(offset, "'" + Float.toString(f) + "'");
		return this;
	}

	public StringComposer insertDoubleQuotes(int offset, float f) {
		sb.insert(offset, "\"" + Float.toString(f) + "\"");
		return this;
	}

	public StringComposer insertln(int offset, float f) {
		sb.insert(offset, insideBreakline()).insert(offset, f);
		return this;
	}

	public StringComposer insert(int offset, int i) {
		sb.insert(offset, i);
		return this;
	}

	public StringComposer insertKeys(int offset, int i) {
		sb.insert(offset, "{" + Integer.toString(i) + "}");
		return this;
	}

	public StringComposer insertBrackets(int offset, int i) {
		sb.insert(offset, "[" + Integer.toString(i) + "]");
		return this;
	}

	public StringComposer insertParentheses(int offset, int i) {
		sb.insert(offset, "(" + Integer.toString(i) + ")");
		return this;
	}

	public StringComposer insertSingleQuotes(int offset, int i) {
		sb.insert(offset, "'" + Integer.toString(i) + "'");
		return this;
	}

	public StringComposer insertDoubleQuotes(int offset, int i) {
		sb.insert(offset, "\"" + Integer.toString(i) + "\"");
		return this;
	}

	public StringComposer insertln(int offset, int i) {
		sb.insert(offset, Integer.toString(i) + insideBreakline());
		return this;
	}

	public StringComposer insertKeysln(int offset, int i) {
		sb.insert(offset, "{" + Integer.toString(i) + "}" + insideBreakline());
		return this;
	}

	public StringComposer insertBracketsln(int offset, int i) {
		sb.insert(offset, "[" + Integer.toString(i) + "]" + insideBreakline());
		return this;
	}

	public StringComposer insertParenthesesln(int offset, int i) {
		sb.insert(offset, "(" + Integer.toString(i) + ")" + insideBreakline());
		return this;
	}

	public StringComposer insertSingleQuotesln(int offset, int i) {
		sb.insert(offset, "'" + Integer.toString(i) + "'" + insideBreakline());
		return this;
	}

	public StringComposer insertDoubleQuotesln(int offset, int i) {
		sb.insert(offset, "\"" + Integer.toString(i) + "\"" + insideBreakline());
		return this;
	}

	public StringComposer insert(int offset, long l) {
		sb.insert(offset, l);
		return this;
	}

	public StringComposer insertKeys(int offset, long l) {
		sb.insert(offset, "{" + Long.toString(l) + "}");
		return this;
	}

	public StringComposer insertBrackets(int offset, long l) {
		sb.insert(offset, "[" + Long.toString(l) + "]");
		return this;
	}

	public StringComposer insertParentheses(int offset, long l) {
		sb.insert(offset, "(" + Long.toString(l) + ")");
		return this;
	}

	public StringComposer insertSingleQuotes(int offset, long l) {
		sb.insert(offset, "'" + Long.toString(l) + "'");
		return this;
	}

	public StringComposer insertDoubleQuotes(int offset, long l) {
		sb.insert(offset, "\"" + Long.toString(l) + "\"");
		return this;
	}

	public StringComposer insertln(int offset, long l) {
		sb.insert(offset, Long.toString(l) + insideBreakline());
		return this;
	}

	public StringComposer insertKeysLn(int offset, long l) {
		sb.insert(offset, "{" + Long.toString(l) + "}" + insideBreakline());
		return this;
	}

	public StringComposer insertBracketsLn(int offset, long l) {
		sb.insert(offset, "[" + Long.toString(l) + "]" + insideBreakline());
		return this;
	}

	public StringComposer insertParenthesesLn(int offset, long l) {
		sb.insert(offset, "(" + Long.toString(l) + ")" + insideBreakline());
		return this;
	}

	public StringComposer insertSingleQuotesLn(int offset, long l) {
		sb.insert(offset, "'" + Long.toString(l) + "'" + insideBreakline());
		return this;
	}

	public StringComposer insertDoubleqQuotesLn(int offset, long l) {
		sb.insert(offset, "\"" + Long.toString(l) + "\"" + insideBreakline());
		return this;
	}

	public StringComposer insert(int offset, Object obj) {
		sb.insert(offset, obj);
		return this;
	}

	public StringComposer insertKeys(int offset, Object obj) {
		sb.insert(offset, "{" + new StringBuilder().append(obj).toString() + "}");
		return this;
	}

	public StringComposer insertBrackets(int offset, Object obj) {
		sb.insert(offset, "[" + new StringBuilder().append(obj).toString() + "]");
		return this;
	}

	public StringComposer insertParentheses(int offset, Object obj) {
		sb.insert(offset, "(" + new StringBuilder().append(obj).toString() + ")");
		return this;
	}

	public StringComposer insertSingleQuotes(int offset, Object obj) {
		sb.insert(offset, "'" + new StringBuilder().append(obj).toString() + "'");
		return this;
	}

	public StringComposer insertDoubleQuotes(int offset, Object obj) {
		sb.insert(offset, "\"" + new StringBuilder().append(obj).toString() + "\"");
		return this;
	}

	public StringComposer insertln(int offset, Object obj) {
		sb.insert(offset, new StringBuilder().append(obj).toString() + insideBreakline());
		return this;
	}

	public StringComposer insertKeysLn(int offset, Object obj) {
		sb.insert(offset, "{" + new StringBuilder().append(obj).toString() + "}" + insideBreakline());
		return this;
	}

	public StringComposer insertBracketsLn(int offset, Object obj) {
		sb.insert(offset, "[" + new StringBuilder().append(obj).toString() + "]" + insideBreakline());
		return this;
	}

	public StringComposer insertParenthesesLn(int offset, Object obj) {
		sb.insert(offset, "(" + new StringBuilder().append(obj).toString() + ")" + insideBreakline());
		return this;
	}

	public StringComposer insertSingleQuotesLn(int offset, Object obj) {
		sb.insert(offset, "'" + new StringBuilder().append(obj).toString() + "'" + insideBreakline());
		return this;
	}

	public StringComposer insertDoubleQuotesLn(int offset, Object obj) {
		sb.insert(offset, "\"" + new StringBuilder().append(obj).toString() + "\"" + insideBreakline());
		return this;
	}

	public StringComposer insert(int offset, String str) {
		sb.insert(offset, str);
		return this;
	}

	public StringComposer insertKeys(int offset, String str) {
		sb.insert(offset, "{" + str + "}");
		return this;
	}

	public StringComposer insertBrackets(int offset, String str) {
		sb.insert(offset, "[" + str + "]");
		return this;
	}

	public StringComposer insertParentheses(int offset, String str) {
		sb.insert(offset, "(" + str + ")");
		return this;
	}

	public StringComposer insertSingleQuotes(int offset, String str) {
		sb.insert(offset, "'" + str + "'");
		return this;
	}

	public StringComposer insertDoubleQuotes(int offset, String str) {
		sb.insert(offset, "\"" + str + "\"");
		return this;
	}

	public StringComposer insertln(int offset, String str) {
		sb.insert(offset, str + insideBreakline());
		return this;
	}

	public StringComposer insertKeysLn(int offset, String str) {
		sb.insert(offset, "{" + str + "}" + insideBreakline());
		return this;
	}

	public StringComposer insertBracketsLn(int offset, String str) {
		sb.insert(offset, "[" + str + "]" + insideBreakline());
		return this;
	}

	public StringComposer insertPrenthesesLn(int offset, String str) {
		sb.insert(offset, "(" + str + ")" + insideBreakline());
		return this;
	}

	public StringComposer insertSingleQuotesLn(int offset, String str) {
		sb.insert(offset, "'" + str + "'" + insideBreakline());
		return this;
	}

	public StringComposer insertDoubleQuotesLn(int offset, String str) {
		sb.insert(offset, "\"" + str + "\"" + insideBreakline());
		return this;
	}

	public int lastIndexOf(String str) {
		return sb.lastIndexOf(str);
	}

	public int lastIndexOf(String str, int fromIndex) {
		return sb.lastIndexOf(str, fromIndex);
	}

	public int length() {
		return sb.length();
	}

	public int offsetByCodePoints(int index, int codePointOffset) {
		return sb.offsetByCodePoints(index, codePointOffset);
	}

	public StringComposer replace(int start, int end, String str) {
		sb.replace(start, end, str);
		return this;
	}

	public StringComposer replace(String to, String from) {
		int i = sb.indexOf(to);
		if (i > 0) {
			sb.replace(i, i + to.length(), from);
		}
		return this;
	}

	public boolean palindrome() {
		StringBuilder aux = new StringBuilder(sb);
		return aux.reverse().toString().equals(sb.toString());
	}

	public StringComposer setCharAt(int index, char c) {
		sb.setCharAt(index, c);
		return this;
	}

	public StringComposer setLength(int newlength) {
		sb.setLength(newlength);
		return this;
	}

	public CharSequence subSequence(int start, int end) {
		return sb.subSequence(start, end);
	}

	public String subString(int start) {
		return sb.substring(start);
	}

	public String subString(int start, int end) {
		return sb.substring(start, end);
	}

	/**
	 * 
	 * @return
	 */
	public boolean checkFormation() {
		boolean result = (countBrackets == 0) && (countKeys == 0) && (countParentheses == 0) && (countSingleQuotes == 0)
				&& (countDoubleQuotes == 0);
		if (result) {
			Pattern p, p2 = null;
			Matcher m, m2 = null;

			p = Pattern.compile("(");
			m = p.matcher(sb.toString());
			while (m.find()) {
				countParentheses++;
			}

			p = Pattern.compile(")");
			m = p.matcher(sb.toString());
			while (m.find()) {
				countParentheses--;
			}

			p = Pattern.compile("[");
			m = p.matcher(sb.toString());
			while (m.find()) {
				countBrackets++;
			}

			p = Pattern.compile("]");
			m = p.matcher(sb.toString());
			while (m.find()) {
				countBrackets--;
			}

			p = Pattern.compile("{");
			m = p.matcher(sb.toString());
			while (m.find()) {
				countKeys++;
			}

			p = Pattern.compile("}");
			m = p.matcher(sb.toString());
			while (m.find()) {
				countKeys--;
			}

			p = Pattern.compile("\"");
			m = p.matcher(sb.toString());
			// no mometno apenas uma estimativa
			while (m.find()) {
				countSingleQuotes = countSingleQuotes == 0 ? 1 : 0;
			}

			p2 = Pattern.compile("'");
			m2 = p2.matcher(sb.toString());
			// no momento apenas uma estimativa
			while (m2.find()) {
				countDoubleQuotes = countDoubleQuotes == 0 ? 1 : 0;
			}

			result = (countBrackets == 0) && (countKeys == 0) && (countParentheses == 0) && (countSingleQuotes == 0)
					&& (countDoubleQuotes == 0);
		}
		if (!result) {
			System.err.println(
					"the number of brackets, keys or parentheses do not match on interval:" + System.lineSeparator()//
							+ "parentheses left " + countParentheses + " | brackets left " + countBrackets
							+ " | keys left " + countKeys);
		} else {
			result = stack.size() == 0;
			if (!result) {
				System.err.println("the sequence of brackets, keys or parentheses do not match on interval.");
			}
		}
		return result;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return sb.toString();
	}

	/**
	 * 
	 * @return
	 */
	public StringComposer trimToSize() {
		sb.trimToSize();
		return this;
	}

	public StringComposer reverse() {
		sb.reverse();
		return this;
	}

	public StringComposer clear() {
		sb.setLength(0);
		return this;
	}

	public static StringBuilder clear(StringBuilder sb) {
		if (sb != null) {
			sb.setLength(0);
		} else {
			sb = new StringBuilder();
		}
		return sb;
	}
}
