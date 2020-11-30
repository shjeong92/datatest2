package com.example.datatest2;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.TabHost;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;




public class MainActivity extends AppCompatActivity {

    Button stDate,stCity,stHospital,stCity2;
    DatePicker dPicker;
    View dialogview1;
    TextView result,result2;
    String date,city,hospital;
    int selectYear,seletcMonth,selectDay;
    XmlPullParser xpp;
    String data,temp_createDt,temp_deathCnt,temp_defCnt
            ,temp_adtFrDd,temp_hospTyTpCd,temp_sgguNm;
    String cityChk = null;
    int tagIdentifier = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost th = (TabHost)findViewById(R.id.th);
        th.setup();


        TabHost.TabSpec ts1 = th.newTabSpec("Tab1");
        ts1.setIndicator("확진자");
        ts1.setContent(R.id.tab_view1);
        th.addTab(ts1);

        TabHost.TabSpec ts2 = th.newTabSpec("Tab2");
        ts2.setIndicator("병원정보");
        ts2.setContent(R.id.tab_view2);
        th.addTab(ts2);

        stDate=findViewById(R.id.stDate);
        stCity=findViewById(R.id.stCity);
        result=findViewById(R.id.result);
        result2=findViewById(R.id.result2);
        stHospital=findViewById(R.id.stHospital);
        stCity2=findViewById(R.id.stCity2);
        stDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogview1 = (View) View.inflate(MainActivity.this,R.layout.dialog,null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("기준날짜를 선택하세요");
                dlg.setView(dialogview1);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dPicker=(DatePicker)dialogview1.findViewById(R.id.dPicker);
                        selectYear=dPicker.getYear();
                        seletcMonth=dPicker.getMonth()+1;
                        selectDay=dPicker.getDayOfMonth();
                        date=Integer.toString(10000*selectYear+100*seletcMonth+selectDay);

                        stDate.setText(date);

                    }
                });
                dlg.setNegativeButton("취소",null);
                dlg.show();
            }
        });


        th.setCurrentTab(0);

        stCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this,stCity);

                MenuInflater inf = popup.getMenuInflater();
                inf.inflate(R.menu.menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()){
                            case R.id.seoul:
                                stCity.setText("서울");
                                city="서울";
                                break;
                            case R.id.gyeongnam:
                                stCity.setText("경남");
                                city="경남";
                                break;
                            case R.id.gyeongbuk:
                                stCity.setText("경북");
                                city="경북";
                                break;
                            case R.id.jeonnam:
                                stCity.setText("전남");
                                city="전남";
                                break;
                            case R.id.jeonbuk:
                                stCity.setText("전북 ");
                                city="전북";
                                break;
                            case R.id.chungnam:
                                stCity.setText("충남");
                                city="충남";
                                break;
                            case R.id.chungbuk:
                                stCity.setText("충북");
                                city="충북";
                                break;
                            case R.id.gangwon:
                                stCity.setText("강원");
                                city="강원";
                                break;
                            case R.id.gyeongki:
                                stCity.setText("경기");
                                city="경기";
                                break;
                            case R.id.sejong:
                                stCity.setText("세종");
                                city="세종";
                                break;
                            case R.id.ulsan:
                                stCity.setText("울산");
                                city="을신";
                                break;
                            case R.id.daejun:
                                stCity.setText("대전");
                                city="대전";
                                break;
                            case R.id.gwangju:
                                stCity.setText("광주");
                                city="광주";
                                break;
                            case R.id.incheon:
                                stCity.setText("인천");
                                city="인천";
                                break;
                            case R.id.daegu:
                                stCity.setText("대구");
                                city="대구";
                                break;
                            case R.id.busan:
                                stCity.setText("부산");
                                city="부산";
                                break;
                            case R.id.jeju:
                                stCity.setText("제주");
                                city="제주";
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
        stHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this,stHospital);

                MenuInflater inf = popup.getMenuInflater();
                inf.inflate(R.menu.hos_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()){

                            case R.id.hos_ansim:
                                stHospital.setText("국민안심병원");
                                hospital="A0";
                                break;
                            case R.id.hos_corona1:
                                stHospital.setText("코로나검사 실시기관");
                                hospital="97";
                                break;
                            case R.id.hos_corono2:
                                stHospital.setText("코로나 선별진료소 운영기관");
                                hospital="99";
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
        stCity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this,stCity2);

                MenuInflater inf = popup.getMenuInflater();
                inf.inflate(R.menu.menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()){
                            case R.id.seoul:
                                stCity2.setText("서울");
                                city="서울";
                                break;
                            case R.id.gyeongnam:
                                stCity2.setText("경남");
                                city="경남";
                                break;
                            case R.id.gyeongbuk:
                                stCity2.setText("경북");
                                city="경북";
                                break;
                            case R.id.jeonnam:
                                stCity2.setText("전남");
                                city="전남";
                                break;
                            case R.id.jeonbuk:
                                stCity2.setText("전북 ");
                                city="전북";
                                break;
                            case R.id.chungnam:
                                stCity2.setText("충남");
                                city="충남";
                                break;
                            case R.id.chungbuk:
                                stCity2.setText("충북");
                                city="충북";
                                break;
                            case R.id.gangwon:
                                stCity2.setText("강원");
                                city="강원";
                                break;
                            case R.id.gyeongki:
                                stCity2.setText("경기");
                                city="경기";
                                break;
                            case R.id.sejong:
                                stCity2.setText("세종");
                                city="세종";
                                break;
                            case R.id.ulsan:
                                stCity2.setText("울산");
                                city="을신";
                                break;
                            case R.id.daejun:
                                stCity2.setText("대전");
                                city="대전";
                                break;
                            case R.id.gwangju:
                                stCity2.setText("광주");
                                city="광주";
                                break;
                            case R.id.incheon:
                                stCity2.setText("인천");
                                city="인천";
                                break;
                            case R.id.daegu:
                                stCity2.setText("대구");
                                city="대구";
                                break;
                            case R.id.busan:
                                stCity2.setText("부산");
                                city="부산";
                                break;
                            case R.id.jeju:
                                stCity2.setText("제주");
                                city="제주";
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });

    }

    public void btnSearch(View v){
        switch (v.getId()){
            case R.id.btnSearch:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        data=getXmlData();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                result.setText(data);
                            }
                        });
                    }
                }).start();
                break;

        }
    }
    public void btnSearch2(View v){
        switch (v.getId()){
            case R.id.btnSearch2:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        data=getXmlData2();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                result2.setText(data);
                            }
                        });
                    }
                }).start();
                break;

        }
    }
    String getXmlData(){
        StringBuffer buffer=new StringBuffer();
        String str= date.toString(); //EditText에 작성된 Text얻어오기
        String date = URLEncoder.encode(str);
        String queryUrl="http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=" +
                "SASy2U2mpWYLDCaI%2B051WRSeUzKC%2F4NV92OJQFH6YNzxTLJR6ArEud3PX2Qh49qX3NBs%2B0OVSCnLWgrLD5o6Pw%3D%3D" +
                "&pageNo=1" +
                "&numOfRows=10" +
                "&startCreateDt="+date +
                "&endCreateDt="+ date;
        try{
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is,"UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag = null;

            xpp.next();
            int eventType= xpp.getEventType();
            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기

                        break;
                    case XmlPullParser.TEXT:
                        if(tag.equals("createDt")){
                            //xpp.next();
                            temp_createDt=xpp.getText();
                        }
                        else if(tag.equals("deathCnt")){
                            //xpp.next();
                            temp_deathCnt=xpp.getText();
                        }
                        else if(tag.equals("defCnt")){
                            //xpp.next();
                            temp_defCnt=xpp.getText();
                        }
                        else if(tag.equals("gubun")){
                            //xpp.next();
                            cityChk=xpp.getText();

                            if (cityChk.equals(city)) {
                                buffer.append(cityChk);
                                buffer.append("\n");

                                buffer.append("기준일: ");
                                buffer.append(temp_createDt);
                                buffer.append("\n");

                                buffer.append("사망자 수: ");
                                buffer.append(temp_deathCnt);
                                buffer.append("\n");

                                buffer.append("확진자 수: ");
                                buffer.append(temp_defCnt);
                                buffer.append("\n");
                            }
                        }
                        else if(tag.equals("incDec") && cityChk.equals(city)){
                            buffer.append("전일대비 증감수 : ");
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if(tag.equals("isolClearCnt") && cityChk.equals(city)){
                            buffer.append("격리 해제 수 : ");
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("isolIngCnt") && cityChk.equals(city)){
                            buffer.append("격리 중 환자수: ");
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("localOccCnt") && cityChk.equals(city)){
                            buffer.append("지역발생 수 : ");
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("overFlowCnt") && cityChk.equals(city)){
                            buffer.append("해외유입 수 : ");
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        else if(tag.equals("qurRate") && cityChk.equals(city)){
                            buffer.append("10만명당 발생률 : ");
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기

                        if(tag.equals("item")) {
                            cityChk = "";
                            temp_createDt = "";
                            temp_deathCnt = "";
                            temp_defCnt = "";
                        }

                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + eventType);
                }

                eventType= xpp.next();
            }

        } catch (Exception e){
            e.printStackTrace();
        }


        return buffer.toString();//StringBuffer 문자열 객체 반환

    }

    String getXmlData2(){
        StringBuffer buffer=new StringBuffer();
        String str= hospital.toString(); //EditText에 작성된 Text얻어오기
        String hospital = URLEncoder.encode(str);
        String queryUrl="http://apis.data.go.kr/B551182/pubReliefHospService/getpubReliefHospList?serviceKey=SASy2U2mpWYLDCaI%2B051WRSeUzKC%2F4NV92OJQFH6YNzxTLJR6ArEud3PX2Qh49qX3NBs%2B0OVSCnLWgrLD5o6Pw%3D%3D" +
                "&pageNo=1" +
                "&numOfRows=1000" +
                "&spclAdmTyCd="+hospital;
        try{
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is,"UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag = null;

            xpp.next();
            int eventType= xpp.getEventType();
            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기

                        break;
                    case XmlPullParser.TEXT:

                        if(tag.equals("adtFrDd")){
                            temp_adtFrDd=xpp.getText();
                        }
                        else if(tag.equals("hospTyTpCd")){
                            temp_hospTyTpCd=xpp.getText();
                        }
                        else if(tag.equals("sgguNm")){
                            temp_sgguNm=xpp.getText();
                        }
                        else if(tag.equals("sidoNm")){
                            cityChk=xpp.getText();

                            if(city.equals(cityChk)){
                                buffer.append(cityChk);
                                buffer.append("\n");
                                buffer.append(temp_adtFrDd);
                                buffer.append("\n");
                                buffer.append(temp_sgguNm);
                                buffer.append("\n");
                                buffer.append(temp_hospTyTpCd);
                                buffer.append("\n");
                            }
                        }

                        /*
                        if(tag.equals("createDt")){
                            //xpp.next();
                            temp_createDt=xpp.getText();
                        }
                        else if(tag.equals("deathCnt")){
                            //xpp.next();
                            temp_deathCnt=xpp.getText();
                        }
                        else if(tag.equals("defCnt")){
                            //xpp.next();
                            temp_defCnt=xpp.getText();
                        }
                        else if(tag.equals("gubun")){
                            //xpp.next();
                            cityChk=xpp.getText();

                            if (cityChk.equals(city)) {
                                buffer.append(cityChk);
                                buffer.append("\n");

                                buffer.append("기준일: ");
                                buffer.append(temp_createDt);
                                buffer.append("\n");

                                buffer.append("사망자 수: ");
                                buffer.append(temp_deathCnt);
                                buffer.append("\n");

                                buffer.append("확진자 수: ");
                                buffer.append(temp_defCnt);
                                buffer.append("\n");
                            }
                        }*/
                        break;
                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기

                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + eventType);
                }

                eventType= xpp.next();
            }

        } catch (Exception e){
            e.printStackTrace();
        }


        return buffer.toString();//StringBuffer 문자열 객체 반환

    }
}
