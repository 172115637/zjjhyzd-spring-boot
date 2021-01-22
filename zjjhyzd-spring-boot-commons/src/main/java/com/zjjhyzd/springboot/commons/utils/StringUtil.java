package com.zjjhyzd.springboot.commons.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.text.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author Fan Peng
 *
 */
public class StringUtil {

	public static final Charset GBK = Charset.forName("GBK");
	public static final Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;
	public static final Charset UTF_8 = StandardCharsets.UTF_8;

	/**
	 * Pattern.compile("<.*?>");
	 */
	private static final Pattern p_html = Pattern.compile("<.*?>");

	/**
	 * 字符串转换
	 * 
	 * @param st
	 * @return ArrayList<String>
	 * @throws
	 */
	public static ArrayList<String> token2List(StringTokenizer st) {
		if (st == null || st.countTokens() == 0) {
			return new ArrayList<>();
		}

		ArrayList<String> resultList = new ArrayList<>();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			resultList.add(token);
		} /* while */

		return resultList;
	}

	/**
	 * 返回输入字符串根据指定分隔符转换为相应的‘驼峰’格式串
	 * 
	 * @param from
	 * @param delimiter
	 * @return <String>
	 * @throws
	 */
	public static String getCamelStyleString(String from, String delimiter) {
		if (from == null) {
			return null;
		}

		if (from.isEmpty()) {
			return "";
		}

		if (delimiter == null || delimiter.isEmpty()) {
			return from;
		}

		/* 复制‘source’串 */
		char[] chars = from.toCharArray();
		StringBuilder sbFrom = new StringBuilder("");
		sbFrom.append(chars);
		String copyFrom = sbFrom.toString();

		/* 根据副本中出现的‘delimiter’分隔符转换为目标‘驼峰’格式串 */
		StringBuilder sbTo = new StringBuilder("");
		if (copyFrom.contains(delimiter)) {
			String[] segments = copyFrom.split("\\" + delimiter);
			for (String segment : segments) {
				sbTo.append(segment.substring(0, 1).toUpperCase()).append(segment.substring(1).toLowerCase());
			} /* for */
		} else {
			/* 不包含‘delimiter’分隔符时, 将当前字符串直接转换为‘驼峰’格式 */
			sbTo.append(copyFrom.substring(0, 1).toUpperCase()).append(copyFrom.substring(1).toLowerCase());
		}

		return sbTo.toString();
	}

	/* 字符串替换 */
	public static String replace(String strSource, String strFrom, String strTo) {
		if (strSource == null) {
			return null;
		}
		int i = 0;
		if ((i = strSource.indexOf(strFrom, i)) >= 0) {
			char[] cSrc = strSource.toCharArray();
			char[] cTo = strTo.toCharArray();
			int len = strFrom.length();
			StringBuilder buf = new StringBuilder(cSrc.length);
			buf.append(cSrc, 0, i).append(cTo);
			i += len;
			int j = i;
			while ((i = strSource.indexOf(strFrom, i)) > 0) {
				buf.append(cSrc, j, i - j).append(cTo);
				i += len;
				j = i;
			}
			buf.append(cSrc, j, cSrc.length - j);
			return buf.toString();
		}
		return strSource;
	}

	/* 字符串合并操作,重复字段删除 */
	public static String merger(String str1, String str2, String split) {
		String str = "";
		TreeSet<String> ts = new TreeSet<>();

		StringTokenizer st = new StringTokenizer(str1, /* ";" */split);
		while (st.hasMoreTokens()) {
			ts.add(st.nextToken());
		}

		st = new StringTokenizer(str2, /* ";" */split);
		while (st.hasMoreTokens()) {
			ts.add(st.nextToken());
		}

		Iterator<String> it = ts.iterator();

		while (it.hasNext()) {
			str = str + it.next() + /* ";" */split;

		}
		if (!str.isEmpty()) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	/* 字符串合并操作,取重复字段 */
	public static String getRepeated(String str1, String str2, String split) {
		String str = "";
		String[] strs1 = str1.split(split);
		String[] strs2 = str2.split(split);
		for (int i = 0; i < strs1.length; i++) {
			for (int j = 0; j < strs2.length; j++) {
				if (strs1[i].equals(strs2[j])) {
					str = str + strs1[i];
					break;
				}
			}
		}
		if (str.endsWith(",")) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	/**
	 * 检查指定‘字符数组’中是否包含指定‘字符’
	 * 
	 * @param array
	 * @param s
	 * @return <boolean>
	 * @throws
	 */
	public static boolean contain(String[] array, String s) {
		if (array == null || array.length == 0) {
			return false;
		}
		if (s == null || s.isEmpty()) {
			return false;
		}

		for (String str : array) {
			if (str.equalsIgnoreCase(s)) {
				return true;
			}
		} /* for */

		return false;
	}

	/**
	 * 将List转化为StringBuffer
	 * 
	 * @param list
	 * @param delimiter
	 * @return <StringBuffer>
	 * @throws
	 */
	public static StringBuffer list2Strbuf(List<?> list, String delimiter) {
		if (list == null || list.isEmpty()) {
			return new StringBuffer("");
		}

		String defaultDelimiterChar = ",";
		String delimiterChar;
		if (delimiter != null && !delimiter.isEmpty()) {
			delimiterChar = delimiter;
		} else {
			delimiterChar = defaultDelimiterChar;
		}

		StringBuffer sb = new StringBuffer();
		String item;
		Iterator<?> it = list.iterator();
		while (it.hasNext()) {
			item = (String) it.next();
			sb.append(item).append(delimiterChar);
		} /* while */
		sb.deleteCharAt(sb.length() - 1);

		return sb;
	}

	/**
	 * 将String[]转化为JSONArray
	 * 
	 * @param str_array
	 * @param attr
	 * @return <JSONArray>
	 * @throws
	 */
	public static JSONArray strAry2JsonAry(String[] str_array, String attr) {
		if (str_array == null || str_array.length == 0) {
			return new JSONArray();
		}
		String[] strArray = str_array;

		JSONArray resultJsonAry = new JSONArray();
		JSONObject jsonItem = null;
		for (String strItem : strArray) {
			jsonItem = new JSONObject();
			jsonItem.put(attr, strItem);
			resultJsonAry.add(jsonItem);
		} /* for */

		return resultJsonAry;
	}

	/**
	 * 首字母大写
	 */
	public static String toUpperCaseFristOne(String str) {
		StringBuilder sb = new StringBuilder(str);
		return toUpperCaseFristOne(sb);
	}

	/**
	 * 首字母大写
	 */
	public static String toUpperCaseFristOne(StringBuilder sb) {
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}

	/**
	 * 首字母小写
	 */
	public static String toLowerCaseFristOne(String str) {
		StringBuilder sb = new StringBuilder(str);
		return toLowerCaseFristOne(sb);
	}

	/**
	 * 首字母小写
	 */
	public static String toLowerCaseFristOne(StringBuilder sb) {
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString();
	}

	/**
	 * 小驼峰标识
	 */
	public static String toLowerCamelCase(String str) {
		return toLowerCaseFristOne(toUpperCamelCase(str)).toString();
	}

	/**
	 * 大驼峰标识
	 */
	public static String toUpperCamelCase(String str) {
		StringBuilder sb = new StringBuilder();
		for (String buf : str.split("_"))
			sb.append(toUpperCaseFristOne(buf));
		return sb.toString();
	}

	/**
	 * toSnakeCaseWithPrefix("t", "ItemBasic") == "t_item_basic"
	 */
	private static final int aA = 'a' - 'A';

	public static String toSnakeCaseWithPrefix(String prf, String str) {
		int prflen = prf.length();
		int strlen = str.length();
		char[] buf = new char[strlen * 2 + prflen];
		prf.getChars(0, prflen, buf, prflen);
		int bufptr = prflen;
		for (int strptr = 0; strptr < strlen; strptr++) {
			char c = str.charAt(strptr);
			if (c >= 'A' && c <= 'Z') {
				buf[bufptr++] = '_';
				buf[bufptr++] = (char) (c + aA);
			} else
				buf[bufptr++] = c;
		}
		return new String(buf, 0, bufptr);
	}

	/**
	 * toSnakeCase("itemBasic") == "item_basic"
	 */
	public static String toSnakeCase(String str) {
		return toSnakeCaseWithPrefix("", str);
	}

	/**
	 * 拼接字符串
	 */
	public static String concat(Object... args) {
		return newBuilder(args).toString();
	}

	/**
	 * 拼接字符串并作为StringBuilder返回
	 */
	public static StringBuilder newBuilder(Object... args) {
		StringBuilder sb = new StringBuilder();
		append(sb, args);
		return sb;
	}

	/**
	 * 向StringBuilder中拼入字符串
	 */
	public static void append(StringBuilder sb, Object... args) {
		for (Object arg : args)
			sb.append(String.valueOf(arg));
	}

	public static String getDoubleString(Object value, Integer n) {
		return getDoubleString(value, (int) n);
	}

	/**
	 * 保留n位小数
	 */
	public static String getDoubleString(Object value, int n) {
		try {
			BigDecimal bigDecimal = new BigDecimal(String.valueOf(value)).setScale(n, BigDecimal.ROUND_HALF_UP);
			return String.valueOf(bigDecimal);
		} catch (Exception e) {
			return "";
		}
	}

	public static String getDoubleStringWithoutZero(Object value, int n) {
		return getDoubleString(value, n).replaceAll("0+?$", "").replaceAll("[\\.]$", "");
	}

	/**
	 * 将输入字符串转成合法的url，并且【不使用】“+”来替换空格
	 */
	public static String escapeUrl(String url) {
		if (url == null)
			return "";
		try {
			return URLEncoder.encode(url, "utf-8").replace("+", "%20").replace("%2F", "/");
		} catch (UnsupportedEncodingException e) {
			throw new AssertionError("utf-8 must be supported", e);
		}
	}

	/**
	 * 将输入字符串中的 \ | / _ % ? 以及空白字符转换为-
	 */
	public static String cleanUrl(String url) {
		if (url == null)
			return "";
		return url.replaceAll("[\\\\\\|\\/\\_\\-\\%\\?\\s]+", " ").trim().replaceAll(" +", "-").toLowerCase();
	}

	/**
	 * 对用于xml的文本进行转义，该方法假设输入内容可能已经经过处理，因此对于转义符“&”本身不进行转义
	 * 相当于escapeXmlText(xml, false, false)
	 */
	public static String escapeXmlText(String xml) {
		return escapeXmlText(xml, false, false);
	}

	/**
	 * 对用于xml的文本进行转义，该方法假设输入内容可能已经经过处理，因此对于转义符“&”本身不进行转义
	 * @param sp 是否将空格替换成&nbsp;
	 * @param br 是否将换行替换成br
	 */
	public static String escapeXmlText(String xml, boolean sp, boolean br) {
		xml = xml.replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
		if (sp)
			xml.replace(" ", "&nbsp;");
		if (br)
			xml.replace("\n", "<br>");
		return xml;
	}

	/**
	 * 判断Bigdecimal是否为0或空
	 */
	public static boolean isNullor0(BigDecimal bigDecimal) {
		return bigDecimal == null || bigDecimal.compareTo(BigDecimal.ZERO) == 0;
	}

	/**
	 * 将对象转换成Bigdecimal
	 * @param object 需转换的对象
	 * @param defaultValue 无法转换时给定一个默认值
	 */
	public static BigDecimal convertToBigDecimal(Object object, Object defaultValue) {
		try {
			return new BigDecimal(String.valueOf(object));
		} catch (Exception e) {
			try {
				return new BigDecimal(String.valueOf(defaultValue));
			} catch (Exception e2) {
				return null;
			}

		}
	}

	public static long parseLong(String source, long defaultValue) {
		try {
			return Long.parseLong(source);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * 保留两位小数
	 */
	public static String round2digit(Object num) {
		return getDoubleString(num, 2);
	}

	public static String delHtmlTags(String htmlContent) {
		if (htmlContent == null)
			return null;
		Matcher m_html = p_html.matcher(htmlContent);
		htmlContent = m_html.replaceAll(""); // 过滤html标签
		htmlContent = StringEscapeUtils.escapeHtml4(htmlContent);
		return htmlContent;
	}

	public static Optional<Double> parseDegree(String degree) {
		if (degree == null) {
			return Optional.empty();
		}
		try {
			return Optional.of(Double.parseDouble(degree));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

	public static int editDistance(String str1, String str2) {
		return editDistance(str1, str2, true);
	}

	public static int editDistance(String str1, String str2, boolean caseSensitive) {
		if (str1.length() == 0) {
			return str2.length();
		}
		if (str2.length() == 0) {
			return str1.length();
		}

		int s1len = str1.length(), s2len = str2.length();
		int matrix[][] = new int[s1len + 1][s2len + 1];
		// 初始化matrix
		for (int i = 0; i <= s1len; i++) {
			matrix[i][0] = i;
		}
		for (int j = 0; j <= s2len; j++) {
			matrix[0][j] = j;
		}

		int cost;
		for (int i = 1; i <= s1len; i++) {
			for (int j = 1; j <= s2len; j++) {
				boolean similar;
				if (caseSensitive) {
					similar = str1.charAt(i - 1) == str2.charAt(j - 1);
				} else {
					similar = Character.toLowerCase(str1.charAt(i - 1)) == Character.toLowerCase(str2.charAt(j - 1));
				}
				if (similar) {
					cost = 0;
				} else {
					cost = 1;
				}
				matrix[i][j] = Math.min(matrix[i - 1][j] + 1,
						Math.min(matrix[i][j - 1] + 1, matrix[i - 1][j - 1] + cost));
			}
		}
		return matrix[s1len][s2len];
	}

	public static double stringSimilarity(String str1, String str2) {
		return stringSimilarity(str1, str2, true);
	}

	public static double stringSimilarity(String str1, String str2, boolean caseSensitive) {
		return 1 - ((double) editDistance(str1, str2, caseSensitive) / Math.max(str1.length(), str2.length()));
	}

}
