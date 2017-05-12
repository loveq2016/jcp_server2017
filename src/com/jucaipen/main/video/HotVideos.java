package com.jucaipen.main.video;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.FamousTeacher;
import com.jucaipen.model.RecoderVideo;
import com.jucaipen.model.VideoLive;
import com.jucaipen.service.FamousTeacherSer;
import com.jucaipen.service.RecoderVideoServer;
import com.jucaipen.service.VideoLiveServer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 * s
 *         获取热门搜索视频
 */
public class HotVideos extends HttpServlet {
	private static final long serialVersionUID = 4404643307063853645L;
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String type=request.getParameter("type");
		if(StringUtil.isNotNull(type)&&StringUtil.isInteger(type)){
			int typeId=Integer.parseInt(type);
			if(typeId==1){
				//热门视频
				result = initHotVideoList();
			}else{
				//热门讲师
				result=initHotTeacherList();
			}
		}else{
			result=JsonUtil.getRetMsg(1,"type 参数异常");
		}
	
		out.println(result);
		out.flush();
		out.close();
	}

	/**
	 * @return  初始化讲师热门列表
	 */
	private String initHotTeacherList() {
		List<FamousTeacher> teachers = FamousTeacherSer.findHotTeacher(5);
		for(FamousTeacher teacher : teachers){
			int id = teacher.getId();
			VideoLive videoLive=VideoLiveServer.findLiveBytId(id);
			if(videoLive!=null){
				teacher.setIsEnd(videoLive.getIsEnd());
			}
		}
		return JsonUtil.getHotTeacher(teachers);
	}

	private String initHotVideoList() {
		List<RecoderVideo>  videos=RecoderVideoServer.getHotVideos(5);
		return JsonUtil.getHotVideo(videos);
	}
}
