package com.example.httplearning;
//通过fastjson发送与解析请求
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public String hostIP = /*"192.168.0.108:18081";*/"10.6.206.20:30549";
    public String userId = "6D的安卓測試機";
    public String vin = "001";

    private static final String TAG = "";
    public TextView replyTextView,requestTextView;
    public VehicleCondition vehicleCondition;
    public RequestVehicleCondition requestVehicleCondition;
    public VideoRequest videoRequest;
    public VideoReply videoReply;
    public AutoParkingRequest autoParkingRequest;
    public AutoParkingReply autoParkingReply;
    public CallCarRequest callCarRequest;
    public CallCarReply callCarReply;
    public IotHubSettingsRequest iotHubSettingsRequest;
    public IotHubSettings iotHubSettings;
    public ShutParkingRequest shutParkingRequest;
    public ShutParkingReply shutParkingReply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestTextView=findViewById(R.id.send_text);
        replyTextView =findViewById(R.id.reply_text);
        Button postVideo = findViewById(R.id.post_Video);
        postVideo.setOnClickListener(this);
        Button postVehicleCondition = findViewById(R.id.post_Vehicle_Condition);
        postVehicleCondition.setOnClickListener(this);

        Button postAutoMove = findViewById(R.id.post_Automove);
        postAutoMove.setOnClickListener(this);

        Button postTurnOffMove = findViewById(R.id.post_TurnOffMove);
        postTurnOffMove.setOnClickListener(this);

        Button postCallForCar = findViewById(R.id.post_callForCar);
        postCallForCar.setOnClickListener(this);

        Button postIotSettings = findViewById(R.id.post_IotHub_Setting);
        postIotSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.post_Vehicle_Condition:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            requestVehicleCondition = new RequestVehicleCondition();
                            requestVehicleCondition.setUserId(userId);
                            requestVehicleCondition.setVin(vin);
                            String VehicleConditionalJson =JSON.toJSONString(requestVehicleCondition);
                            requestTextView.setText(VehicleConditionalJson);
                            OkHttpClient client=new OkHttpClient();
                            Request request= new Request.Builder()
                                    .url("http://"+hostIP+"/appBackend/vehicleCondition")
                                    .post(RequestBody.create(MediaType.parse("application/json"),VehicleConditionalJson))
                                    .build();//创造HTTP请求
                            //执行发送的指令
                            Response response = client.newCall(request).execute();
                            String responseString=response.body().string();
                            vehicleCondition = new VehicleCondition();
                            vehicleCondition = JSON.parseObject(responseString,VehicleCondition.class);
                            Log.d(TAG, "run: "+vehicleCondition.toString()+"\n"+responseString);


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"請求車況中",Toast.LENGTH_SHORT).show();
                                    replyTextView.setText(vehicleCondition.toString()+"\n"+responseString);

                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"請求車況失败！",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }).start();
                break;

            case R.id.post_Video:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            videoRequest = new VideoRequest();
                            videoRequest.setUserId(userId);
                            videoRequest.setVin(vin);
                            videoRequest.setVideo_type("1");
                            videoRequest.setServicetype("1");
                            String videoRequestJson =JSON.toJSONString(videoRequest);
                            requestTextView.setText(videoRequestJson);
                            Log.d(TAG,videoRequestJson);
                            OkHttpClient videoClient=new OkHttpClient();
                            Request videoRequest= new Request.Builder()
                                    .url("http://"+hostIP+"/appBackend/videoRequest")
                                    .post(RequestBody.create(MediaType.parse("application/json"),videoRequestJson))
                                    .build();//创造HTTP请求
                            //执行发送的指令
                            Response videoResponse = videoClient.newCall(videoRequest).execute();
                            String videoResponseString=videoResponse.body().string();
                            replyTextView.setText(videoResponseString);
                            videoReply = new VideoReply();
                            videoReply = JSON.parseObject(videoResponseString,VideoReply.class);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"請求視頻中",Toast.LENGTH_LONG).show();
//                                    replyTextView.setText(videoReply.toString());
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"請求視頻失败！",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }).start();
                break;

            case R.id.post_Automove:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            videoRequest = new VideoRequest();
                            videoRequest.setUserId(userId);
                            videoRequest.setVin(vin);
                            videoRequest.setVideo_type("1");
                            videoRequest.setServicetype("0");
                            String videoRequestJson =JSON.toJSONString(videoRequest);
                            requestTextView.setText(videoRequestJson);
                            Log.d(TAG,videoRequestJson);
                            OkHttpClient videoClient=new OkHttpClient();
                            Request videoRequest= new Request.Builder()
                                    .url("http://"+hostIP+"/appBackend/videoRequest")
                                    .post(RequestBody.create(MediaType.parse("application/json"),videoRequestJson))
                                    .build();//创造HTTP请求
                            //执行发送的指令
                            Response videoResponse = videoClient.newCall(videoRequest).execute();
                            String videoResponseString=videoResponse.body().string();
                            replyTextView.setText(videoResponseString);
                            videoReply = new VideoReply();
                            videoReply = JSON.parseObject(videoResponseString,VideoReply.class);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"請求視頻中",Toast.LENGTH_LONG).show();
