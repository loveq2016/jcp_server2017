package com.jucaipen.base;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.jucaipen.model.ApkInfo;
import com.jucaipen.service.ApkInfoServer;
import com.jucaipen.utils.StringUtil;
/**
 * @author YLF
 * 
 *         文件上传
 *         versionName versionCode file
 * 
 */
@SuppressWarnings("serial")
public class UploadApk extends HttpServlet {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * APK文件保存路径
	 */
	private String rootPath;
	/**
	 * 上传APK参数
	 */
	private Map<String, String> param = new HashMap<String, String>();
	private ApkInfo info;
	private int maxId;
	private String uuId;
	private String savePath;
	private String versionName;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		rootPath = "D:/apkInfo/apk/";
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		uuId = UUID.randomUUID().toString();
		savePath = rootPath + uuId;
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		DiskFileItemFactory dif = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(dif);
		try {
			List<FileItem> list = sfu.parseRequest(request);
			Iterator<FileItem> iter = list.iterator();
			while (iter.hasNext()) {
				FileItem fi = iter.next();
				if (fi.isFormField()) {
					String key = fi.getFieldName();
					String values = fi.getString("UTF-8");
					param.put(key, values);
				} else {
					if (fi.getFieldName() != null && !fi.getName().equals("")) {
						File tempFile = new File(fi.getName());
						File saveFile = new File(savePath, tempFile.getName());
						fi.write(saveFile);
						querryMaxId();
						if (maxId > 0) {
							// 更新apk数据库数据
							String filePath = uuId + "/" + tempFile.getName();
							createApkDate(param, filePath);
							if (info != null) {
								updateApkInfo(info);
								//pushUpdateInfo(filePath);
								out.print("文件上传处理成功");
							} else {
								out.print("文件处理失败");
							}
						} else {
							out.print("文件处理失败");
						}
					} else {
						out.print("没有选择文件");
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print("文件上传失败");
		}
		out.flush();
		out.close();
	}

	/**
	 * 获取APK信息最大的id
	 */
	private void querryMaxId() {
		maxId = ApkInfoServer.querryMaxId();

	}

	/**
	 * @param info
	 * 
	 *            上传APK文件
	 */
	private void updateApkInfo(ApkInfo info) {
		ApkInfoServer.insertApkInfo(info);
	}

	/**
	 * @param param
	 * @param string
	 *            构建APK对象
	 */
	private void createApkDate(Map<String, String> param, String path) {
		if (param.size() > 0) {
			versionName = param.get("versionName");
			String versionCode = param.get("versionCode");
			info = new ApkInfo();
			info.setId(++maxId);
			info.setVsionName(versionName);
			info.setApkPath(path);
			info.setPkgName("com.example.androidnetwork");
			String date = sdf.format(new Date());
			info.setUpdateDate(date);
			if (StringUtil.isInteger(versionCode)) {
				int vCode = Integer.parseInt(versionCode);
				info.setVersionCode(vCode);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		doPost(request, response);
	}
}
