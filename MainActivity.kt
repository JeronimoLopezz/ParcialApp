import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplicacionCalculadora()
        }
    }
}

@Composable
fun AplicacionCalculadora() {
    var entrada by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var operador by remember { mutableStateOf<Char?>(null) }
    var primerOperando by remember { mutableStateOf<Double?>(null) }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF202020)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.Black)
                .height(100.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = entrada,
                    color = Color.White,
                    fontSize = 36.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = resultado,
                    color = Color.Gray,
                    fontSize = 24.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val modificadorBoton = Modifier
                .weight(1f)
                .padding(8.dp)
                .aspectRatio(1f)
                .background(Color.DarkGray, RoundedCornerShape(50))

            val estiloTextoBoton = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ComponenteBoton(texto = "7", modificador = modificadorBoton, estiloTexto = estiloTextoBoton) { entrada += "7" }
                ComponenteBoton(texto = "8", modificador = modificadorBoton, estiloTexto = estiloTextoBoton) { entrada += "8" }
                ComponenteBoton(texto = "9", modificador = modificadorBoton, estiloTexto = estiloTextoBoton) { entrada += "9" }
                ComponenteBoton(texto = "/", modificador = modificadorBoton, estiloTexto = estiloTextoBoton) {
                    establecerOperador('/', entrada, operador, primerOperando, resultado) { op, primer, resultadoActual ->
                        operador = op
                        primerOperando = primer
                        resultado = resultadoActual
                        entrada = ""
                    }
                }
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ComponenteBoton(texto = "4", modificador = modificadorBoton, estiloTexto = estiloTextoBoton) { entrada += "4" }
                ComponenteBoton(texto = "5", modificador = modificadorBoton, estiloTexto = estiloTextoBoton) { entrada += "5" }
                ComponenteBoton(texto = "6", modificador = modificadorBoton, estiloTexto = estiloTextoBoton) { entrada += "6" }
                ComponenteBoton(texto = "*", modificador = modificadorBoton, estiloTexto = estiloTextoBoton) {
                    establecerOperador('*', entrada, operador, primerOperando, resultado) { op, primer, resultadoActual ->
                        operador = op
                        primerOperando = primer
                        resultado = resultadoActual
                        entrada = ""
                    }
                }
            }

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ComponenteBoton(texto = "1", modificador = modificadorBoton, estiloTexto = estiloTextoBoton) { entrada += "1" }
                ComponenteBoton(texto = "2", modificador = modificadorBoton, estiloTexto = estiloTextoBoton) { entrada += "2" }
                ComponenteBoton(texto = "3", modificador = modificadorBoton, estiloTexto = estiloTextoBoton) { entrada += "3" }
                ComponenteBoton(texto = "-", modificador = modificadorBoton, estiloTexto = estiloTextoBoton) {
                    establecerOperador('-', entrada, operador, primerOperando, resultado) { op, primer, resultadoActual ->
                        operador = op
                        primerOperando = primer
                        resultado = resultadoActual
                        entrada = ""
                    }
                }
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ComponenteBoton(texto = "0", modificador = modificadorBoton, estiloTexto = estiloTextoBoton) { entrada += "0" }
                ComponenteBoton(texto = "C", modificador = modificadorBoton, estiloTexto = estiloTextoBoton) {
                    entrada = ""
                    resultado = ""
                    operador = null
                    primerOperando = null
                }
                ComponenteBoton(texto = "=", modificador = modificadorBoton, estiloTexto = estiloTextoBoton) {
                    if (primerOperando != null && operador != null) {
                        val segundoOperando = entrada.toDoubleOrNull()
                        if (segundoOperando != null) {
                            resultado = calcular(primerOperando!!, segundoOperando, operador!!).toString()
                            entrada = resultado
                            operador = null
                            primerOperando = null
                        }
                    }
                }
                ComponenteBoton(texto = "+", modificador = modificadorBoton, estiloTexto = estiloTextoBoton) {
                    establecerOperador('+', entrada, operador, primerOperando, resultado) { op, primer, resultadoActual ->
                        operador = op
                        primerOperando = primer
                        resultado = resultadoActual
                        entrada = ""
                    }
                }
            }
        }
    }
}

fun establecerOperador(
    nuevoOperador: Char,
    entrada: String,
    operadorActual: Char?,
    primerOperando: Double?,
    resultado: String,
    alActualizar: (Char, Double, String) -> Unit
) {
    val entradaComoDouble = entrada.toDoubleOrNull()
    if (entradaComoDouble != null) {
        if (operadorActual == null) {
            alActualizar(nuevoOperador, entradaComoDouble, resultado)
        } else if (primerOperando != null) {
            val resultadoIntermedio = calcular(primerOperando, entradaComoDouble, operadorActual)
            alActualizar(nuevoOperador, resultadoIntermedio, resultadoIntermedio.toString())
        }
    }
}

fun calcular(primerOperando: Double, segundoOperando: Double, operador: Char): Double {
    return when (operador) {
        '+' -> primerOperando + segundoOperando
        '-' -> primerOperando - segundoOperando
        '*' -> primerOperando * segundoOperando
        '/' -> if (segundoOperando != 0.0) primerOperando / segundoOperando else Double.NaN
        else -> Double.NaN
    }
}

@Composable
fun ComponenteBoton(
    texto: String,
    modificador: Modifier = Modifier,
    estiloTexto: androidx.compose.ui.text.TextStyle = LocalTextStyle.current,
    alHacerClic: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modificador.clickable(onClick = alHacerClic)
    ) {
        Text(text = texto, style = estiloTexto)
    }
}

@Preview(showBackground = true)
@Composable
fun VistaPreviaAplicacionCalculadora() {
    AplicacionCalculadora()
}
