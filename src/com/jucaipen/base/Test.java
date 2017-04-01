package com.jucaipen.base;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.utils.ImgUtils;
/**
 * @author Administrator
 */
@SuppressWarnings("serial")
public class Test extends HttpServlet {
	private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	private String rootPath="http://img.jucaipen.com/jucaipenStudy/2016/4/20/2016420163158.jpg";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		OutputStream os=response.getOutputStream();
		DataOutputStream dos=new DataOutputStream(os);
		BufferedImage bufferedImage = ImageIO.read(new URL(rootPath));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String[] urlStr = rootPath.split("\\.");
		ImageIO.write(bufferedImage, urlStr[urlStr.length-1], outputStream);
		byte [] bs=outputStream.toByteArray();
		dos.write(bs);
		dos.flush();
		dos.close();
	}
	
	
	public void localFile(){
		String loaclUrl="";
		String format="png";
		File imageFile=new File(loaclUrl);
		if(imageFile.exists()){
			String baseUrl=ImgUtils.encodeImgageToBase64(imageFile, format);
			 ImgUtils.decodeBase64ToImage(baseUrl, "D:\\项目\\", "my.png");
		}
	}
	
	public static void main(String[] args) {
		try {
			String url="http://img.jucaipen.com/jucaipenStudy/2016/4/20/2016420163158.jpg";
			String urlStr[]=url.split("\\.");
			String format=urlStr[urlStr.length-1];
			String baseUrl=ImgUtils.encodeImgageToBase64(new URL(url),format);
		    System.out.println("baseUrl:"+baseUrl);
		     ImgUtils.decodeBase64ToImage(baseUrl, "D:\\项目\\",sdf.format(new Date())+"."+format );
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
