package com.jucaipen.utils;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
public class ImgUtils {
	/**
	 * 将网络图片进行Base64位编码
	 * @param imgUrl  图片URL
	 * @param format  图片格式
	 * @return  Base64图片字符串信息
	 */
	public static String encodeImgageToBase64(URL imageUrl,String format) {
		ByteArrayOutputStream outputStream = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(imageUrl);
			outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, format, outputStream);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(outputStream.toByteArray());
	}

	/**
	 * 将本地图片进行Base64位编码
	 * 
	 * @param imgUrl   本地图片URL
	 * @param format 图片格式
	 * @return  Base64图片字符串信息
	 */
	public static String encodeImgageToBase64(File imageFile,String format) {
		ByteArrayOutputStream outputStream = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(imageFile);
			outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, format, outputStream);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(outputStream.toByteArray());
	}

	/**
	 * 将Base64位编码的图片进行解码，并保存到指定目录
	 * @param base64  图片base64编码信息
	 */
	public static void decodeBase64ToImage(String base64, String path,
			String imgName) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			FileOutputStream write = new FileOutputStream(new File(path
					+ imgName));
			byte[] decoderBytes = decoder.decodeBuffer(base64);
			write.write(decoderBytes);
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
