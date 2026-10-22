package com.example.dispensador_iot

object ProtocoloIoT {
    fun crearLecturaNivel(porcentaje: Int): String = "LECTURA;nivel;$porcentaje\n"
    fun crearLecturaPresencia(presente: Boolean): String = "LECTURA;presencia;${if (presente) 1 else 0}\n"
    fun crearComandoDispensar(): String = "CMD;dispensar;START\n"
    fun crearRespuestaAck(): String = "ACK;dispensar;OK\n"
}