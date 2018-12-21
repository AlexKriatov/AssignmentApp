package com.kriatov.alex.assignmentapp.core

import com.kriatov.alex.assignmentapp.network.Api

/**
 * Created by Alex Kriatov on 12/21/18
 */
interface ApiProvider {
    fun getApi(): Api
}