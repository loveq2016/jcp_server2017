package com.jucaipen.base;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jucaipen.utils.ImgUtils;
import com.jucaipen.utils.QrCodeUtils;
public class GetCode extends HttpServlet {
	private static final long serialVersionUID = -5050808368233646244L;
	private String loginUrl="http://121.40.227.121:8080/AccumulateWealth/login.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/jpeg");
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");  
		String type = request.getParameter("type");
		if("1".equals(type)){
			BufferedImage image = ImgUtils.createImage(80, 30, Color.GRAY);
			HttpSession session = request.getSession();
			session.setAttribute("randomCode", new ImgUtils().getRandomCode());
			ImageIO.write(image, "jpeg", response.getOutputStream());
		}else{
			BufferedImage image=QrCodeUtils.encoderQRCode(loginUrl);
			ImageIO.write(image, "jpeg", response.getOutputStream());
		}
	}
}
