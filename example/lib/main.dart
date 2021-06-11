import 'package:flutter/material.dart';

import 'package:android_environment_sensors/android_environment_sensors.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  bool isExist = false;
  AndroidEnvironmentSensors androidEnvironmentSensors =
      AndroidEnvironmentSensors();
  @override
  void initState() {
    super.initState();
  }
//

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: GestureDetector(
            onTap: () {
              print('tabbed');
            },
            child: Center(
                child: ListTile(
                    title: Text('Light Sensor'),
                    trailing: !isExist
                        ? TextButton(
                            style: OutlinedButton.styleFrom(),
                            onPressed: () async {
                              final exist =
                                  await androidEnvironmentSensors.isExist(
                                      AndroidEnvironmentSensors.TYPE_LIGHT);
                              print(isExist.toString());
                              setState(() {
                                isExist = exist;
                              });
                            },
                            child: Text('connect'),
                          )
                        : StreamBuilder(
                            stream: androidEnvironmentSensors.lightStream,
                            builder: (_, snap) {
                              if (snap.hasData) {
                                return Text(snap.data.toString());
                              } else {
                                return Text('connecting');
                              }
                            }))),
          ),
        ),
      ),
    );
  }
}
