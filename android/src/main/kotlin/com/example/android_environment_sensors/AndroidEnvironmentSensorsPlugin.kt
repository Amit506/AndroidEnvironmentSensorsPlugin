package com.example.android_environment_sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.util.logging.StreamHandler


/** AndroidEnvironmentSensorsPlugin */
class AndroidEnvironmentSensorsPlugin: FlutterPlugin, MethodCallHandler {


   private  val _SENSORS_CHANNEL_NAME ="android_environment_sensors";
    private  val _PRESSURE_CHANNEL_NAME ="android_environment_sensors/pressure";
    private  val _TEMPERATURE_CHHANEL_NAME= "android_environment_sensors/temperature"
    private  val _LIGHT_CHANNEL_NAME ="android_environment_sensors/light"
    private  val _HUMIDITY_CHANNEL_NAME ="android_environment_sensors/humidity"
    private  val _DEVICE_TEMPERATUE_CHANNEL_NAME ="android_environment_sensors/device_temperature"
    private lateinit  var isExistSensor : MethodChannel
    private  lateinit var  pressureChannel:EventChannel
    private  lateinit var  temperatureChannel:EventChannel
    private  lateinit var humidityChannel : EventChannel
    private  lateinit var  lightChannel :EventChannel
//    private  lateinit var  deviceTemChannel: EventChannel
    private  lateinit var  humidityStreamHandler :SensorHandler
    private  lateinit var  lightStreamHandler: SensorHandler
//    private  lateinit var  deviceTempStreamHandler: SensorHandler
    private  lateinit var pressurestreamhandler: SensorHandler
private  lateinit var  temperatureStreamHandler: TemperatureSensorHandler
    private  lateinit var  sensorManager: SensorManager

 private lateinit var pluginBinding :FlutterPlugin. FlutterPluginBinding
  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
pluginBinding=flutterPluginBinding
    val context=flutterPluginBinding.applicationContext
    sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

// channel to check sensor exist or not
    isExistSensor = MethodChannel(flutterPluginBinding.binaryMessenger,_SENSORS_CHANNEL_NAME)
isExistSensor.setMethodCallHandler(this)
      // ambient temperature
temperatureChannel=EventChannel(flutterPluginBinding.binaryMessenger,_TEMPERATURE_CHHANEL_NAME)
      temperatureStreamHandler= TemperatureSensorHandler(sensorManager,Sensor.TYPE_AMBIENT_TEMPERATURE)
      temperatureChannel.setStreamHandler(temperatureStreamHandler)
      // pressure
pressureChannel= EventChannel(flutterPluginBinding.binaryMessenger,_PRESSURE_CHANNEL_NAME)

      pressurestreamhandler = SensorHandler(sensorManager,Sensor.TYPE_PRESSURE)
      pressureChannel.setStreamHandler(pressurestreamhandler)
      //light channel
      lightChannel = EventChannel(flutterPluginBinding.binaryMessenger,_LIGHT_CHANNEL_NAME)
      lightStreamHandler = SensorHandler(sensorManager,Sensor.TYPE_LIGHT)
      lightChannel.setStreamHandler(lightStreamHandler)
      //humidity channel
      humidityChannel = EventChannel(flutterPluginBinding.binaryMessenger,_HUMIDITY_CHANNEL_NAME)
      humidityStreamHandler = SensorHandler(sensorManager,Sensor.TYPE_RELATIVE_HUMIDITY)
      humidityChannel.setStreamHandler(humidityStreamHandler)





  }



  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    isExistSensor.setMethodCallHandler(null)
      pressureChannel.setStreamHandler(null)
      lightChannel.setStreamHandler(null)
      humidityChannel.setStreamHandler(null)
      temperatureChannel.setStreamHandler(null)
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    val context=pluginBinding.applicationContext
    sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

  when(call.method){
      "isExist"-> {

          val type: Int? = call.argument<Int>("type")?.toInt()
          result.success(  sensorManager.getSensorList(type!!).isNotEmpty())
      }


      else-> {

      result.notImplemented()
  }


  }



  }




}
