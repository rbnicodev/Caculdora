package com.rbnico.caculdora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener
{

    var n1 = 0.0
    var n2 = 0.0
    var op: String? = null
    var new = true
    var equal: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        op = null

        bt0.setOnClickListener(this)
        bt1.setOnClickListener(this)
        bt2.setOnClickListener(this)
        bt3.setOnClickListener(this)
        bt4.setOnClickListener(this)
        bt5.setOnClickListener(this)
        bt6.setOnClickListener(this)
        bt7.setOnClickListener(this)
        bt8.setOnClickListener(this)
        bt9.setOnClickListener(this)
        btComa.setOnClickListener(this)
        btMenos.setOnClickListener(this)
        btPlus.setOnClickListener(this)
        btX.setOnClickListener(this)
        btSlash.setOnClickListener(this)
        btPorcentaje.setOnClickListener(this)
        btClear.setOnClickListener(this)
        btBorrar.setOnClickListener(this)
        btEqual.setOnClickListener(this)
        btMasMenos.setOnClickListener(this)

    }

    override fun onClick(v: View?)
    {

        var n: String
        try
        {
            n = (v as Button).text as String
        } catch (e: Exception){
            n=""
        }

        when (v)
        {
            bt0 -> numberPressed(n)
            bt1 -> numberPressed(n)
            bt2 -> numberPressed(n)
            bt3 -> numberPressed(n)
            bt4 -> numberPressed(n)
            bt5 -> numberPressed(n)
            bt6 -> numberPressed(n)
            bt7 -> numberPressed(n)
            bt8 -> numberPressed(n)
            bt9 -> numberPressed(n)
            btMasMenos -> masMenos()
            btComa -> coma(n)
            btPlus -> operationPressed(n)
            btMenos -> operationPressed(n)
            btX -> operationPressed(n)
            btSlash -> operationPressed(n)
            btPorcentaje -> porcentaje()
            btBorrar -> borrar()
            btClear -> clearScreen()
            btEqual ->
            {
                equal = true
                equalPressed()
            }
        }
    }

    private fun masMenos() // CAMBIO DE SIGNO
    {
        try
        {
            if(screen.text.toString() != "0") //SOLO LO CAMBIA SI NO ES CERO
            {
                if(!screen.text.toString().startsWith("-")) //SI ES NEGATIVO LO ASIGNA PONE EN POSITIVO
                {
                    screen.text = screen.text.toString().replace("+", "")
                    screen.text = "-" + screen.text.toString()
                }
                else //SI ES POSITIVO LO ASIGNA PONE EN NEGATIVO
                {
                    screen.text = screen.text.toString().replace("-", "")
                    screen.text = "+" + screen.text.toString()
                }
            }
        } catch(e: Exception)
        {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT)
        }

    }

    private fun coma(n: String)
    {
        try
        {
            if(!(screen.text.toString().contains("."))) // SÓLO SI NO TIENE PUNTO
                if(screen.text.toString()=="0")
                    screenWriter("0.")
                else
                    screenWriter(n)
        } catch(e: Exception)
        {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT)
        }
    }

    private fun porcentaje()
    {
        try
        {
            when(op)
            {
                "x" ->
                {
                    screen.text = (screen.text.toString().toDouble()/100).toString()
                }
                "/" -> equalPressed()
                null ->
                {
                    screen.text = (screen.text.toString().toDouble()/100).toString()
                }
                "+" ->
                {
                    screen.text = (screen.text.toString().toDouble() * n1 / 100).toString()
                }
                "-" ->
                {
                    screen.text = (screen.text.toString().toDouble() * n1 / 100).toString()
                }
            }
        } catch(e: Exception)
        {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT)
        }
    }

    private fun numberPressed(n: String)
    {
        try
        {
            if(new)
            {
                screen.text = ""
            }

            new = false
            screenWriter(n)

        } catch(e: Exception)
        {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT)
        }
    }

    private fun clearScreen()
    {
        try
        {
            n1 = 0.0
            n2 = 0.0
            op = null
            screen.text = "0"
            screenSymbol.text = ""
        } catch(e: Exception)
        {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT)
        }
    }

    private fun borrar()
    {
        try
        {
            //SI TIENE UN DÍGITO Y EMPIEZA CON SIGNO
            if(screen.text.toString().length == 2 && (screen.text.toString().startsWith("+") || screen.text.toString().startsWith("-")))
            {
                screen.text = screen.text.toString().dropLast(2)


            //SI ESTÁ VACÍO O SOLO LE QUEDA UN NÚMERO Y EL PUNTO DECIMAL
            }else if (screen.text.toString().length == 0 || (screen.text.toString().length == 2 && screen.text.toString().contains("."))) {
                screen.text = "0"


            //SI ESTÁ EN NOTACIÓN CIENTÍFICA
            } else if(screen.text.toString().contains('E'))
            {
                operationPressed("/")
            } else
            {
                screen.text = screen.text.toString().dropLast(1)
            }



            //SI ESTÁ VACÍO, LO PONE A CERO
            if(screen.text.toString().length==0)
                screen.text = "0"
        } catch(e: Exception)
        {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT)
        }

    }

    private fun operationPressed(operation: String)
    {
        try
        {
            equalPressed()
            new = true
            this.op = operation
            n1 = screen.text.toString().toDouble()
            screenSymbol.text  = op
        } catch(e: Exception)
        {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT)
        }
    }

    private fun screenWriter(n: String)
    {
        try
        {
            var result = if(screen.text == "0" && n != ".")
                n
            else
                "${screen.text}$n"

            screen.text = result
        } catch(e: Exception)
        {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT)
        }


    }

    private fun equalPressed()
    {


        var resultadoExponencial: Double = 0.0
        var exponente = 0
        var stringResult: String = ""
        var resultado: Double


        try
        {
            screenSymbol.text = ""
            if(screen.text.toString().contains('E'))
            {
                exponente = Integer.valueOf(screen.text.toString()[screen.text.toString().length-1].toString())
                n2 = screen.text.toString().dropLast(2).toDouble()
                for(i in 1..exponente)
                {
                    n2 = n2*10
                }
            }else
            {
                n2 = screen.text.toString().toDouble()
            }
            exponente = 0

            if (n2 == 0.0 && op == "/") {
                screen.text = "0"
                return
            }
            resultado =
                when(op)
                {
                    "+" -> n1+n2
                    "-" -> n1-n2
                    "/" -> n1/n2
                    "x" -> n1*n2
                    else -> screen.text.toString().toDouble()
                }

            resultadoExponencial = resultado

            if(resultadoExponencial > 1000000)
            {
                while(resultadoExponencial > 10)
                {
                    resultadoExponencial = resultadoExponencial / 10
                    exponente += 1
                }
            }

            op = null
            n1 = resultado

            if(exponente > 1)
            {
                stringResult = resultadoExponencial.toString() + "E" + exponente
            } else
            {
                stringResult = resultado.toString()
            }

            if(stringResult.contains('E'))
            {
                screen.text = stringResult
            }else
            {
                if(resultado.toString().endsWith(".0"))
                {
                    screen.text = resultado.toString().replace(".0", "")
                } else
                {
                    screen.text = "%.2f".format(resultado)
                }
            }
        } catch(e: Exception)
        {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT)
        }
        new = true
    }
}