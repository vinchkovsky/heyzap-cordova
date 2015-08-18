//  CDVInterstitialAd.java
//
//  Copyright 2015 Heyzap, Inc. All Rights Reserved
//
//  Permission is hereby granted, free of charge, to any person
//  obtaining a copy of this software and associated documentation
//  files (the "Software"), to deal in the Software without
//  restriction, including without limitation the rights to use,
//  copy, modify, merge, publish, distribute, sublicense, and/or sell
//  copies of the Software, and to permit persons to whom the
//  Software is furnished to do so, subject to the following
//  conditions:
//
//  The above copyright notice and this permission notice shall be
//  included in all copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
//  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
//  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
//  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
//  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
//  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
//  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
//  OTHER DEALINGS IN THE SOFTWARE.

package com.heyzap.cordova.ads;

import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;

import com.heyzap.sdk.ads.InterstitialAd;
import com.heyzap.sdk.extensions.unity3d.UnityHelper;

public class CDVInterstitialAd extends CDVHeyzapAbstractPlugin {
    private static final String TAG = "CDVInterstitialAd";

    public void fetch(final JSONArray args, final CallbackContext callbackContext) {
        String tag = args.optString(0);

        if (tag.isEmpty()) {
            InterstitialAd.fetch();
        } else {
            InterstitialAd.fetch(tag);
        }

        callbackContext.success();
    }

    public void show(final JSONArray args, final CallbackContext callbackContext) {
        String tag = args.optString(0);

        if (tag.isEmpty()) {
            displayIfAvailable(callbackContext);
        } else {
            displayIfAvailable(callbackContext, tag);
        }
    }

    @Override
    public void setListener(CDVListener listener) {
        InterstitialAd.setOnStatusListener(listener);
    }

    private void displayIfAvailable(final CallbackContext callbackContext) {
        if (InterstitialAd.isAvailable()) {
            InterstitialAd.display(cordova.getActivity());
            callbackContext.success();

        } else {
            callbackContext.error("Interstitial Ad ad is not available.");
        }
    }

    private void displayIfAvailable(final CallbackContext callbackContext, String tag) {
        if (InterstitialAd.isAvailable(tag)) {
            InterstitialAd.display(cordova.getActivity(), tag);
            callbackContext.success();

        } else {
            callbackContext.error("Interstitial Ad is not available");
        }
    }

    // Chartboost Cross-promo
    public void chartboostFetchForLocation(final JSONArray args, final CallbackContext callbackContext) {
        String location = args.optString(0);

        try {
            UnityHelper.chartboostLocationFetch(location);
            callbackContext.success();

        } catch (Exception e) {
            String msg = "Could not fetch Chartboost for location: " + location + ", Error: " + e.getMessage();
            Log.e(TAG, msg);
            e.printStackTrace();

            callbackContext.error(msg);
        }
    }

    public void chartboostShowForLocation(final JSONArray args, final CallbackContext callbackContext) {
        String location = args.optString(0);

        try {
            UnityHelper.chartboostLocationShow(location);
            callbackContext.success();
            
        } catch (Exception e) {
            String msg = "Could not show Chartboost for location: " + location + ", Error: " + e.getMessage();
            Log.e(TAG, msg);
            e.printStackTrace();

            callbackContext.error(msg);
        }
    }

    public void chartboostIsAvailableForLocation(final JSONArray args, final CallbackContext callbackContext) {
        String location = args.optString(0);

        try {
            boolean available = UnityHelper.chartboostLocationIsAvailable(location);
            PluginResult result = new PluginResult(PluginResult.Status.OK, available);
            callbackContext.sendPluginResult(result);
            
        } catch (Exception e) {
            String msg = "Could not determine if Chartboost is available for location: " + location + ", Error: " + e.getMessage();
            Log.e(TAG, msg);
            e.printStackTrace();

            callbackContext.error(msg);
        }
    }
}
