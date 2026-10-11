package com.edric.vp_week03_homework.ClickerGame

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edric.vp_week03_homework.R
import java.text.DecimalFormat
import kotlin.math.ceil

const val upgradeMultiplier = 2
const val coinMultiplier = 1.5

@Composable
fun clickerGameView() {
    var coins by rememberSaveable { mutableIntStateOf(0) }
    var coinsPerTap by rememberSaveable { mutableIntStateOf(1) }
    var upgradePrice by rememberSaveable { mutableIntStateOf(10) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.catground),
            contentDescription = "catground",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(0.25f))
        ) { }
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(2f))
            Box(
                modifier = Modifier.weight(5f)
            ) {
                yourCoins(coins,coinsPerTap)
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier.weight(8f)
            ){
                tapTheCat(onTap = {coins+=coinsPerTap})
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier.weight(6f)
            ) {
                giveMeYourCoin(coins,coinsPerTap,upgradePrice,
                    onUpgrade = {
                            coins-=upgradePrice
                            upgradePrice *= upgradeMultiplier
                            coinsPerTap = ceil(coinsPerTap * coinMultiplier).toInt()
                    }
                )
            }
            Spacer(modifier = Modifier.weight(2f))
        }
    }
}
@Composable
private fun yourCoins(coins: Int, coinsPerTap: Int){
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(Modifier.weight(1f))
        Card(
            shape = RoundedCornerShape(32.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(0.375f)
            ),
            modifier = Modifier
                .aspectRatio(1f)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(10f)
            ) {
                Text(
                    text = "Your Coins",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = coins.toString(),
                    color = Color.Green,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "${DecimalFormat("0.##").format(coinsPerTap)} coins per tap",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Spacer(Modifier.weight(1f))
    }
}
@Composable
private fun tapTheCat(onTap: () -> Unit){
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
        Text(
            text = "Tap the Cat!",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f)
        ) {
            Spacer(Modifier.weight(1f))
            Card(
                shape = RoundedCornerShape(32.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(6f)
                    .aspectRatio(1f)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onTap
                    )
            ) {
                Image(
                    painter = painterResource(if (isPressed) R.drawable.pop else R.drawable.cat),
                    contentDescription = "pop cat",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f)
                )
            }
            Spacer(Modifier.weight(1f))
        }
        Text(
            text = "Purr~",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}
@Composable
private fun giveMeYourCoin(coins: Int,coinsPerTap: Int,upgradePrice: Int, onUpgrade: () -> Unit){
    val buttonEnabled = coins>=upgradePrice
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(Modifier.weight(1f))
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .weight(10f)
    ) {
        Spacer(Modifier.weight(1f))
        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxSize()
                .weight(8f)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Give Me Your Coin",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Next upgrade: +${ceil((coinsPerTap * coinMultiplier) - coinsPerTap).toInt()} coins per tap",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(Modifier.weight(1f))
                    Button(
                        enabled = buttonEnabled,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CB050),
                            contentColor = Color.White,
                            disabledContainerColor = Color.LightGray,
                            disabledContentColor = Color.White
                        ),
                        onClick = onUpgrade,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(12f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = if (buttonEnabled) "Pay for $upgradePrice coins" else "Find ${upgradePrice - coins} more coins"
                        )
                    }
                    Spacer(Modifier.weight(1f))
                }
            }
        }
        Spacer(Modifier.weight(1f))
    }
    Spacer(Modifier.weight(1f))
}
}
@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun clickerGamePreview() {
    clickerGameView()
}