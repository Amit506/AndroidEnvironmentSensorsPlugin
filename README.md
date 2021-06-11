# AndroidEnvironmentSensorsPlugin
Environmental sensor plugin for flutter<br />
Android only

<br/>***supported sensor*** <br/>
- TYPE_AMBIENT_TEMPERATURE
- TYPE_LIGHT	
- TYPE_PRESSURE	
- TYPE_RELATIVE_HUMIDITY
- TYPE_TEMPERATURE
<br/>for more information on [environment sensors](https://developer.android.com/guide/topics/sensors/sensors_environment)<br />




<br/>**Usage**<br />
*Check if sensor is available or not*<br />

```
 AndroidEnvironmentSensors androidEnvironmentSensors =AndroidEnvironmentSensors();
 final exist = await androidEnvironmentSensors.isExist(AndroidEnvironmentSensors.TYPE_LIGHT);
```
*if sensor exist then listen to it*<br />

```
 StreamSubscription  lightSubscription = androidEnvironmentSensors.lightStream.listen((event) {
      print(event);
    });
 
```

*Remember to cancel subscription when done*
```
lightSubscription.cancel();
```

check [Example](https://github.com/Amit506/AndroidEnvironmentSensorsPlugin/blob/master/example/lib/main.dart)
