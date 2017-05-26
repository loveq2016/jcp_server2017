package com.jucaipen.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class ImgUtils {

	private static final String codeChar = "abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
    private static String randomCode;
	public String getRandomCode() {
		return randomCode;
	}

	/**
	 * 将网络图片进行Base64位编码
	 * 
	 * @param imgUrl
	 *            图片URL
	 * @param format
	 *            图片格式
	 * @return Base64图片字符串信息
	 */
	public static String encodeImgageToBase64(URL imageUrl, String format) {
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
	 * @param imgUrl
	 *            本地图片URL
	 * @param format
	 *            图片格式
	 * @return Base64图片字符串信息
	 */
	public static String encodeImgageToBase64(File imageFile, String format) {
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
	 * 
	 * @param base64
	 *            图片base64编码信息
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

	public static void main(String[] args) throws IOException {
		BufferedImage image = createImage(80, 50, Color.PINK);
		ImageIO.write(image, "jpeg", new File("d://code.jpeg"));
	}

	/**
	 * @param width
	 * @param height
	 * @param bgColor
	 * @return 获取验证码图片
	 */
	public static BufferedImage createImage(int width, int height, Color bgColor) {
		BufferedImage bImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = bImage.createGraphics();
		graphics.setColor(bgColor);
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(getRandomColor(255, 243, 155));
		graphics.setFont(new Font("宋体", Font.BOLD, 22));
		randomCode=createRandonCode(4);
		graphics.drawString(randomCode, 15, 22);
		return bImage;
	}

	/**
	 * @param c
	 * 
	 *            画干扰线
	 */
	public static void drawLine(Color c, Graphics2D g, int num, int width,
			int height) {
		g.setColor(c);
		g.drawLine(0, 0, width, height);
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 * @return 获取随机颜色
	 */
	public static Color getRandomColor(int red, int green, int blue) {
        return new Color(red, green, blue);
	}

	/**
	 * @param num
	 * @return 获取验证码文字
	 */
	public static String createRandonCode(int num) {
		StringBuilder builder = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			builder.append(codeChar.charAt(random.nextInt(codeChar.length())));
		}
		return builder.toString();
	}

}
