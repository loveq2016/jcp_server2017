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

public class GetCode extends HttpServlet {
	private static final long serialVersionUID = -5050808368233646244L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html/image");
		response.addHeader( "Cache-Control", "no-cache" );  
		BufferedImage image = ImgUtils.createImage(80, 30, Color.PINK);
		HttpSession session = request.getSession();
		session.setAttribute("randomCode", new ImgUtils().getRandomCode());
		ImageIO.write(image, "jpeg", response.getOutputStream());
	}
}
