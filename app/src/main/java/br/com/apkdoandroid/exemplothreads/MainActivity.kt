package br.com.apkdoandroid.exemplothreads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.apkdoandroid.exemplothreads.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var parar = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonParar.setOnClickListener { parar = true }
        binding.buttonIniciar.setOnClickListener {
           /* repeat(30){
                Log.d("Repito","Contador ${it}")
                Thread.sleep(1000)
            }*/

           // Mythread().start()

            Thread(MyRunnble()).start()
        }

    }
    inner class Mythread : Thread() {
        override fun run() {
            super.run()
            repeat(30){

                Log.d("Repito","Contador ${it} Thread - ${Thread.currentThread().name}")
                runOnUiThread {
                    binding.buttonIniciar.isEnabled = false
                    binding.buttonIniciar.setText("Contando: ${it}")
                    if(it == 29){
                        binding.buttonIniciar.isEnabled = true
                        binding.buttonIniciar.setText("Reiniciar")
                    }
                }
                Thread.sleep(1000)


            }
        }
    }

    inner class MyRunnble : Runnable{
        override fun run() {
            repeat(30){
                    if(parar){
                        parar = false
                        runOnUiThread {
                            binding.buttonIniciar.isEnabled = true
                            binding.buttonIniciar.setText("Iniciar")
                        }
                        return
                    }
                Log.d("Repito","Contador ${it} Thread - ${Thread.currentThread().name}")

                runOnUiThread {
                    binding.buttonIniciar.isEnabled = false
                    binding.buttonIniciar.setText("Contando: ${it}")
                    if(it == 29){
                        binding.buttonIniciar.isEnabled = true
                        binding.buttonIniciar.setText("Reiniciar")
                    }
                }
                Thread.sleep(1000)


            }
        }

    }
}