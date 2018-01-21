package com.webapp.ediya.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CommonUtil {
	private static SimpleDateFormat formatdateWithTime = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");

	public static String getTimeNow() {
		return formatdateWithTime.format(new Date());
	}

	public static String logTimePrefix() {
		return "[" + CommonUtil.getTimeNow() + "] ";
	}

	public static String join(String inputArray[], String delimiter) {
		StringBuffer buf = null;
		if (inputArray == null)
			return null;

		for (String value : inputArray) {
			if (buf == null) {
				buf = new StringBuffer();
				buf.append(value);
			} else {
				buf.append(delimiter);
				buf.append(value);
			}
		}

		return (buf == null) ? null : buf.toString();
	}

	public static String join(List<String> inputArray, String delimiter) {
		StringBuffer buf = null;
		if (inputArray == null)
			return null;

		for (String value : inputArray) {
			if (buf == null) {
				buf = new StringBuffer();
				buf.append(value);
			} else {
				buf.append(delimiter);
				buf.append(value);
			}
		}

		return (buf == null) ? null : buf.toString();
	}
	
	/**
	 * Verify if file exists
	 * 
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public static boolean fileExists(String dir, String fileName) {
		return new File(dir, fileName).exists();
	}

	/**
	 * This method is used to zip an input file
	 * 
	 * @param inputFile
	 */
	public static boolean gzipIt(String inputFile) {
		String outputFile;
		byte[] buffer = new byte[1024];
		boolean writeSuccess = false;
		try {
			outputFile = inputFile + ".gz";
			GZIPOutputStream gzos = new GZIPOutputStream(new FileOutputStream(outputFile));

			FileInputStream in = new FileInputStream(inputFile);

			int len;
			while ((len = in.read(buffer)) > 0) {
				gzos.write(buffer, 0, len);
			}

			in.close();

			gzos.finish();
			gzos.close();
			writeSuccess = true;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return writeSuccess;
	}

	/**
	 * This method is used to unzip a gz file
	 */
	public static boolean gunzipIt(String inputFile) {
		String outputFile;
		byte[] buffer = new byte[1024];
		boolean gunzipSuccess = false;
		try {
			outputFile = inputFile.replace(".gz","");
			
			GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(inputFile));
			FileOutputStream out = new FileOutputStream(outputFile);

			int len;
			while ((len = gzis.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}

			gzis.close();
			out.close();

			gunzipSuccess = true;

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return gunzipSuccess;
	}

	/**
	 * Generate MD5 hash of the token
	 * 
	 * @param rawToken
	 * @return
	 */
	public static String generateMd5Hash(String rawToken) {
		byte[] mdbytes;
		MessageDigest md;
		StringBuffer hexString = new StringBuffer();
		;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(rawToken.getBytes());
			mdbytes = md.digest();

			// convert the byte to hex format method 2
			for (int i = 0; i < mdbytes.length; i++) {
				String hex = Integer.toHexString(0xff & mdbytes[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hexString.toString();
	}

}