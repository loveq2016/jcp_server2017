package com.jucaipen.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;

public class FileUtils {
	/**
	 * 创建文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean createFile(String pathName) {
		try {
			File file=new File(pathName);
			if (!file.exists()) {
				file.createNewFile();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 读TXT文件内容
	 * 
	 * @param fileName
	 * @return
	 */
	public static String readTxtFile(File fileName) throws Exception {
		String result = "";
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			try {
				String read = null;
				while ((read = bufferedReader.readLine()) != null) {
					result = result + read + "<br/>";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (fileReader != null) {
				fileReader.close();
			}
		}
		return result;
	}

	/**
	 * @param content
	 * @param fileName
	 * @return
	 * @throws Exception
	 * 
	 *    清空写入
	 */
	public static boolean writeTxtFile(String content, File fileName)
			throws Exception {
		RandomAccessFile mm = null;
		boolean flag = false;
		FileOutputStream o = null;
		try {
			o = new FileOutputStream(fileName);
			o.write(content.getBytes("GBK"));
			o.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (mm != null) {
				mm.close();
			}
		}
		return flag;
	}

	/**
	 * @param filePath
	 * @param content
	 *    追加写入
	 */
	public static void contentToTxt(String filePath, String content) {
		String str = new String(); // 原有txt内容
		String s1 = new String();// 内容更新
		try {
			File f = new File(filePath);
			if (f.exists()) {
				//System.out.print("文件存在");
			} else {
				System.out.print("文件不存在");
				f.createNewFile();// 不存在则创建
			}
			BufferedReader input = new BufferedReader(new FileReader(f));

			while ((str = input.readLine()) != null) {
				s1 += str + "\n";
			}
			input.close();
			s1 += content;
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(s1);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	

}