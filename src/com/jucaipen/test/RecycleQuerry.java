package com.jucaipen.test;

import java.util.List;
import com.jucaipen.model.Answer;
import com.jucaipen.model.Ask;
import com.jucaipen.service.AnswerSer;
import com.jucaipen.service.AskSer;

/**
 * @author Administrator
 *  µ›πÈ≤‚ ‘
 */
public class RecycleQuerry {
	static StringBuffer  cIdArray=new StringBuffer();
	
	
	
	public static void main(String[] args) {
		
	}
	
	
	
	public static void getAnswerList(int askId){
		List<Answer> answers=AnswerSer.findAnswerByAskId(askId);
		if(answers!=null){
			for(Answer ans : answers){
				int answerId=ans.getId();
				List<Ask> asks=AskSer.findAskByParentId(answerId);
				if(asks!=null){
					for(Ask ask : asks){
						getAnswerList(ask.getId());	
					}
					
				}
			}
		}
		
		
		
	}
		
	
	/*public static void main(String[] args) {
		List<VideoClass> vcs = VideoClassSer.findClassByPid(2);
		StringBuffer vs = getVideoClass(vcs);
		System.out.println(vs.toString());
		
	}
	
	
	
	public static StringBuffer getVideoClass(List<VideoClass> vcs){
		if (vcs != null) {
			for (VideoClass vc : vcs) {
				cIdArray.append(vc.getId());
				cIdArray.append(",");
				List<VideoClass> vs = VideoClassSer.findClassByPid(vc.getId());
				if (vs != null) {
					getVideoClass(vs);
				}
			}
		}
		return cIdArray;
	}
*/
}
