package com.kriatov.alex.assignmentapp.utils

import android.annotation.SuppressLint
import com.kriatov.alex.assignmentapp.network.model.ModelConfig
import com.kriatov.alex.assignmentapp.ui.model.CheckableItem
import java.text.SimpleDateFormat

/**
 * Created by Alex Kriatov on 12/21/18
 */
class Mappers {
    companion object {
        fun mapModelConfigToCheckableItems(modelConfig: ModelConfig):List<CheckableItem>{
            val result: ArrayList<CheckableItem> = ArrayList()
            modelConfig.hits?.forEach { result.add(CheckableItem(it)) }
            return result
        }
        @SuppressLint("SimpleDateFormat")
        fun formatTime(time: Long):String{
            return SimpleDateFormat("dd-MM-yyyy").format(time)
        }
    }
}