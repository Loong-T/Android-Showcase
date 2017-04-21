/*
 *    Copyright 2017 Xuqiang ZHENG
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package in.nerd_is.dragtodismisslayout;

/**
 * @author Xuqiang ZHENG on 2017/4/10.
 */
public abstract class DragToDismissCallback {

    /**
     * Called for each drag event.
     *
     * @param elasticOffset       Indicating the drag offset with elasticity applied i.e. may
     *                            exceed 1.
     * @param elasticOffsetPixels The elastically scaled drag distance in pixels.
     * @param rawOffset           Value from [0, 1] indicating the raw drag offset i.e.
     *                            without elasticity applied. A value of 1 indicates that the
     *                            dismiss distance has been reached.
     * @param rawOffsetPixels     The raw distance the user has dragged
     */
    void onDrag(float elasticOffset, float elasticOffsetPixels,
                float rawOffset, float rawOffsetPixels) {
    }

    /**
     * Called when dragging is released and has exceeded the threshold dismiss distance.
     */
    void onDragDismissed() {
    }

}
