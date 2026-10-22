package com.example.dispensador_iot

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dispensadoriot.databinding.ActivityOwnerBinding
class DueñoActivity : AppCompatActivity (){
    private lateinit var binding: ActivityDueñoBinding
    override fun onCreate(savedIntState: Bundle?){
        super.onCreate(savedIntState)
        binding = ActivityDueñoBinding.inflate(layoutInflate)
        setContentView(binding.root)
        binding.btnDispensar.setOnClickListener{
            val cmd = ProtocoloIoT.crearComandoDispensar()
            Toast.makeText(this, "Enviando orden de dispensar...",Toast.SHORT).show()

        }
    }
    fun prosesarMensajeEntrante(mensaje: String){
        val partes = mensaje.trim().split(";")
        if (partes.size == 3){
            when(partes[0]){
                "LECTURA" ->{
                    if (partes[1]== "Nivel"){
                        val nivel = partes[2].toIntOrNull()?:0
                        binding.tvComidaPanel.text = "$nivel"
                        if (nivel < 20){
                            Toast.makeText(this, "¡Alerta! Nivel de comida menor al 20%", Toast.LENGTH_LONG).show()
                        }else if (partes[1]== "presencia"){
                            val enPlato = partes[2] == "1"
                            binding.tvPresenciaPanel.text = if (enPlato) "Mascota: en el plato" else "mascota: Ausente"
                        }
                    }
                    "ACK" ->{
                        if (partes[1]== "dipensar" && partes[2]== "OK"){
                            Toast.makeText(this, "Dispenso exitoso", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}