package com.liker.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 项目名称：bbw
 * 类名称：StringUtils
 * 类描述：字符工具类
 * 创建人：lanyongbin
 * 创建时间：2013-9-2上午11:13:10
 *
 * @version
 */

public class StringUtils extends org.apache.commons.lang.StringUtils {
	
	
	/**
	 * 去除字符串中的空格、回车、换行符、制表符
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
	
	//不能全是相同的数字或者字母（如：000000、111111、aaaaaa） 全部相同返回true
	public static boolean isEqualStr(String numOrStr){
		boolean flag = true;
		char str = numOrStr.charAt(0);
		for (int i = 0; i < numOrStr.length(); i++) {
			if (str != numOrStr.charAt(i)) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	//不能是连续的数字--递增（如：123456、12345678）连续数字返回true
	public static boolean isOrderNumericAsc(String numOrStr){
		boolean flag = true;//如果全是连续数字返回true
		boolean isNumeric = true;//如果全是数字返回true
		for (int i = 0; i < numOrStr.length(); i++) {
			if (!Character.isDigit(numOrStr.charAt(i))) {
				isNumeric = false;
				break;
			}
		}
		if (isNumeric) {//如果全是数字则执行是否连续数字判断
			for (int i = 0; i < numOrStr.length(); i++) {
				if (i > 0) {//判断如123456
					int num = Integer.parseInt(numOrStr.charAt(i)+"");
					int num_ = Integer.parseInt(numOrStr.charAt(i-1)+"")+1;
					if (num != num_) {
						flag = false;
						break;
					}
				}
			}
		} else {
			flag = false;
		}
		return flag;
	}
	//不能是连续的数字--递减（如：987654、876543）连续数字返回true
	public static boolean isOrderNumericDesc(String numOrStr){
		boolean flag = true;//如果全是连续数字返回true
		boolean isNumeric = true;//如果全是数字返回true
		for (int i = 0; i < numOrStr.length(); i++) {
			if (!Character.isDigit(numOrStr.charAt(i))) {
				isNumeric = false;
				break;
			}
		}
		if (isNumeric) {//如果全是数字则执行是否连续数字判断
			for (int i = 0; i < numOrStr.length(); i++) {
				if (i > 0) {//判断如654321
					int num = Integer.parseInt(numOrStr.charAt(i)+"");
					int num_ = Integer.parseInt(numOrStr.charAt(i-1)+"")-1;
					if (num != num_) {
						flag = false;
						break;
					}
				}
			}
		} else {
			flag = false;
		}
		return flag;
	}
	
	public static String leftPad(int value, int size, char padChar) {
		String str = String.valueOf(value);
		str = leftPad(str, size, padChar);
		return str;
	}

	public static String rightPad(int value, int size, char padChar) {
		String str = String.valueOf(value);
		str = rightPad(str, size, padChar);
		return str;
	}

	public static String addQuote(String str) {
		if (!"NULL".equalsIgnoreCase(str)) {
			str = "'" + encodeSingleQuotedString(str) + "'";
		}
		return str;
	}

	public static String leftQuote(String str) {
		str = "'" + encodeSingleQuotedString(str);

		return str;
	}

	public static String rightQuote(String str) {
		str = encodeSingleQuotedString(str) + "'";

		return str;
	}

	public static String encodeSingleQuotedString(String str) {
		if (isNotEmpty(str)) {
			StringBuffer sb = new StringBuffer(64);
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (c == '\'')
					sb.append("''");
				else
					sb.append(c);
			}
			return sb.toString();
		}

		return str;
	}

	public static String formatDouble(double value) {
		String str = "0.0";

		if (value != 0.0D) {
			DecimalFormat nf = new DecimalFormat("#.##");

			nf.setParseIntegerOnly(false);
			nf.setDecimalSeparatorAlwaysShown(false);

			str = nf.format(value);
		}

		return str;
	}

	public static String formatDouble(double value, int digitNum) {
		String str = "0.0";

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < digitNum; i++) {
			sb.append("0");
		}
		DecimalFormat nf = new DecimalFormat("#." + sb.toString());
		nf.setParseIntegerOnly(false);
		nf.setDecimalSeparatorAlwaysShown(false);
		str = nf.format(value);
		if (isEmpty(str.split("\\.")[0]))
			str = "0" + str;
		return str;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[] tokenize(String str, String delimiter) {
		ArrayList v = new ArrayList();

		StringTokenizer t = new StringTokenizer(str, delimiter);

		while (t.hasMoreTokens()) {
			String s = t.nextToken();
			if (isNotEmpty(s)) {
				v.add(s.trim());
			}
		}

		String[] pro = new String[v.size()];
		for (int i = 0; i < pro.length; i++) {
			pro[i] = ((String) (String) v.get(i));
		}
		return pro;
	}

	public static void byte2hex(byte b, StringBuffer buf) {
		char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		int high = (b & 0xF0) >> 4;
		int low = b & 0xF;

		buf.append(hexChars[high]);
		buf.append(hexChars[low]);
	}

	public static String toHexString(byte[] block) {
		StringBuffer buf = new StringBuffer(64);

		int len = block.length;

		for (int i = 0; i < len; i++) {
			byte2hex(block[i], buf);
		}

		return buf.toString();
	}

	public static String addFlagQuote(String str) {
		StringBuffer sbFlag = new StringBuffer(16);

		String[] flags = tokenize(str, ",");

		int count = flags.length;
		for (int i = 0; i < count; i++) {
			if (i != 0) {
				sbFlag.append(",");
			}

			sbFlag.append("'" + flags[i] + "'");
		}

		return sbFlag.toString();
	}

	public static String getData(String resouce, String label) {
		String result = "";
		String labelB = "";
		String labelE = "";

		int site1 = 0;
		int site2 = 0;

		if ((resouce == null) || (label == null)) {
			return result;
		}

		resouce = resouce.trim();
		labelB = "<" + label + ">";
		labelE = "</" + label + ">";

		site1 = resouce.indexOf(labelB) + labelB.length();
		site2 = resouce.indexOf(labelE);

		if ((site1 < 0) || (site2 < 0)) {
			return "";
		}

		result = resouce.substring(site1, site2);

		return result;
	}

	public static String convertToUTF8(String str) {
		String result = str;
//		String charCodeOld = "GBK";
		String charCodeNew = "UTF-8";

		if (isNotEmpty(result)) {
			try {
				byte[] bytes = result.getBytes();
				result = new String(bytes, charCodeNew);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return result;
	}
	
	public static String convertToGB(String str) {
		String result = str;
		String charCodeOld = "8859_1";
		String charCodeNew = "GBK";

		if (isNotEmpty(result)) {
			try {
				byte[] bytes = result.getBytes(charCodeOld);
				result = new String(bytes, charCodeNew);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return result;
	}

	public static String convertToISO(String str) {
		String result = str;
		String charCodeOld = "GBK";
		String charCodeNew = "8859_1";

		if (isNotEmpty(result)) {
			try {
				byte[] bytes = result.getBytes(charCodeOld);
				result = new String(bytes, charCodeNew);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static String fomatHtmlText(String reCeiv) {
		String cMesg = "";
		String sMesg = "";

		for (int ii = 0; ii < reCeiv.length(); ii++) {
			cMesg = reCeiv.substring(ii, ii + 1);
			if ("\n".compareTo(cMesg) == 0) {
				sMesg = sMesg + "<br>";
			} else if (" ".compareTo(cMesg) == 0) {
				sMesg = sMesg + "&nbsp";
			} else {
				sMesg = sMesg + cMesg;
			}
		}
		return sMesg;
	}

	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ((c >= 0) && (c <= 'ÿ')) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}

		return sb.toString();
	}

	public static String getFormatDate(String s) {
		String s_date = "";
		if (s.trim().length() == 14)
			s_date = s.substring(0, 4) + "-" + s.substring(4, 6) + "-"
					+ s.substring(6, 8);
		else
			s_date = s;
		return s_date;
	}

	public static String getPriorDay(int offset) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

		Calendar theday = Calendar.getInstance();
		theday.add(5, offset);

		df.applyPattern("yyyyMMdd");
		return df.format(theday.getTime());
	}

	public static String trim(String s) {
		if ((s == null) || ("".equalsIgnoreCase(s))
				|| ("null".equalsIgnoreCase(s))) {
			return "";
		}
		return s.trim();
	}

	public static String getStrSysYear() {
		Calendar getTime = Calendar.getInstance(Locale.CHINA);
		return Integer.toString(getTime.get(1));
	}

	public static int getIntSysYear() {
		Calendar getTime = Calendar.getInstance(Locale.CHINA);
		return getTime.get(1);
	}

	public static int getIntSysMonth() {
		Calendar getTime = Calendar.getInstance(Locale.CHINA);
		return getTime.get(2) + 1;
	}

	public static String getStrSysDay() {
		Calendar getTime = Calendar.getInstance(Locale.CHINA);
		return Integer.toString(getTime.get(5));
	}

	public static int getIntSysDay() {
		Calendar getTime = Calendar.getInstance(Locale.CHINA);
		return getTime.get(5);
	}

	public static String getFileModifyTime(String fileName) {
		File fn = new File(fileName);

		Long.toString(fn.lastModified());
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar theday = Calendar.getInstance();
		theday.setTimeInMillis(fn.lastModified());
		df.applyPattern("yyyy/MM/dd HH:mm:ss");
		return df.format(theday.getTime());
	}

	public static String getLikeString(String colName, String colValue) {
		String out = "";
		out = colName + " Like '%" + colValue + "%'";
		return out;
	}

	public static String getUpperInteger(int in) {
		String out = "";
		int tmp = in;
		while (true) {
			int tmp1 = tmp % 10;
			switch (tmp1) {
			case 1:
				out = "一" + out;
				break;
			case 2:
				out = "二" + out;
				break;
			case 3:
				out = "三" + out;
				break;
			case 4:
				out = "四" + out;
				break;
			case 5:
				out = "五" + out;
				break;
			case 6:
				out = "六" + out;
				break;
			case 7:
				out = "七" + out;
				break;
			case 8:
				out = "八" + out;
				break;
			case 9:
				out = "九" + out;
			}
			tmp /= 10;
			if (tmp == 0)
				break;
		}
		return out;
	}

	public static String changeNullToEmpty(String str) {
		if (isEmpty(str)) {
			return "";
		}

		return str;
	}

	public static String convertURL(String URL) {
		if (isEmpty(URL)) {
			return "";
		}

		return URL.replace('\\', '/');
	}

	public static String byteToString(byte b) {
		byte maskHigh = -16;
		byte maskLow = 15;

		byte high = (byte) ((b & maskHigh) >> 4);
		byte low = (byte) (b & maskLow);

		StringBuffer buf = new StringBuffer();
		buf.append(findHex(high));
		buf.append(findHex(low));

		return buf.toString();
	}

	private static char findHex(byte b) {
		int t = new Byte(b).intValue();
		t = t < 0 ? t + 16 : t;

		if ((t >= 0) && (t <= 9)) {
			return (char) (t + 48);
		}

		return (char) (t - 10 + 65);
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getdate(){
		return new Date();
	}
	/**
	 * 根据当前时间获得17位的流水号yyyymmddHHMMssLLL
	 * @param server
	 * @return
	 */
	public static String getSerialno17() {
		return String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL", StringUtils.getdate());
	}
	public static String getSerialno14() {
		return String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", StringUtils.getdate());
	}
	
