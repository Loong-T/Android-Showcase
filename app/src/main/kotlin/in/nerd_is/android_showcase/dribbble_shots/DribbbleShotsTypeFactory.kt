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

package `in`.nerd_is.android_showcase.dribbble_shots

import `in`.nerd_is.android_showcase.databinding.DribbbleShotsItemBinding
import `in`.nerd_is.android_showcase.dribbble.model.Shot
import `in`.nerd_is.recycler_simplification.TypeFactory
import `in`.nerd_is.recycler_simplification.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * @author Xuqiang ZHENG on 2017/6/16.
 */
class DribbbleShotsTypeFactory : TypeFactory() {

    override fun addTypeRules() {
        add(Shot::class.java, ShotHolder.Companion::newInstance)
    }

    class ShotHolder(val binding: DribbbleShotsItemBinding) :
            ViewHolder<Shot>(binding.root) {

        companion object {
            fun newInstance(inflater: LayoutInflater, container: ViewGroup): ShotHolder {
                val binding = DribbbleShotsItemBinding.inflate(inflater, container, false)
                return ShotHolder(binding)
            }
        }

        override fun render(data: Shot) {
            binding.shot = data

            val lp = binding.imageView.layoutParams
            lp.height = (data.height.toFloat() / data.width * binding.imageView.width).toInt()

            binding.executePendingBindings()
        }
    }
}