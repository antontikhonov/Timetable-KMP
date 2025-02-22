package ru.antontikhonov.timetable_kmp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.w3c.dom.HTMLScriptElement
import ru.antontikhonov.timetable_kmp.app.App
import ru.antontikhonov.timetable_kmp.di.initKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val gaScript = (document.createElement("script") as HTMLScriptElement).apply {
        async = true
        src = "https://www.googletagmanager.com/gtag/js?id=G-D1Y6YPVJGS"
    }

    val inlineScript = (document.createElement("script") as HTMLScriptElement).apply {
        textContent = """
            window.dataLayer = window.dataLayer || [];
            function gtag(){dataLayer.push(arguments);}
            gtag('js', new Date());
            gtag('config', 'G-D1Y6YPVJGS');
        """.trimIndent()
    }

    document.head?.appendChild(gaScript)
    document.head?.appendChild(inlineScript)

    initKoin()
    ComposeViewport(document.body!!) {
        App()
    }
}
