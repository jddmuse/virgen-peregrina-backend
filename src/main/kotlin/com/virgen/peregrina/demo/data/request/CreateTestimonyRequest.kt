package com.virgen.peregrina.demo.data.request

import com.virgen.peregrina.demo.data.model.TestimonyModel
import java.util.*

data class CreateTestimonyRequest(
    val pilgrimageId: Long,
    val date: Date,
    val value: String
)