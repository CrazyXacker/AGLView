/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.glview.util;

import android.hardware.Camera;
import android.os.Build;
import android.view.View;

public class ApiHelper {
    public interface VERSION_CODES {
        // These value are copied from Build.VERSION_CODES
        int GINGERBREAD_MR1 = 10;
        int HONEYCOMB = 11;
        int HONEYCOMB_MR1 = 12;
        int HONEYCOMB_MR2 = 13;
        int ICE_CREAM_SANDWICH = 14;
        int ICE_CREAM_SANDWICH_MR1 = 15;
        int JELLY_BEAN = 16;
        int JELLY_BEAN_MR1 = 17;
        int JELLY_BEAN_MR2 = 18;
    }

    public static final boolean HAS_VIEW_SYSTEM_UI_FLAG_LAYOUT_STABLE = hasField(View.class, "SYSTEM_UI_FLAG_LAYOUT_STABLE");

    public static final boolean HAS_SET_SYSTEM_UI_VISIBILITY = hasMethod(View.class, "setSystemUiVisibility", int.class);

    public static final boolean HAS_MOTION_EVENT_TRANSFORM = Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB;

    public static final boolean HAS_FACE_DETECTION;
    static {
        boolean hasFaceDetection = false;
        try {
            Class<?> listenerClass = Class.forName("android.hardware.Camera$FaceDetectionListener");
            hasFaceDetection =
                    hasMethod(Camera.class, "setFaceDetectionListener", listenerClass) &&
                    hasMethod(Camera.class, "startFaceDetection") &&
                    hasMethod(Camera.class, "stopFaceDetection") &&
                    hasMethod(Camera.Parameters.class, "getMaxNumDetectedFaces");
        } catch (Throwable ignored) {
        }
        HAS_FACE_DETECTION = hasFaceDetection;
    }

    private static boolean hasField(Class<?> klass, String fieldName) {
        try {
            klass.getDeclaredField(fieldName);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private static boolean hasMethod(
            Class<?> klass, String methodName, Class<?> ... paramTypes) {
        try {
            klass.getDeclaredMethod(methodName, paramTypes);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
}
