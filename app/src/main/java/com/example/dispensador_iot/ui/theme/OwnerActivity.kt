package com.example.dispensador_iot.ui.theme
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dispensadoriot.databinding.ActivityOwnerBinding
class OwnerActivity : AppCompatActivity(){
    private lateinit var binding: ActivityOwnerBinding
    override fun OnCreate(savedIntState: Bundle?){
        super.OnCreate(savedIntState)
        binding = ActivityOwnerBinding.inflate(layoutInflate)
        setContentview(binding.root)
        binding.btnConectar.setOnClickListener{
            Toast.makeText(this, "Buscando/Conectando a Dispensador...", Toast.SHORT).show()
        }
        binding.btnDipensar.setOnClickListener{
            val cmd = "cmd;Dipensar;START\n"
            Toast.makeText(this, "orden enviada: Dispensar Ración", Toast.SHORT).show()
        }
    }
    fun actualizarEstado(mensaje: String){
        val partes = mensaje.trim().split(";")
        if (partes,size==3 && partes[0]== "LECTURA"){
            when(partes[1]){
                "nivel" -> {
                    val Porcentaje = partes[2].toIntOrNull()?: 0
                    binding.tvComidaPanel.text = "$porcentajes$"
                    if (porcentaaje < 20){
                        Toast.makeText(this, "¡Alerts! Nivel de comida menor al 20%")
                    }
                }
                "presencia" ->{
                    val estaPresente = partes[2] == "1"
                    binding.tvPresenciPanel.text = "presencia" if (estaPresente){
                        "Estado: Mascota presente en el plata"
                    }else{
                        "Estado: Mascota Aunsente"
                    }
                }
            }
        }
    }
}