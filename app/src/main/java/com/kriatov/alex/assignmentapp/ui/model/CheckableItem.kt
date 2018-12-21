package com.kriatov.alex.assignmentapp.ui.model

import com.kriatov.alex.assignmentapp.network.model.Hit

/**
 * Created by Alex Kriatov on 12/21/18
 */
data class CheckableItem(var hit: Hit, var isChecked: Boolean = false)