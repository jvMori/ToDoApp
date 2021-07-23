package com.moricode.todoapp.core.util

fun CharSequence?.isMoreThanCharactersLength(maxCharacters: Int?) = (this?.length ?: 0) > (maxCharacters ?: 0)