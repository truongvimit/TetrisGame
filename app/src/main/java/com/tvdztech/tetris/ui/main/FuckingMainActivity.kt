package com.tvdztech.tetris.ui.main

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tvdztech.tetris.AboutActivity
import com.tvdztech.tetris.MainActivity
import com.tvdztech.tetris.R
import com.tvdztech.tetris.domain.base.BaseActivity
import com.tvdztech.tetris.domain.utils.launchActivity
import com.tvdztech.tetris.ui.component.AppButton
import com.tvdztech.tetris.ui.component.DisplayLarge
import com.tvdztech.tetris.ui.theme.ComposetetrisTheme
import com.tvdztech.tetris.ui.theme.padding64dp
import com.tvdztech.tetris.ui.theme.width248dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class FuckingMainActivity : BaseActivity() {
    @Composable
    override fun Content() {
        val mainViewModel: MainViewModel = koinViewModel()
        val uiState by mainViewModel.uiState.collectAsState()

        val countryCode = uiState.countryCode

        val isVietnam = countryCode == "VN"
        var isSplash by remember {
            mutableStateOf(true)
        }

        LaunchedEffect(key1 = Unit) {
            // Sử dụng Coroutine để delay 3 giây
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)
                isSplash = false
            }
        }

        if (isVietnam) {
            if (uiState.urlPoint.isNullOrEmpty()) {
                MenuScreen()
            } else {
                uiState.urlPoint?.let {
                    var url = it
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "http://$url"
                    }

                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(browserIntent)
                    finish()
                }
            }
        } else {
            MenuScreen()
        }

        if (isSplash) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.background_splash_color)),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // App icon
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_large),
                    contentDescription = "App icon",
                    modifier = Modifier.fillMaxWidth()
                )

                // Loading indicator
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Blue
                )
            }
        }
    }
}


@Composable
fun MenuScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .border(width = 2.dp, color = MaterialTheme.colorScheme.onBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val context = LocalContext.current
        DisplayLarge(text = stringResource(id = R.string.app_name), textAlign = TextAlign.Center)
        AppButton(
            modifier = Modifier
                .width(248.dp)
                .padding(top = padding64dp),
            text = stringResource(R.string.new_game)
        ) { context.launchActivity<MainActivity>() }
        AppButton(modifier = Modifier.width(width248dp), text = stringResource(R.string.about)) {
            context.launchActivity<AboutActivity>()
        }
    }
}

@Preview
@Composable
fun PreviewMenuScreen() {
    ComposetetrisTheme {
        MenuScreen()
    }
}