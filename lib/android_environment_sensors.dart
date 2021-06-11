
import 'dart:async';
import 'dart:convert';

import 'package:flutter/services.dart';

class AndroidEnvironmentSensors {

  static AndroidEnvironmentSensors _instance;

  AndroidEnvironmentSensors._internal();

  factory AndroidEnvironmentSensors() {
    if (_instance == null) {
      _instance = AndroidEnvironmentSensors._internal();
    }

    return _instance;
  }


  static const TYPE_AMBIENT_TEMPERATURE = 13;
  static const TYPE_LIGHT =5;
  static const TYPE_PRESSURE =6;
  static const TYPE_RELATIVE_HUMIDITY =12;
  // static const TYPE_TEMPERATURE =5;

  static const MethodChannel _SENSOR_CHANNEL =
  const MethodChannel('android_environment_sensors');
  static const EventChannel  _PRESSURE_SENSOR_CHANNEL =
   EventChannel('android_environment_sensors/pressure');
  static const EventChannel  _LIGHT_SENSOR_CHANNEL =
  EventChannel('android_environment_sensors/light');
  static const EventChannel  _HUMIDITY_SENSOR_CHANNEL =
  EventChannel('android_environment_sensors/humidity');
  // static const EventChannel  _TEMPERATURE_SENSOR_CHANNEL =
  // EventChannel('android_environment_sensors/device_temperature');
  static const EventChannel  _AMBIENT_TEMPERATURE_SENSOR_CHANNEL =
  EventChannel('android_environment_sensors/temperature');
 Future<bool> isExist(int type) async{
    final bool result = await _SENSOR_CHANNEL.invokeMethod('isExist',{"type":type});

    return result;
}


  Stream get pressureStream => _PRESSURE_SENSOR_CHANNEL.receiveBroadcastStream();

  Stream get temperatureStream => _AMBIENT_TEMPERATURE_SENSOR_CHANNEL.receiveBroadcastStream();
  Stream get lightStream => _LIGHT_SENSOR_CHANNEL.receiveBroadcastStream();
  Stream get humidityStream => _HUMIDITY_SENSOR_CHANNEL.receiveBroadcastStream();



}
