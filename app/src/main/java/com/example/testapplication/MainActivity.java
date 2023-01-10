package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Vibrator vibrator;

    boolean inFirstInput = true; // 계산기 입력 전
    double resultNum = 0; // 결과값
    char operator = 'a'; // 연산자 저장
    double beforeResult = 0;
    DecimalFormat df = new DecimalFormat("###,###.########"); // 1000자리 , 찍기

    TextView result;
    LinearLayout record;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        record = findViewById(R.id.record);
        drawerLayout = findViewById(R.id.drawerLayout);

        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void onClick(View v) {
        String nowresult = (String) result.getText();
        vibrator.vibrate(13);
            switch (v.getId()) {
                case R.id.button_zero:
                    if (inFirstInput == true) {
                        result.setText("0");
                    } else {
                        if (nowresult.contains("(")) {
                            int bs = nowresult.indexOf("(");
                            if (nowresult.substring(bs).equals("(")) {
                            } else {
                                notTrue("0");
                            }
                        } else {
                            notTrue("0");
                        }
                    }
                    break;
                case R.id.button_one:
                    if (inFirstInput == true) {
                        inFirstInput = false;
                        result.setText("1");
                    } else {
                        notTrue("1");
                    }
                    break;
                case R.id.button_two:
                    if (inFirstInput == true) {
                        inFirstInput = false;
                        result.setText("2");
                    } else {
                        notTrue("2");
                    }
                    break;
                case R.id.button_three:
                    if (inFirstInput == true) {
                        inFirstInput = false;
                        result.setText("3");
                    } else {
                        notTrue("3");
                    }
                    break;
                case R.id.button_four:
                    if (inFirstInput == true) {
                        inFirstInput = false;
                        result.setText("4");
                    } else {
                        notTrue("4");
                    }
                    break;
                case R.id.button_five:
                    if (inFirstInput == true) {
                        inFirstInput = false;
                        result.setText("5");
                    } else {
                        notTrue("5");
                    }
                    break;
                case R.id.button_six:
                    if (inFirstInput == true) {
                        inFirstInput = false;
                        result.setText("6");
                    } else {
                        notTrue("6");
                    }
                    break;
                case R.id.button_seven:
                    if (inFirstInput == true) {
                        inFirstInput = false;
                        result.setText("7");
                    } else {
                        notTrue("7");
                    }
                    break;
                case R.id.button_eight:
                    if (inFirstInput == true) {
                        inFirstInput = false;
                        result.setText("8");
                    } else {
                        notTrue("8");
                    }
                    break;
                case R.id.button_nine:
                    if (inFirstInput == true) {
                        inFirstInput = false;
                        result.setText("9");
                    } else {
                        notTrue("9");
                    }
                    break;
                case R.id.button_dot:
                    if(inFirstInput == true) {
                        inFirstInput = false;
                        result.setText("0.");
                    } else {
                        if (!nowresult.contains(".")) {
                            notTrue(".");
                        } else if(nowresult.charAt(nowresult.length() - 1) == '.') {
                            nowresult = nowresult.substring(0, nowresult.length()-1);
                            nowresult = replaceC(nowresult);
                            result.setText(nowresult);
                        }
                    }
                    break;
                case R.id.button_ac:
                        inFirstInput = true;
                        resultNum = 0;
                        result.setText("0");
                        operator = 'a';
                        changeOper();
                    break;
                case R.id.button_ce:
                    double getResult = 0;
                    if(!nowresult.equals("0")) {
                        nowresult = nowresult.substring(0, nowresult.length()-1);
                        nowresult = replaceC(nowresult);
                        if(nowresult.equals("")) {
                            getResult = 0;
                            inFirstInput = true;
                        } else {
                            getResult = Double.parseDouble(nowresult);
                        }
                    }
                    result.setText(df.format(getResult));
                    break;
                case R.id.button_bs:
                    nowresult = replaceC(nowresult);
                    resultNum = Double.parseDouble(nowresult) / 100;
                    getResult(resultNum);
                    break;
                case R.id.button_dv:
                    nowresult = replaceC(nowresult);
                        if(operator == '/') {
                            inFirstInput = true;
                            resultNum = Double.parseDouble(nowresult) / Double.parseDouble(nowresult);
                            getResult(resultNum);

                            double beforeResult = Double.parseDouble(nowresult);
                            textview(beforeResult, resultNum, nowresult);
                            operator = 'a';
                        } else {
                            inFirstInput = true;
                            resultNum = Double.parseDouble(nowresult);
                            operator = '/';
                        }
                        changeOper();
                    break;
                case R.id.button_mp:
                    nowresult = replaceC(nowresult);
                        if(operator == '*') {
                            inFirstInput = true;
                            resultNum = Double.parseDouble(nowresult) * Double.parseDouble(nowresult);
                            getResult(resultNum);

                            double beforeResult = Double.parseDouble(nowresult);
                            textview(beforeResult, resultNum, nowresult);
                            operator = 'a';
                        } else {
                            inFirstInput = true;
                            resultNum = Double.parseDouble(nowresult);
                            operator = '*';
                        }
                        changeOper();
                    break;
                case R.id.button_mn:
                    nowresult = replaceC(nowresult);
                        if(operator == '-') {
                            inFirstInput = true;
                            resultNum = Double.parseDouble(nowresult) - Double.parseDouble(nowresult);
                            getResult(resultNum);

                            double beforeResult = Double.parseDouble(nowresult);
                            textview(beforeResult, resultNum, nowresult);
                            operator = 'a';
                        } else {
                            inFirstInput = true;
                            resultNum = Double.parseDouble(nowresult);
                            operator = '-';
                        }
                        changeOper();
                    break;
                case R.id.button_pl:
                    nowresult = replaceC(nowresult);
                        if(operator == '+') {
                            inFirstInput = true;
                            resultNum = Double.parseDouble(nowresult) * 2;
                            getResult(resultNum);

                            double beforeResult = Double.parseDouble(nowresult);
                            textview(beforeResult, resultNum, nowresult);
                            operator = 'a';
                        } else {
                            inFirstInput = true;
                            resultNum = Double.parseDouble(nowresult);
                            operator = '+';
                        }
                        changeOper();
                    break;
                case R.id.button_eq:
                    nowresult = replaceC(nowresult);
                    double beforeResult = resultNum;
                        if (resultNum != 0 && operator != 'a') {
                            if (operator == '/') {
                                resultNum = resultNum / Double.parseDouble(nowresult);
                            }
                            if (operator == '*') {
                                resultNum = resultNum * Double.parseDouble(nowresult);
                            }
                            if (operator == '+') {
                                resultNum = resultNum + Double.parseDouble(nowresult);
                            }
                            if (operator == '-') {
                                resultNum = resultNum - Double.parseDouble(nowresult);
                            }
                            inFirstInput = true;
                            getResult(resultNum);

                            textview(beforeResult, resultNum, nowresult);

                            operator = 'a';
                            changeOper();
                        }
                    break;
            }
    }

    // record 추가
    public void textview(double beforeResult, double resultNum, String nowresult) {
        TextView view1 = new TextView(this);
        view1.setText(df.format(beforeResult) + " " + operator + " " + nowresult + " = " + df.format(resultNum));
        view1.setTextSize(40);
        view1.setPadding(30, 0,0,30);
        view1.setTextColor(ContextCompat.getColor(getApplication(), R.color.white));

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.LEFT;
        view1.setLayoutParams(lp);

        record.addView(view1, 0);

        // record 클릭
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(13);
                drawerLayout.closeDrawer(drawerLayout);
                result.setText(df.format(resultNum));
            }
        });
    }

    // 숫자 입력
    public void notTrue(String a) {
        String nowresult = (String) result.getText();
        if(nowresult.contains(",")) {
            if(nowresult.contains(".") && a == "0") {
                result.setText(nowresult + "0");
            } else if(a == ".") {
                result.setText(nowresult + ".");
            } else {
                nowresult = replaceC(nowresult);
                beforeResult = Double.parseDouble(nowresult + a);
                result.setText(df.format(beforeResult));
            }
        } else {
            if(nowresult.contains(".") && a == "0") {
                result.setText(nowresult + "0");
            } else if(a == ".") {
                result.setText(nowresult + ".");
            } else {
                    beforeResult = Double.parseDouble(nowresult + a);
                    result.setText(df.format(beforeResult));
                }
            }
        beforeResult = 0;
        }

    // 숫자 포맷변경
    public void getResult(double resultNum) {
        result.setText(df.format(resultNum));
    }

    // , 제거
    public String replaceC(String nowresult) {
        if(nowresult.contains(",")) {
            nowresult = nowresult.replaceAll(",", "");
        }
        return nowresult;
    }

    @SuppressLint("ResourceAsColor")
    public void changeOper() {
        switch (operator) {
            case '+':
                findViewById(R.id.button_pl).setBackgroundColor(getApplication().getResources().getColor(R.color.after));

                findViewById(R.id.button_mn).setBackgroundColor(getApplication().getResources().getColor(R.color.before));
                findViewById(R.id.button_mp).setBackgroundColor(getApplication().getResources().getColor(R.color.before));
                findViewById(R.id.button_dv).setBackgroundColor(getApplication().getResources().getColor(R.color.before));
                break;
            case '-':
                findViewById(R.id.button_mn).setBackgroundColor(getApplication().getResources().getColor(R.color.after));

                findViewById(R.id.button_pl).setBackgroundColor(getApplication().getResources().getColor(R.color.before));
                findViewById(R.id.button_mp).setBackgroundColor(getApplication().getResources().getColor(R.color.before));
                findViewById(R.id.button_dv).setBackgroundColor(getApplication().getResources().getColor(R.color.before));
                break;
            case '*':
                findViewById(R.id.button_mp).setBackgroundColor(getApplication().getResources().getColor(R.color.after));

                findViewById(R.id.button_pl).setBackgroundColor(getApplication().getResources().getColor(R.color.before));
                findViewById(R.id.button_mn).setBackgroundColor(getApplication().getResources().getColor(R.color.before));
                findViewById(R.id.button_dv).setBackgroundColor(getApplication().getResources().getColor(R.color.before));
                break;
            case '/':
                findViewById(R.id.button_dv).setBackgroundColor(getApplication().getResources().getColor(R.color.after));

                findViewById(R.id.button_pl).setBackgroundColor(getApplication().getResources().getColor(R.color.before));
                findViewById(R.id.button_mn).setBackgroundColor(getApplication().getResources().getColor(R.color.before));
                findViewById(R.id.button_mp).setBackgroundColor(getApplication().getResources().getColor(R.color.before));
                break;
            case 'a':
                findViewById(R.id.button_pl).setBackgroundColor(getApplication().getResources().getColor(R.color.before));
                findViewById(R.id.button_mn).setBackgroundColor(getApplication().getResources().getColor(R.color.before));
                findViewById(R.id.button_mp).setBackgroundColor(getApplication().getResources().getColor(R.color.before));
                findViewById(R.id.button_dv).setBackgroundColor(getApplication().getResources().getColor(R.color.before));
                break;
        }
    }
}