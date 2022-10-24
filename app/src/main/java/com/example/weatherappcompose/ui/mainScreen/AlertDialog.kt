package com.example.weatherappcompose.ui.mainScreen

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.core.content.ContextCompat
import com.example.weatherappcompose.ui.theme.GreyNight

@Composable
fun AlertDialog(
    onConfirmButtonClicked: () -> Unit,
    openDialog: MutableState<Boolean>
) {
    AlertDialog(
        onDismissRequest = { openDialog.value = false },
        title = { Text(text = "Доступ к местоположению") },
        text = {
            Text(
                text = "Предоставить доступ к данным о Вашем текущем местоположении? " +
                        "Без этих данных приложение не сможет корректно отображать информацию"
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirmButtonClicked()
                    openDialog.value = false
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = GreyNight)
            ) {
                Text(text = "ОК")
            }
        }
    )
}