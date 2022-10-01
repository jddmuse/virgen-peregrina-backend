package com.virgen.peregrina.demo.util

import com.virgen.peregrina.demo.data.model.UserModel
import org.apache.commons.logging.LogFactory

inline fun <reified T> getLog() = LogFactory.getLog(T::class.java)