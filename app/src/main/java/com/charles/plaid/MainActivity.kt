package com.charles.plaid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.charles.plaid.ui.theme.PlaidTheme
import com.plaid.link.OpenPlaidLink
import com.plaid.link.linkTokenConfiguration
import com.plaid.link.result.LinkExit
import com.plaid.link.result.LinkSuccess

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val linkTokenConfiguration = linkTokenConfiguration {
            token = "link-sandbox-1e05c178-9973-4696-82bb-a8f9e052d872"
        }
        val linkAccountToPlaid =
            registerForActivityResult(OpenPlaidLink()) {
                when (it) {
                    is LinkSuccess ->{
                        println("Link success")
                    }
                    is LinkExit -> {
                        println("Link exit")
                    }
                }
            }
        setContent {
            PlaidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Button(onClick = {
                            linkAccountToPlaid.launch(linkTokenConfiguration)

                        }) {
                            Text(text = "Link account")
                        }
                    }
                }
            }
        }
    }
}
