package com.demo.network.common

import com.demo.core.bases.ResultException
import com.demo.network.R

open class NetworkException(
    messageResource: Int = R.string.error_unexpected,
    cause: Throwable? = null
) : ResultException(messageResource, cause)