/*
 * Copyright (C) 2013 Chen Hui <calmer91@gmail.com>
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

package master.flame.danmaku.danmaku.renderer.android;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.renderer.Renderer;

import java.util.Iterator;

public class DanmakuRenderer extends Renderer {

    public static int ANTI_ALIAS_DISABLE_SIZE = 30;

    public static int ANTI_ALIAS_ENABLE_SIZE = 5;

    @Override
    public void draw(IDisplayer disp, IDanmakus danmakus) {
        Danmakus drawItems = (Danmakus) danmakus;
        IDanmakuIterator itr = drawItems.iterator();

        while (itr.hasNext()) {

            BaseDanmaku drawItem = itr.next();

            if (drawItem.isTimeOut()) {
                continue;
            }

            // measure
            if (!drawItem.isMeasured()) {
                drawItem.measure(disp);
            }

            // layout
            DanmakusRetainer.fix(drawItem, disp);

            // draw
            if (drawItem.isOutside()==false && drawItem.isShown() && drawItem.getLeft() < disp.getWidth() && drawItem.getRight() > 0) {

                drawItem.draw(disp);

            }

        }
    }

    @Override
    public void clear() {
        DanmakusRetainer.clear();
    }

}
