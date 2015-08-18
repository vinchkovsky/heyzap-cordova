Heyzap Cordova Plugin
=====================

**Heyzap SDK Version: 9.0.6**

This a custom Heyzap SDK plugin for Apache Cordova that has the ability to call Chartboost directly for those that wish to show Chartboost cross-promotion Ads.

Installing:
```
cordova plugin add https://github.com/Heyzap/heyzap-cordova.git#chartboost-crosspromo
```

Supported Platforms
-------------------
- iOS (cordova-ios engine 3.8.0 and later)
- Android (cordova-android engine 4.0.0 and later)

External Libraries Used
-----------------------
- [ES6-Promise](https://github.com/jakearchibald/es6-promise) by Jake Archibald.

Documentation
-------------
Please visit our [documentation page](https://developers.heyzap.com/docs/cordova_sdk_setup_and_requirements) for guides on how to integrate heyzap with your Cordova app.

Chartboost Cross-Promotion
--------------------------

- Fetch Ad: `HeyzapAds.InterstitialAd.chartboostFetch(location)`
	- `location` (string) - Chartboost location, pass in your cross-promo location here.
    - returns: `Promise` - An ES-6 style promise if the native call succeeded for failed.
        - **Note:** A success callback simply indicates that the native call to fetch an Ad succeeded. It _does not_ necessarily mean that the ad is ready to display. Call `chartboostIsAvailable` to determine if the ad is ready to display.
    - example:

        ```javascript
        HeyzapAds.InterstitialAd.chartboostFetch("Default").then(function() {
            // Native call succeeded
            // Note: This *does not* necessarily mean that the Ad is available for display.

        }, function(err) {
            // Native call failed
        });
        ```

- Check if Ad is Available: `HeyzapAds.InterstitialAd.chartboostIsAvailable(location)`
    - `location` (string) - Chartboost location, pass in your cross-promo location here.
    - returns: `Promise` - An ES-6 style promise if the native call succeeded for failed. The success callback wil have a `boolean` as its first parameter that will indicate if the Ad is available or not.
    - example:

        ```javascript
        HeyzapAds.InterstitialAd.chartboostIsAvailable("Default").then(function(available) {
            if (available) {
                return HeyzapAds.InterstitialAd.chartboostShow("Default");
            }

        }).then(...);
        ```

- Show Ad: `HeyzapAds.InterstitialAd.chartboostShow(location)`
    - `location` (string) - Chartboost location, pass in your cross-promo location here.
    - returns: `Promise` - An ES-6 style promise if the native call succeeded for failed.
    - example:

        ```javascript
        HeyzapAds.InterstitialAd.chartboostShow("Default").then(function() {
            // Native call succeeded, Ad is being displayed

        }, function(err) {
            // Native call failed
        });
        ```