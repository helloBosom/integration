package fun.peri.arithmetic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	/**
	 * 16进制字符数组
	 */
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };
	protected static MessageDigest messagedigest = null;

	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.err.println(MD5Util.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
			e.printStackTrace();
		}
	}

	/**
	 * 0xf0 :为16进制数转成10进制为 240 转成2进制为 11110000
	 * 
	 * 解释：(b[i]&0xf0)>>>4 -->将b[i]的低4位清零后， (将b[i]转成2进制 再 & 0xf0 既 & 11110000 ;
	 * 例如:01010101 & 11110000 结果是 01010000) 再无符号的右移4位 既取出高4位 作为数组hexChar的下标
	 * 拿到对应的16进制符
	 * 
	 * @param bt
	 * @param stringbuffer
	 */
	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	/**
	 * 将字节数组的内容转为16进制数
	 * 
	 * @param bytes
	 *            字节数组
	 * @return 16进制数字符串
	 */
	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	/**
	 * 将字节数组的内容转为16进制数
	 * 
	 * @param bytes
	 *            bytes 字节数组
	 * @param m
	 *            数组起始索引
	 * @param n
	 *            数组结束索引
	 * @return 16进制数字符串
	 */
	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	/**
	 * 取得文件的MD5码
	 * 
	 * @param file
	 *            文件
	 * @return MD5码
	 * @throws IOException
	 */
	public static String getFileMD5String(File file) {
		FileInputStream in = null;
		FileChannel ch = null;
		String tempMD5 = "";
		try {
			in = new FileInputStream(file);
			ch = in.getChannel();
			MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			messagedigest.update(byteBuffer);
			tempMD5 = bufferToHex(messagedigest.digest());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ch.close();
				ch = null;

				in.close();
				in = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return tempMD5;
	}

	/**
	 * 取得文件的MD5码
	 * 
	 * @param file
	 *            文件
	 * @return MD5码
	 * @throws IOException
	 */
	public static String getFileMD5String2(File file) {
		FileInputStream in = null;
		String tempMD5 = "";
		try {
			in = new FileInputStream(file);
			byte[] cache = new byte[1048576];
			int len = 0;
			while ((len = in.read(cache)) > 0) {
				messagedigest.update(cache, 0, len);
			}
			tempMD5 = bufferToHex(messagedigest.digest());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				in = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return tempMD5;
	}

	/**
	 * 取得字符串的MD5码
	 * 
	 * @param s
	 * @return
	 */
	public static String getMD5String(String s) {
		return getMD5String(s.getBytes());
	}

	/**
	 * 取得字节数组的MD5码
	 * 
	 * @param bytes
	 * @return
	 */
	public static String getMD5String(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

}
