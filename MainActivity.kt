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
            CalculatorApp()
        }
    }
}

@Composable
fun CalculatorApp() {
    var input by remember { mutableStateOf("") }

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
            Text(
                text = input,
                color = Color.White,
                fontSize = 48.sp,
                textAlign = TextAlign.End,
                modifier = Modifier.padding(16.dp)
            )
        }

        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val buttonModifier = Modifier
                .weight(1f)
                .padding(8.dp)
                .aspectRatio(1f)
                .background(Color.DarkGray, RoundedCornerShape(50))

            val buttonTextStyle = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            // Row 1
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ButtonComponent(text = "7", modifier = buttonModifier, textStyle = buttonTextStyle) { input += "7" }
                ButtonComponent(text = "8", modifier = buttonModifier, textStyle = buttonTextStyle) { input += "8" }
                ButtonComponent(text = "9", modifier = buttonModifier, textStyle = buttonTextStyle) { input += "9" }
                ButtonComponent(text = "/", modifier = buttonModifier, textStyle = buttonTextStyle) { input += "/" }
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ButtonComponent(text = "4", modifier = buttonModifier, textStyle = buttonTextStyle) { input += "4" }
                ButtonComponent(text = "5", modifier = buttonModifier, textStyle = buttonTextStyle) { input += "5" }
                ButtonComponent(text = "6", modifier = buttonModifier, textStyle = buttonTextStyle) { input += "6" }
                ButtonComponent(text = "*", modifier = buttonModifier, textStyle = buttonTextStyle) { input += "*" }
            }

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ButtonComponent(text = "1", modifier = buttonModifier, textStyle = buttonTextStyle) { input += "1" }
                ButtonComponent(text = "2", modifier = buttonModifier, textStyle = buttonTextStyle) { input += "2" }
                ButtonComponent(text = "3", modifier = buttonModifier, textStyle = buttonTextStyle) { input += "3" }
                ButtonComponent(text = "-", modifier = buttonModifier, textStyle = buttonTextStyle) { input += "-" }
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ButtonComponent(text = "0", modifier = buttonModifier, textStyle = buttonTextStyle) { input += "0" }
                ButtonComponent(text = "C", modifier = buttonModifier, textStyle = buttonTextStyle) { input = "" }
                ButtonComponent(text = "=", modifier = buttonModifier, textStyle = buttonTextStyle) {  }
                ButtonComponent(text = "+", modifier = buttonModifier, textStyle = buttonTextStyle) { input += "+" }
            }
        }
    }
}

@Composable
fun ButtonComponent(
    text: String,
    modifier: Modifier = Modifier,
    textStyle: androidx.compose.ui.text.TextStyle = LocalTextStyle.current,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Text(text = text, style = textStyle)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculatorApp() {
    CalculatorApp()
}
