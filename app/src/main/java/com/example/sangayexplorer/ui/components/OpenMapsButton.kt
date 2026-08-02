package com.example.sangayexplorer.ui.components

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun OpenMapsButton() {

    val context = LocalContext.current

    Button(
        onClick = {

            val uri = Uri.parse(
                "google.navigation:q=-2.216,-78.450"
            )

            val intent = Intent(
                Intent.ACTION_VIEW,
                uri
            )

            intent.setPackage("com.google.android.apps.maps")

            if (intent.resolveActivity(context.packageManager) != null) {

                context.startActivity(intent)

            } else {

                Toast.makeText(
                    context,
                    "Google Maps no está instalado.",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }
    ) {

        Text("🧭 Cómo llegar")

    }

}