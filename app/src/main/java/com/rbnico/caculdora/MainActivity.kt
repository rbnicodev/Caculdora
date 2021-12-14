package com.rbnico.caculdora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener
{


    var n1 = 0.0
    var n2 = 0.0
    var op: String? = null
    var new = true
    var equal: Boolean = false

    @Throws(Exception::class)
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
        var n = (v as Button).text as String

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

    private fun masMenos()
    {
        if(screen.text.toString() != "0")
        {
            if(!screen.text.toString().startsWith("-"))
            {
                screen.text = screen.text.toString().replace("+", "")
                screen.text = "-" + screen.text.toString()
            }
            else
            {
                screen.text = screen.text.toString().replace("-", "")
                screen.text = "+" + screen.text.toString()
            }
        }

    }

    private fun coma(n: String)
    {

        if(!(screen.text.toString().contains(".")))
            if(screen.text.toString()=="0")
                screenWriter("0.")
            else
                screenWriter(n)
    }

    private fun porcentaje()
    {
        operationPressed("/")
        numberPressed("100")
        equalPressed()
    }

    private fun numberPressed(n: String)
    {
        if(new)
        {
            screen.text = ""
        }

        new = false
        screenWriter(n)
    }

    private fun clearScreen()
    {
        n1 = 0.0
        n2 = 0.0
        op = null
        screen.text = "0"
    }

    private fun borrar()
    {
        if(screen.text.toString().length == 2 && (screen.text.toString().startsWith("+") || screen.text.toString().startsWith("-")))
        {
            screen.text = screen.text.toString().dropLast(2)
        }else if (screen.text.toString().length == 0 || (screen.text.toString().length == 2 && screen.text.toString().contains("."))) {
            screen.text = "0"
        }else
            screen.text = screen.text.toString().dropLast(1)

        if(screen.text.toString().length==0)
            screen.text = "0"

    }

    private fun operationPressed(operation: String)
    {
        equalPressed()
        new = true
        this.op = operation
        n1 = screen.text.toString().toDouble()
    }

    private fun screenWriter(n: String)
    {
        var result = if(screen.text == "0" && n != ",")
            n
        else
            "${screen.text}$n"

        while(result.contains(".") && result.endsWith("0",true))
        {
            result.dropLast(1)
        }


        screen.text = result


    }

    private fun checkOperation()
    {
        if(op == null)
            n1 = screen.text.toString().toDouble()
        else
            n2 = screen.text.toString().toDouble()
    }

    private fun equalPressed()
    {
        n2 = screen.text.toString().toDouble()

        if (n2 == 0.0 && op == "/") {
            screen.text = "0"
            return
        }
        val resultado =
            when(op)
            {
                "+" -> n1+n2
                "-" -> n1-n2
                "/" -> n1/n2
                "X" -> n1*n2
                else -> screen.text.toString().toDouble()
            }
        op = null
        n1 = resultado.toDouble()

        screen.text = if(resultado.toString().endsWith(".0"))
        {
            resultado.toString().replace(".0", "")
        } else
        {
            "%.2f".format(resultado)
        }
    }
}