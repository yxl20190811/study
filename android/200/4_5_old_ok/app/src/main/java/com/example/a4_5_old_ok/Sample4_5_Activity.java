package com.example.a4_5_old_ok;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sample4_5_Activity extends Activity 
{
	EditText et;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        
        //��ʼ��ָ������������ı���������
        et=(EditText)this.findViewById(R.id.EditText01);
        //��ȡȷ����ť
        Button b=(Button)this.findViewById(R.id.Button01);
        //��ȷ����ť��Ӽ�����
        b.setOnClickListener
        (
        	new OnClickListener()
        	{
				public void onClick(View v) 
				{
					//��ȡ�ı��������������
					String msg=et.getText().toString();
					if(msg.trim().length()==0)
					{//�����������Ϊ������ʾ������
						Toast.makeText
						(
								Sample4_5_Activity.this, 
								"���鲻��Ϊ�գ�����", 
								Toast.LENGTH_SHORT
						).show();
						return;
					}
					else if(msg.length()>12)
					{//����������鳬����������ʾ������
						Toast.makeText
						(
								Sample4_5_Activity.this, 
								"���鲻�ܴ���12���֣�����", 
								Toast.LENGTH_SHORT
						).show();
						return;
					}
					
					//������Ϸ�����Intent�޸�widget�е�����
			        Intent intent = new Intent("wyf.action.update_xq");
			        intent.putExtra("xxq", msg);
					intent.setComponent(new ComponentName(Sample4_5_Activity.this, MyWidgetProvider.class));
			        Sample4_5_Activity.this.sendBroadcast(intent);	
			        //������Intent����Activity
			        Sample4_5_Activity.this.finish();
				}        		
        	}
        );


    }
}