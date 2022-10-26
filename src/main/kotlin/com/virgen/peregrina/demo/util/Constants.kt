package com.virgen.peregrina.demo.util

const val METHOD_CALLED = "METHOD CALLED:"
const val PARAMS = "PARAMS:"

const val ERROR_PILGRIMAGE_ON_RANGE = "No se pudo guardar esta peregrinación ya que se cruzan los dias con otra peregrinación ya registrada"
const val ERROR_START_DATE_GREATER_THAN_END_DATE =  "La fecha de inicio debe ser inferior a la fecha de Finalización"

const val POINT = "."
const val SPLIT = "-"
const val DOUBLE_POINT = ":"
const val SLASH = "/"
const val STANDAR_STR = "standar"
const val N_A = "N/A"

const val PILGRIMAGE_REPOSITORY_NAME = "pilgrimageRepository"
const val PILGRIMAGE_SERVICE_NAME = "pilgrimageService"
const val PILGRIMAGE_CONVERTER_NAME = "pilgrimageConverter"

object PilgrimageState {
    const val FINISHED = "Finished"
    const val PENDING = "Pending"
    const val IN_PROGRESS = "In Progress"
}