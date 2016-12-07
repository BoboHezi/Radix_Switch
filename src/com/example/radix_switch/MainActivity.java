package com.example.radix_switch;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {
	
	OnClickListener listener1=null;
	Button button1;
	String text=null;
	int choise1=0;
	int choise2=0;
	float befornumber=0;
	char befortext[];
	int buff[];
	byte buff1[]=new byte[100];
	int length=0;
	int lengthofDecimal=0;
	int Decimalmark=0;
	int pointmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
///////////////////Button触发事件//////////////////////     
        listener1 = new OnClickListener()
        {
        	public void onClick(View v){
        ////////////////在此处触发事件////////////////////////
         		funcation();                           //
        /////////////////////////////////////////////////
        	}
        };  
        setContentView(R.layout.main);
        getWindow().setBackgroundDrawableResource(R.drawable.background);
        button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(listener1);
///////////////////Button触发事件//////////////////////   
////////////////////第一个选择框////////////////////////    
        Spinner s1=(Spinner)findViewById(R.id.Spinner01);
        final ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.befor, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?>parent,View view,int position,long id) {
				choise1=(int) adapter.getItemId(position);
				//getItemId   以0开始按顺序排列的数
			}

			@Override
			public void onNothingSelected(AdapterView<?>parent) {
			}
			
		});
///////////////////第一个选择框//////////////////////   
///////////////////第二个选择框//////////////////////   
        Spinner s2=(Spinner)findViewById(R.id.Spinner02);
        final ArrayAdapter<CharSequence>adapter1=ArrayAdapter.createFromResource(this, R.array.befor, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter1);
        s2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?>parent,View view,int position,long id) {
				choise2=(int) adapter.getItemId(position);
				//getItemId   以0开始按顺序排列的数
			}

			@Override
			public void onNothingSelected(AdapterView<?>parent) {
			}
			
		});
