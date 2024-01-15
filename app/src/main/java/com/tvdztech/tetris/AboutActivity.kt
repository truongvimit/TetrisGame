package com.tvdztech.tetris

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.tvdztech.tetris.domain.base.BaseActivity
import com.tvdztech.tetris.ui.component.AppBar
import com.tvdztech.tetris.ui.component.BodyLarge
import com.tvdztech.tetris.ui.component.DisplayLarge
import com.tvdztech.tetris.ui.component.TitleLarge
import com.tvdztech.tetris.ui.theme.ComposetetrisTheme
import com.tvdztech.tetris.ui.theme.border2dp
import com.tvdztech.tetris.ui.theme.padding16dp
import com.tvdztech.tetris.ui.theme.padding8dp

class AboutActivity : BaseActivity() {
    @Composable
    override fun Content() {
        AboutScreen {
            this.finish()
        }
    }

}

@Composable
fun AboutScreen(
    onBackPress: () -> Unit
) {
    AppBar(
        title = stringResource(R.string.title_about),
        onBackClicked = onBackPress
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = padding16dp,
                    start = padding16dp,
                    end = padding16dp
                )
                .border(width = border2dp, color = MaterialTheme.colorScheme.onBackground),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DisplayLarge(text = stringResource(id = R.string.app_name))
            TitleLarge(
                modifier = Modifier.padding(padding8dp),
                text = BuildConfig.VERSION_NAME
            )
            BodyLarge(
                modifier = Modifier.padding(padding16dp),
                text = stringResource(R.string.about_game),
                textAlign = TextAlign.Justify
            )
            /*AppButton(
                modifier = Modifier.width(width248dp),
                text = stringResource(R.string.source_code)
            ) { customTabsIntent.launchUrl(context, Uri.parse(REPO_URL)) }*/
        }
    }
}

@Preview
@Composable
fun PreviewAboutScreen() {
    ComposetetrisTheme {
        Surface {
            AboutScreen(onBackPress = {/* no-op */ })
        }
    }
}