//                                    replyTextView.setText(videoReply.toString());
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"請求視頻失败！",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }).start();
                /*new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            autoParkingRequest = new AutoParkingRequest();
                            autoParkingRequest.setUserId(userId);
                            autoParkingRequest.setVin(vin);
                            autoParkingRequest.setParkingType("2");
                            autoParkingRequest.setParkingDirection("1");
                            autoParkingRequest.setParkingOutWay("1");
                            autoParkingRequest.setPathData("1");
                            String autoParkingRequestJson =JSON.toJSONString(autoParkingRequest);
                            requestTextView.setText(autoParkingRequestJson);
                            Log.d(TAG,autoParkingRequestJson );
                            OkHttpClient autoParkingClient=new OkHttpClient();
                            Request autoParkingRequest= new Request.Builder()
                                    .url("http://"+hostIP+"/appBackend/autoParking")
                                    .post(RequestBody.create(MediaType.parse("application/json"),autoParkingRequestJson))
                                    .build();//创造HTTP请求
                            //执行发送的指令
                            Response autoParkingResponse = autoParkingClient.newCall(autoParkingRequest).execute();
                            String autoParkingResponseString=autoParkingResponse.body().string();
                            replyTextView.setText(autoParkingResponseString);
                            autoParkingReply = new AutoParkingReply();
                            autoParkingReply = JSON.parseObject(autoParkingResponseString,AutoParkingReply.class);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"請求視頻中",Toast.LENGTH_LONG).show();
                                    replyTextView.setText(autoParkingReply.toString());
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"請求自动泊车失败！",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }).start();*/
                break;

            case R.id.post_TurnOffMove:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            shutParkingRequest = new ShutParkingRequest();
                            shutParkingRequest.setUserId(userId);
                            shutParkingRequest.setVin(vin);
                            shutParkingRequest.setParkingServiceType(1);
                            String shutParkingRequestJson =JSON.toJSONString(shutParkingRequest);
                            requestTextView.setText(shutParkingRequestJson);
                            OkHttpClient shutParkingClient=new OkHttpClient();
                            Request shutParkingRequest= new Request.Builder()
                                    .url("http://"+hostIP+"/appBackend/closeParking")
                                    .post(RequestBody.create(MediaType.parse("application/json"),shutParkingRequestJson))
                                    .build();//创造HTTP请求
                            //执行发送的指令
                            Response shutParkingResponse = shutParkingClient.newCall(shutParkingRequest).execute();
                            String shutParkingResponseString=shutParkingResponse.body().string();
                            replyTextView.setText(shutParkingResponseString);
                            shutParkingReply = new ShutParkingReply();
                            shutParkingReply = JSON.parseObject(shutParkingResponseString,ShutParkingReply.class);


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"請求視頻中",Toast.LENGTH_LONG).show();
                                    replyTextView.setText(shutParkingReply.toString());
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"請求自动泊车失败！",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }).start();
                break;

            case R.id.post_callForCar:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            callCarRequest = new CallCarRequest();
                            callCarRequest.setUserId(userId);
                            callCarRequest.setVin(vin);
                            callCarRequest.setParkingType("3");
                            callCarRequest.setPathData("一汽NBD總部");
                            String callCarRequestJson =JSON.toJSONString(callCarRequest);
                            requestTextView.setText(callCarRequestJson);
                            OkHttpClient callCarClient=new OkHttpClient();
                            Request callCarRequest= new Request.Builder()
                                    .url("http://"+hostIP+"/appBackend/callForCar")
                                    .post(RequestBody.create(MediaType.parse("application/json"),callCarRequestJson))
                                    .build();//创造HTTP请求
                            replyTextView.setText("正在叫車。。。");
                            //执行发送的指令
                            Response callCarResponse = callCarClient.newCall(callCarRequest).execute();
                            String callCarResponseString=callCarResponse.body().string();
                            replyTextView.setText(callCarResponseString);
                            callCarReply = new CallCarReply();
                            callCarReply = JSON.parseObject(callCarResponseString,CallCarReply.class);


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"請求視頻中",Toast.LENGTH_LONG).show();
                                    replyTextView.setText(callCarReply.toString());
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"請求自动泊车失败！",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }).start();
                break;

            case R.id.post_IotHub_Setting:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            iotHubSettingsRequest = new IotHubSettingsRequest();
                            iotHubSettingsRequest.setUserId(userId);
                            iotHubSettingsRequest.setVin(vin);
                            String iotHubSettingsRequestJson =JSON.toJSONString(iotHubSettingsRequest);
                            requestTextView.setText(iotHubSettingsRequestJson);
                            OkHttpClient iotHubSettingsClient=new OkHttpClient();
                            Request iotHubSettingsRequest= new Request.Builder()
                                    .url("http://"+hostIP+"/appBackend/getIotHubSetting")
                                    .post(RequestBody.create(MediaType.parse("application/json"),iotHubSettingsRequestJson))
                                    .build();//创造HTTP请求
                            replyTextView.setText("正在叫車。。。");
                            //执行发送的指令
                            Response iotHubSettingsResponse = iotHubSettingsClient.newCall(iotHubSettingsRequest).execute();
                            String iotHubSettingsResponseString=iotHubSettingsResponse.body().string();
                            replyTextView.setText(iotHubSettingsResponseString);
                            iotHubSettings = new IotHubSettings();
                            iotHubSettings = JSON.parseObject(iotHubSettingsResponseString,IotHubSettings.class);


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"請求視頻中",Toast.LENGTH_LONG).show();
                                    replyTextView.setText(iotHubSettings.toString());
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"請求自动泊车失败！",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                }).start();
                break;

        }
    }
}