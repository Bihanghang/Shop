package com.verify;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class MessageUtils {  
    /** 
     * 发送短信消息方法,返回验证码 
     * 
     * @param phone 
     * @return 
     */  
    public static String sendMsg(String phone) throws Exception {  
  
        //设置超时时间-可自行调整  
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");  
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");  
        //初始化ascClient需要的几个参数  
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）  
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）  
        //替换成你的AK  

        final String accessKeyId = "LTAIn9DiipS5h0Ff";//你的accessKeyId,参考本文档步骤2  
        final String accessKeySecret = "oV03dWGZrcr7U9zMrTxfExW9VyN1OL";//你的accessKeySecret，参考本文档步骤2  
        //初始化ascClient,暂时不支持多region（请勿修改）  
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,  
                accessKeySecret);  
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);  
        IAcsClient acsClient = new DefaultAcsClient(profile);  
        //组装请求对象  
        SendSmsRequest request = new SendSmsRequest();  
        //使用post提交  
        request.setMethod(MethodType.POST);  
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式  
        request.setPhoneNumbers(phone);  
        //必填:短信签名-可在短信控制台中找到  
        request.setSignName("匿名信");  
        //必填:短信模板-可在短信控制台中找到  
        request.setTemplateCode("SMS_132391135");  
        String checkCode = "sdfsdfsf";//此处是生成6位数验证码工具类  
        //request.setTemplateParam("{\"code\":\"123\"}");//测试用,此处json一定要严格按照json格式书写  
        request.setTemplateParam("{\"password\":\""+checkCode+"\"}");  
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)  
        //request.setSmsUpExtendCode("90997");  
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者  
        request.setOutId("yourOutId");  
        //请求失败这里会抛ClientException异常  
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);  
  
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {  
            //请求成功  
            System.out.println(sendSmsResponse.getCode());  
            return checkCode;  
        }  
        return null;  
    }  
    //测试发送短信  
    public static void main(String[] args) {
    	String s="";
		try {
			s = sendMsg("15996954427");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//手机号  
        System.out.println(s); 
	}
         
}  