///////////////////第二个选择框//////////////////////   
        
        
    }

    public void funcation()
    {	
    	befornumber=0;
    	buff=null;
    	EditText edittext=(EditText)findViewById(R.id.befornum);
    	
    	text=edittext.getText().toString();
    	int i= 0;
    	
    	transtonumber();
    	//转化为单个数字字符
    	i=cheak();
    	//检验是否有错
    	if(i==0)
    	{
    		Toast.makeText(getApplicationContext(), "您的输入有误！",Toast.LENGTH_SHORT).show();
    		TextView finaltext1=(TextView)findViewById(R.id.textView4);
    		finaltext1.setText("结果为：");
    	}
    	else
    	{
    		toten();
    		//转化为十进制
    		toRadix();
    		//转化为其他进制   
    		display();
    		//显示
    	}
    }
 
    public void transtonumber()
    {
    	befortext=new char[text.length()];
    	befortext=text.toCharArray();
    	buff = new int[text.length()];
    	for(int i=0;i<text.length();i++)
    	{
    		if(befortext[i]=='A'||befortext[i]=='B'||befortext[i]=='C'||befortext[i]=='D'||befortext[i]=='E'||befortext[i]=='F')
    			buff[i]=befortext[i]-55;
    		else
    		{
    			buff[i]=befortext[i]-48;
    		}
    	}
    }
    
    public void toten()//转换为十进制
    {
    	int Radix = 0;
    	int i,j;
    	Decimalmark=0;
    	int point=text.length();
    	switch(choise1){
    	case 0:Radix=2;break;
    	case 1:Radix=8;break;
    	case 2:Radix=10;break;
    	case 3:Radix=16;break;
    	}
    	for(i=0;i<text.length();i++)
    	{
    		if(buff[i]==-2)
    		{
    			point=i;
    			break;
    		}
    	}
    	if(point!=text.length())
    	{
    		Decimalmark=1;
    	}
    	for(i=0;i<text.length();i++)
    	{
    		j=1;
    		float choise=1;
    		if(i<point)
    		{
    			while(j<=point-i-1)
    			{
    				choise=choise*Radix;
    				j++;
    			}
    		}
    		else if(i==point)
    			continue;
    		else
    		{
    			while(j<=i-point-1)
    			{
    				choise=choise*Radix;
    				j++;
    			}
				choise=choise*Radix;
				choise=1/choise;
    		}
    		befornumber=befornumber+(buff[i])*choise;
    	} 
    }
    
    public void toRadix()
    {	
    	int i=0;
    	int Radix=0;
    	length=0;
    	lengthofDecimal=0;
    	for(i=0;i<text.length();i++)
    	{
    		if(buff[i]==-2)
    		{
    			break;
    		}
    	}
    	switch(choise2)
    	{
    	case 0:Radix=2;break;
    	case 1:Radix=8;break;
    	case 2:Radix=10;break;
    	case 3:Radix=16;break;
    	}
    	if(Decimalmark==0)
    	{
    		for(i=0;befornumber>0.5;i++)
    		{
    			buff1[i]=(byte) (befornumber%Radix);
    			befornumber=befornumber/Radix;
    		}
    		length=i;
    	}
    	else
    	{
    		float befornumber1=befornumber;
    		int Integer=(int) befornumber1;
    		float Decimal=befornumber1-Integer;
    		
    		for(i=0;Integer>0.5;i++)
    		{
    			buff1[i]=(byte) (Integer%Radix);
    			Integer=Integer/Radix;
    		}
    		length=i;
    		
    		int j=0;
    		int Inte;
    		for(i=49;Decimal!=0;i++)
    		{
    			Decimal=Decimal*Radix;
    			Inte=(int) Decimal;
    			Decimal=Decimal-Inte;
    			buff1[i]=(byte) Inte;
    			j++;
    			if(j>=6)
    				break;
    		}
    		lengthofDecimal=j;
    	}
    }
    
    public void display()
    {
    	char aftertext[]=new char[length+lengthofDecimal+2];
    	int i;

    	for(i=0;i<length;i++)
    	{
    		if(buff1[i]<10)
    			aftertext[length-i-1]=(char) (buff1[i]+48);
    		else
    			aftertext[length-i-1]=(char) (buff1[i]+55);
    	}
    	if(Decimalmark==1)
    	{
    		aftertext[length]='.';
    	}
    	int j=49;
    	for(i=length+1;i<length+lengthofDecimal+1;i++,j++)
    	{
    		if(buff1[j]<10)
    			aftertext[i]=(char) (buff1[j]+48);
    		else
    			aftertext[i]=(char) (buff1[j]+55);
    	}
    	TextView finalnum=(TextView)findViewById(R.id.textView4);
    	String finalstr=new String(aftertext);
    	if(length==0)
    		finalstr='0'+finalstr;
    	if(befornumber==0)
    	{
    		if(choise2==1)
        		finalnum.setText("结果为：OX0");
        	else if(choise2==3)
        		finalnum.setText("结果为：0h");
        	else
        		finalnum.setText("结果为：0");
    	}
    	else
    	{
    		if(choise2==1)
    			finalnum.setText("结果为：OX"+finalstr);
    		else if(choise2==3)
    			finalnum.setText("结果为："+finalstr+"h");
    		else
    			finalnum.setText("结果为："+finalstr);
    	}
    }
    public int cheak()
    {
    	int i;
    	int Radix = 0;
    	int point=0;
    	switch(choise1){
    	case 0:Radix=2;break;
    	case 1:Radix=8;break;
    	case 2:Radix=10;break;
    	case 3:Radix=16;break;
    	}
    	for(i=0;i<text.length();i++)
    	{
    		if(buff[i]==-2)
    		{
    			point++;
    			pointmark=1;
    		}
    		if(buff[i]>=Radix)
    			return 0;
    	}
    	if(point>=2)
    		return 0;
    	return 1;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