	public static int stringToByte(String in, byte[] b) {
		if (b.length < in.length() / 2) {
			return 0;
		}

		int j = 0;
		StringBuffer buf = new StringBuffer(2);
		for (int i = 0; i < in.length(); j++) {
			buf.insert(0, in.charAt(i));
			buf.insert(1, in.charAt(i + 1));
			int t = Integer.parseInt(buf.toString(), 16);

			b[j] = ((byte) t);
			i++;
			buf.delete(0, 2);

			i++;
		}

		return j;
	}

	public static byte[] hex2Bytes(String hexString) {
		if (hexString == null) {
			return null;
		}

		if (hexString.length() % 2 != 0) {
			hexString = '0' + hexString;
		}

		byte[] result = new byte[hexString.length() / 2];

		for (int i = 0; i < result.length; i++) {
			result[i] = ((byte) Integer.parseInt(
					hexString.substring(i * 2, (i + 1) * 2), 16));
		}

		return result;
	}

	public static String bytes2Hex(byte[] bytes) {
		StringBuffer result = new StringBuffer("");
		if (bytes == null) {
			return "";
		}

		for (int i = 0; i < bytes.length; i++) {
			result.append(padding2Head(Integer.toHexString(bytes[i] & 0xFF),
					'0', 2));
		}
		return result.toString();
	}

