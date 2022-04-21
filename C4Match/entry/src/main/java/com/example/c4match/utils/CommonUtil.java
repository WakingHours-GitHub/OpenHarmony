/*
 * Copyright (c) 2021 Huawei Device Co., Ltd.
 * Licensed under the Apache License,Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.c4match.utils;

import ohos.agp.components.Component;
import ohos.agp.utils.Point;
import ohos.agp.window.service.Display;
import ohos.agp.window.service.DisplayManager;
import ohos.app.Context;

import java.security.SecureRandom;
import java.util.Optional;

/**
 * CommonUtil
 *
 * @since 2021-01-11
 */
public class CommonUtil {
    private CommonUtil() {
    }

    /**
     * getRandomInt
     *
     * @param num Random [0-num)
     * @return int Random [0-num)
     */
    public static int getRandomInt(int num) {
        int randomInt = 0;
        if (num > 0) {
            SecureRandom random = new SecureRandom();
            randomInt = random.nextInt(num);
        }
        return randomInt;
    }

    /**
     * getTileHeight
     *
     * @param context Context
     * @param parentLayout the layout that fills the screen
     * @return title height int
     */
    public static int getTileHeight(Context context, Component parentLayout) {
        int layoutHeight = parentLayout.getHeight();
        int screenHeight = getScreenHeight(context);
        return screenHeight - layoutHeight;
    }

    /**
     * getScreenHeight
     *
     * @param context Context
     * @return screen height
     */
    private static int getScreenHeight(Context context) {
        DisplayManager displayManager = DisplayManager.getInstance();
        Optional<Display> optDisplay = displayManager.getDefaultDisplay(context);
        Point point = new Point(0, 0);
        if (optDisplay.isPresent()) {
            Display display = optDisplay.get();
            display.getSize(point);
        }
        return (int) point.position[1];
    }
}
