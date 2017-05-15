package com.jucaipen.main.purch;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.jucaipen.main.datautils.RollBackUtil;
import com.jucaipen.model.Account;
import com.jucaipen.model.AccountDetail;
import com.jucaipen.model.ChargeOrder;
import com.jucaipen.model.SysAccount;
import com.jucaipen.model.SysDetailAccount;
import com.jucaipen.service.AccountSer;
import com.jucaipen.service.ChargeOrderSer;
import com.jucaipen.service.SysAccountSer;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;
/**
 * @author ���ʷ� ֧���ص��ӿ�
 */
public class RechargeResult extends HttpServlet {
	private static final long serialVersionUID = -7946065445513185182L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Log(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

	private void Log(HttpServletRequest request) throws Exception {
		String appid=null;
		String tradeNo=null;
		String total_amount=null;
		String payTime=null;
		String orderTime=null;
		int tralType=0;
		Enumeration<String> names = request.getParameterNames();
		// ֧����------------------------------------------------------------------------
		// 2016010501066384
		appid = request.getParameter("app_id");
		// �̻�������
		tradeNo = request.getParameter("out_trade_no");
		// �������
		total_amount = request.getParameter("total_amount");
		// ���״���ʱ��
		orderTime = request.getParameter("gmt_create");
		// ���׽���ʱ��
		payTime = request.getParameter("gmt_payment");
		if (StringUtil.isNotNull(appid)) {
			// ֧����֧��
			tralType=2;
		} else {
			//΢��֧��
			tralType=3;
			//InputStream inStream = request.getInputStream();
			/*ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outSteam.write(buffer, 0, len);
			}
			outSteam.close();
			inStream.close();*/
			/*<xml><appid><![CDATA[wx4e03910ab5dec31d]]></appid>
			<bank_type><![CDATA[CFT]]></bank_type>
			<cash_fee><![CDATA[1]]></cash_fee>
			<fee_type><![CDATA[CNY]]></fee_type>
			<is_subscribe><![CDATA[N]]></is_subscribe>
			<mch_id><![CDATA[1273861101]]></mch_id>
			<nonce_str><![CDATA[1bd2caf96a17d892c2c7e9959549cfc7]]></nonce_str>
			<openid><![CDATA[oOPWat40BAmBiVP6wwPXtP2k6Sc8]]></openid>
			<out_trade_no><![CDATA[617050412510644779]]></out_trade_no>
			<result_code><![CDATA[SUCCESS]]></result_code>
			<return_code><![CDATA[SUCCESS]]></return_code>
			<sign><![CDATA[9F550B1952CD27931CFA7CA103AB14E4]]></sign>
			<time_end><![CDATA[20170504125114]]></time_end>
			<total_fee>1</total_fee>
			<trade_type><![CDATA[APP]]></trade_type>
			<transaction_id><![CDATA[4000412001201705049508011754]]></transaction_id>
			</xml>*/
           DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
           DocumentBuilder builder = factory.newDocumentBuilder();
           Document parse = builder.parse(request.getInputStream());
           Element element = parse.getDocumentElement();
           if(element!=null){
        	   NodeList childNodes = element.getChildNodes();
        	   orderTime=TimeUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
        	   for(int i=0;i<childNodes.getLength();i++){
        		   Node item = childNodes.item(i);
        		   String nodeName = item.getNodeName();
        		   if(!"#text".equals(nodeName)){
        			   if("appid".equals(nodeName)){
        				   appid=item.getTextContent();
        			   }else if("out_trade_no".equals(nodeName)){
        				   tradeNo=item.getTextContent();
        			   }else if("total_fee".equals(nodeName)){
        				   total_amount=(Double.valueOf(item.getTextContent())/100)+"";
        			   }else if("time_end".equals(nodeName)){
        				   Date time = TimeUtils.parse(item.getTextContent(), "yyyyMMddHHmmss");
        				   payTime= TimeUtils.format(time, "yyyy-MM-dd HH:mm:ss");
        			   }
        		   }
        	   }
           }
		}
		int state = 1;
		// �ж϶������Ƿ��Ѿ�����
		ChargeOrder order = ChargeOrderSer.findOrderByOrderCode(tradeNo);
		if (order.getOrderState() != 2) {
			if(tralType==2){
				System.out.println("���ڽ���֧����֧��-----------------------------------");
				while (names.hasMoreElements()) {
					String name = names.nextElement();
					String value = request.getParameter(name);
					System.out.println(name + "=" + value);
				}
			}else{
				System.out.println("���ڽ���΢��֧��-------------------------------------------");
				System.out.println("appId="+appid);
				System.out.println("tradeNo="+tradeNo);
				System.out.println("total_amount="+total_amount);
				System.out.println("orderTime="+orderTime);
				System.out.println("payTime="+payTime);
			}
			int uId = order.getUserId();
			double amount = Double.valueOf(total_amount);
			initRecharge(uId, (int) amount, tradeNo, payTime, state, tralType,
					orderTime);

		}
	}
	

	private String initRecharge(int uId, int bills, String orderCode,
			String payDate, int pState, int type, String prePayDate) {
		Account a = AccountSer.findAccountByUserId(uId);
		AccountDetail detail = new AccountDetail();
		SysAccount account = SysAccountSer.findAccountInfo();
		detail.setDetailMoney(bills);
		detail.setDetailType(0);
		detail.setInsertDate(TimeUtils
				.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		detail.setIsDel(0);
		detail.setOrderCode(orderCode);
		detail.setRemark("��ֵ�۲Ʊ�");
		detail.setState(0);
		detail.setUserId(uId);

		SysDetailAccount detailAccount = new SysDetailAccount();
		detailAccount.setInsertDate(TimeUtils.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		detailAccount.setIsDel(0);
		detailAccount.setOrderId(0);
		detailAccount.setPrice(bills);
		detailAccount.setRecoderType(1);
		detailAccount.setType(1);
		detailAccount.setIp("");
		// 1�������У�ͨ���� 2֧���� 3΢��֧��4�����֧�� 5��������(�㸶��)
		if (type == 1) {
			detailAccount.setRemark("�û�ͨ����ֵ�۲Ʊ�");
		} else if (type == 2) {
			detailAccount.setRemark("�û�֧������ֵ�۲Ʊ�");
		} else if (type == 3) {
			detailAccount.setRemark("�û�΢�ų�ֵ�۲Ʊ�");
		} else if (type == 4) {
			detailAccount.setRemark("�û�����ֵ�۲Ʊ�");
		} else {
			detailAccount.setRemark("�û��㸶����ֵ�۲Ʊ�");
		}
		detailAccount.setUserId(uId);
		int isSuccess = RollBackUtil.getInstance().rechargeNoRollBack(
				orderCode, pState, payDate, "", bills, a, uId, detail, account,
				detailAccount, type, prePayDate);
		if(type==2){
			return isSuccess == 1 ? "success" : "fail";
		}else{
			StringBuffer buffer=new StringBuffer();
			buffer.append("<xml>");
			buffer.append("<return_code><![CDATA[SUCCESS]]></return_code>");
			buffer.append("<return_msg><![CDATA[OK]]></return_msg>");
			buffer.append("</xml>");
			return isSuccess == 1 ? buffer.toString(): "fail";
		}
	}

}