	public static String padding2Head(String s, char ch, int destLength) {
		StringBuffer str = null;
		if (destLength < 0) {
			return "";
		}
		if (s == null) {
			str = new StringBuffer();
			for (int i = 0; i < destLength; i++) {
				str.append(ch);
			}
		} else {
			if (s.length() > destLength) {
				return "";
			}

			str = new StringBuffer();
			for (int i = 0; i < destLength - s.length(); i++) {
				str.append(ch);
			}
			str.append(s);
		}

		return str.toString();
	}
	
	/**
	 * 通过HttpServletRequest返回IP地址
	 * @param request HttpServletRequest
	 * @return ip String
	 * @throws Exception
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 通过IP地址获取MAC地址
	 * @param ip String,127.0.0.1格式
	 * @return mac String
	 * @throws Exception
	 */
	public static String getMACAddress(String ip) {
		String line = "";
		String macAddress = "";
		final String MAC_ADDRESS_PREFIX = "MAC Address = ";
		final String LOOPBACK_ADDRESS = "127.0.0.1";
		try {
			//如果为127.0.0.1,则获取本地MAC地址。
			if (LOOPBACK_ADDRESS.equals(ip)) {
				InetAddress inetAddress = InetAddress.getLocalHost();
				//貌似此方法需要JDK1.6。
				byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
				//下面代码是把mac地址拼装成String
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < mac.length; i++) {
					if (i != 0) {
						sb.append("-");
					}
					//mac[i] & 0xFF 是为了把byte转化为正整数
					String s = Integer.toHexString(mac[i] & 0xFF);
					sb.append(s.length() == 1 ? 0 + s : s);
				}
				//把字符串所有小写字母改为大写成为正规的mac地址并返回
				macAddress = sb.toString().trim().toUpperCase();
				return macAddress;
			}
			//获取非本地IP的MAC地址

			String os = System.getProperty("os.name");
			Process p;
			if (os != null && os.startsWith("Windows")) {
				p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			} else {
				p = Runtime.getRuntime().exec("nmblookup -A " + ip);
			}
			InputStreamReader isr = new InputStreamReader(p.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				if (line != null) {
					int index = line.indexOf(MAC_ADDRESS_PREFIX);
					if (index != -1) {
						macAddress = line.substring(index + MAC_ADDRESS_PREFIX.length()).trim().toUpperCase();
					}
				}
			}
			br.close();
		} catch (IOException e) {
		}
		return macAddress;
	}
	public static void main(String[] args) throws Exception {
		System.out.println(getSerialno17());
		System.out.println(getSerialno17().length());
	}
}