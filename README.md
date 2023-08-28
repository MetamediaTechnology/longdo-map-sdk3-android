Longdo Map API3 Android
--------

Longdo Map API3 Demo for Android is a sample project of how to develop geoinformatics application on Android devices. The API3 use map resources from Longdo.COM services yet allow developers to use other map tile standards such as TMS, WMS, and WMTS. The API3 provides most must have features allow developers to show markers on the map with a few lines. It also includes exclusive services such as POI tags, traffic events, and traffic camera.

Installation
--------
settings.gradle
```
repositories {
  maven { url 'https://maven.longdo.com/artifactory/libs-release-public' }
}
```

app/build.gradle
```
dependencies {
    api 'com.longdo.map:sdk3:1.2.0'
}
```

How do I use Longdo Map API3?
-------------------
Check out the [documentation][1] for pages on a variety of topics, and see the [Longdo Map REST API][2].

Bugs and feature requests
--------
Have a bug or a feature request? Please feel free to contact us at [info@mm.co.th](info@mm.co.th).

Copyright and license
--------
Longdo Map API3 copyright 2012-2021 Metamedia Technology Co., Ltd. Demo code released under [Apache 2.0 license](https://github.com/MetamediaTechnology/longdo-map-demo-ios/blob/master/LICENSE).

[1]: https://map.longdo.com/docs3/
[2]: http://api.longdo.com/map/doc/rest.php
