package neusoft.edu.cn.broadcastreceiversmsdemo;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;

public class SmsReceiveReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String SMS_ACTION = "android.provider.Telephony.SMS_RECEIVED";
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm,MM月dd日");
		if (intent.getAction().equals(SMS_ACTION)) {
			// System.out.println("--receive sms->>");
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				Object[] objects = (Object[]) bundle.get("pdus");//得到由短信内容组成的数组对象
				SmsMessage[] messages = new SmsMessage[objects.length];
				for (int i = 0; i < objects.length; i++) {
					// 为原始的PDU创建一个SmsMessage对象
					messages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
				}
					String smsBody = messages[0].getDisplayMessageBody();
					String smsSender = messages[0].getDisplayOriginatingAddress();
					String smsReceiveTime = dateFormat.format(new Date());// 获取当前系统时间
					
					MainActivity.textView.setText("内容：" + smsBody + "\n"
							+ "发送者：" + smsSender + "\n" +"接收时间："
							+ smsReceiveTime);


			}
		}
	}

}
