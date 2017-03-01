package com.ailk.eaap.op2.sso.main.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.asiainfo.foundation.common.ExceptionCommon;
import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.linkage.rainbow.ui.struts.BaseAction;
import com.opensymphony.xwork2.ActionSupport;

public class MakeCodeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLog(MakeCodeAction.class);
	@Override
	public String execute() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest request=ServletActionContext.getRequest();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
		int width = 60;
		int height = 20;
		BufferedImage image = new BufferedImage(width,height,1);
		Graphics graphics = image.getGraphics();
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		graphics.setColor(getColor(200,250));
		graphics.fillRect(0, 0, width, height);
		graphics.setFont(new Font("Times New Roman", 0, 18));
		graphics.setColor(new Color(33,66,99));
		graphics.drawRect(0, 0, width-1, height-1);
		graphics.setColor(getColor(160,200));
		for(int i=0;i<155;i++){
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			graphics.drawLine(x, y, x + xl, y + yl);
		}
		StringBuffer sRand = new StringBuffer();
		for (int i = 0; i < 4; i++)
		{
			String rand = String.valueOf(random.nextInt(10));
			sRand.append(rand);
			int c = random.nextInt(110);
			graphics.setColor(new Color(20 + c, 20 + c, 20 + c));
			graphics.drawString(rand, 13 * i + 6, 16);
		}
		getSession().setAttribute("code", sRand.toString());
		graphics.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
		return null;
	}
	private Color getColor(int s,int e){
		if(s>255){
			s=255;
		}
		if(e>255){
			e=255;
		}
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
			int r = s + random.nextInt(e-s);
			int g = s + random.nextInt(e-s);
			int b = s + random.nextInt(e-s);
			return new Color(r,g,b);
		} catch (NoSuchAlgorithmException e1) {
			log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e1.getMessage(),null));
		}
		return null;
	}

}
