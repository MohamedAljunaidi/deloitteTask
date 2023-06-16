package com.demo.core.extensions

import java.lang.reflect.ParameterizedType

/**
 * Created by Mohammad Aljunaidi on 15/06/2023.
 */

fun <GENERIC_CLASS> Any.getGenericClass(pos: Int = 0) =
    (javaClass.genericSuperclass as? ParameterizedType)?.actualTypeArguments?.get(pos) as? Class<GENERIC_CLASS>
        ?: throw IllegalArgumentException("there is no generic param at position $pos")