package com.jucaipen.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import com.jucaipen.model.ApkInfo;
import com.jucaipen.service.ApkInfoServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 *      ����APK�ļ�
 */
@SuppressWarnings("serial")
public class DownFile extends HttpServlet {
	private String rootPath;
	private String loadPath;
	private String fm;
	private String result;
    private	String fileName;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		rootPath = "D:/apkInfo/apk/";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		String typeId = request.getParameter("downloadType");
		if(StringUtil.isNotNull(typeId)&&StringUtil.isInteger(typeId)){
			int type=Integer.parseInt(typeId);
			if(type==0){
				ApkInfo info = ApkInfoServer.findLastApkInfo(1);
				fileName=info.getApkPath();
			}else{
				fileName = request.getParameter("fileName");
			}
		}else{
			ApkInfo info = ApkInfoServer.findLastApkInfo(1);
			fileName=info.getApkPath();
		}
		if (StringUtil.isNotNull(fileName)) {
			String adr=request.getLocalAddr();
			if(adr.equals("121.40.227.121")){
				fm = fileName;
			}else{
				fm = new String(fileName.getBytes("ISO-8859-1"),"utf-8");
			}
			loadPath = rootPath + fm;
			File apkFile = new File(loadPath);
			if (apkFile.exists()) {
				downLoadApk(response,request,apkFile);
			} else {
				result=JsonUtil.getRetMsg(3, "�ļ�������");
			}
		} else {
			PrintWriter out = response.getWriter();
			result = JsonUtil.getRetMsg(1, "�����ļ�������Ϊ��");
			out.write(result);
			out.flush();
			out.close();
		}
	}

	private void downLoadApk(HttpServletResponse response, HttpServletRequest request, File apkFile) {
		FileInputStream in = null;
		// ������Ӧͷ��������������ظ��ļ�
					try {
						response.setHeader("content-disposition", "attachment;filename="
								+ URLEncoder.encode(fm, "UTF-8"));
						response.setHeader("Content_Type", "apk");
						// ��ȡҪ���ص��ļ������浽�ļ�������
						in = new FileInputStream(loadPath);
						IOUtils.copy(in, response.getOutputStream());
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
		/*try {
			// ������Ӧͷ��������������ظ��ļ�
			response.setHeader("content-disposition", "attachment;filename="
					+ URLEncoder.encode(fm, "UTF-8"));
			// ��ȡҪ���ص��ļ������浽�ļ�������
			FileInputStream in = new FileInputStream(loadPath);
			bis=new BufferedInputStream(in);
			// ���������
			OutputStream out = response.getOutputStream();
			bos=new BufferedOutputStream(out);
			// ����������
			byte buffer[] = new byte[1024*1024*5];
			int len = 0;
			// ѭ�����������е����ݶ�ȡ������������
			while ((len = bis.read(buffer)) > 0) {
				// ��������������ݵ��������ʵ���ļ�����
				bos.write(buffer, 0, len);
				bos.flush();
			}
			// �ر��ļ�������
			bis.close();
			bos.close();
			// �ر������
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("err:"+e.getMessage());
		}
*/
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
