import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:android_environment_sensors/android_environment_sensors.dart';

void main() {
  const MethodChannel channel = MethodChannel('android_environment_sensors');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });


}
