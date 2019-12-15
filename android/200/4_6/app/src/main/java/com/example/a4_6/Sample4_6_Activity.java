package com.example.a4_6;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.EditText;

import java.lang.reflect.Method;

public class Sample4_6_Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
	private void setIconEnable(Menu menu, boolean enable){
		try{
			Class<?> clazz = Class.forName("com.android.internal.view.menu.MenuBuilder");

			Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
			m.setAccessible(true);
			//MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
			m.invoke(menu, enable);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
    final int MENU_GENDER_MALE=0;    //ÿ���˵���Ŀ�ı��=======begin=========
    final int MENU_GENDER_FEMALE=1;
    final int MENU_HOBBY1=2;
    final int MENU_HOBBY2=3;
    final int MENU_HOBBY3=4;
    final int MENU_OK=5;    
    final int MENU_GENDER=6;  
    final int MENU_HOBBY=7;         //ÿ���˵���Ŀ�ı��=======end============
    
    final int GENDER_GROUP=0;      //�Ա��Ӳ˵�����ı��
    final int HOBBY_GROUP=1;  	   //�����Ӳ˵�����ı��
    final int MAIN_GROUP=2;        //����ܲ˵�����ı��
    
    MenuItem[] miaHobby=new MenuItem[3];//���ò˵�����
    MenuItem male=null;//�����Ա�˵���
    
    //��ȡ��ǰѡ��״̬�ķ���
    public void appendStateStr()
    {
    	String result="��ѡ����Ա�Ϊ��";
    	if(male.isChecked())
    	{
    		result=result+"��";
    	}
    	else
    	{
    		result=result+"Ů";
    	}
    	
    	String hobbyStr="";
    	for(MenuItem mi:miaHobby)
    	{
    		if(mi.isChecked())
    		{
    			hobbyStr=hobbyStr+mi.getTitle()+"��";
    		}
    	}
    	
    	if(hobbyStr.length()>0)
    	{
    		result=result+",���İ���Ϊ��"+hobbyStr.substring(0, hobbyStr.length()-1)+"��\n";
    	}
    	else
    	{
    		result=result+"��\n";
    	}
    	//EditText et=(EditText)Sample4_6_Activity.this.findViewById(R.id.);
		//et.append(result);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
		setIconEnable(menu, true);
    	//�Ա�ѡ�˵�����   �˵���������ǵ�ѡ�˵�����

    	SubMenu subMenuGender = menu.addSubMenu(MAIN_GROUP,MENU_GENDER,0,"性别");
    	subMenuGender.setIcon(R.drawable.gender);
    	male=subMenuGender.add(GENDER_GROUP, MENU_GENDER_MALE, 0, "男");
    	male.setChecked(true);
    	subMenuGender.add(GENDER_GROUP, MENU_GENDER_FEMALE, 0, "女");
    	//����GENDER_GROUP���ǿ�ѡ��ģ������
    	subMenuGender.setGroupCheckable(GENDER_GROUP, true,true);




    	//���ø�ѡ�˵�����
    	SubMenu subMenuHobby = menu.addSubMenu(MAIN_GROUP,MENU_HOBBY,0,"爱好");
    	subMenuHobby.setIcon(R.drawable.hobby); 
    	miaHobby[0]=subMenuHobby.add(HOBBY_GROUP, MENU_HOBBY1, 0, "游泳");
    	miaHobby[1]=subMenuHobby.add(HOBBY_GROUP, MENU_HOBBY2, 0, "打球");
    	miaHobby[2]=subMenuHobby.add(HOBBY_GROUP, MENU_HOBBY3, 0, "写代码");
    	miaHobby[0].setCheckable(true);//���ò˵���Ϊ��ѡ�˵���
    	miaHobby[1].setCheckable(true);
    	miaHobby[2].setCheckable(true);
    	
    	//ȷ���˵���
    	MenuItem ok=menu.add(GENDER_GROUP+2,MENU_OK,0,"确定");
    	OnMenuItemClickListener lsn=new OnMenuItemClickListener()
    	{//ʵ�ֲ˵������¼������ӿ�
			public boolean onMenuItemClick(MenuItem item) {
				appendStateStr();
				return true;
			}    		
    	};
    	ok.setOnMenuItemClickListener(lsn);//��ȷ���˵�����Ӽ�����    	
    	//��ȷ���˵�����ӿ�ݼ�
    	ok.setAlphabeticShortcut('o');//�����ַ���ݼ�
    	//ok.setNumericShortcut('1');//�������ֿ�ݼ�
    	//ok.setShortcut('a', '2');//ͬʱ�������ֿ�ݼ�
    	//Ҫע�⣬ͬʱ���ö��ʱֻ�����һ������������

    	return true;
    }
    
    @Override  //��ѡ��ѡ�˵���ѡ��״̬�仯��Ļص�����
    public boolean onOptionsItemSelected(MenuItem mi)
    {

    	switch(mi.getItemId())
    	{
    	   case MENU_GENDER_MALE://��ѡ�˵���״̬���л�Ҫ����д�������
    	   case MENU_GENDER_FEMALE:
    		    mi.setChecked(true); 
    		    appendStateStr();//����Ч��Ŀ�仯ʱ��¼���ı�����
    	   break;   
    	   
    	   case MENU_HOBBY1://��ѡ�˵���״̬���л�Ҫ����д�������
    	   case MENU_HOBBY2:
    	   case MENU_HOBBY3:
    	       mi.setChecked(!mi.isChecked());
    	       appendStateStr();//����Ч��Ŀ�仯ʱ��¼���ı�����
    	   break;    	 
    	}

    	return true;
    }
}