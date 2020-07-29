import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  int _batteryLevel;

  Future getBatteryLevel() async {
    const flatform = MethodChannel('demo.flutter/battery');
    try {
      final batteryLevel = await flatform.invokeMethod('getBatteryLevel');
      setState(() {
        _batteryLevel = batteryLevel;
      });
    } on PlatformException catch (error) {
      setState(() {
        _batteryLevel = null;
      });
    }
    //course.flutter.dev/battery
  }

  @override
  void initState() {
    getBatteryLevel();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Battery level",
      home: Scaffold(
        appBar: AppBar(
          title: Text("Battery Level"),
        ),
        body: Center(child: Text("Battery Level :$_batteryLevel")),
      ),
    );
  }
}
