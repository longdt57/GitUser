package com.app.androidcompose.ui.base

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AlertDialogView(
    dialogTitle: String? = null,
    dialogText: String? = null,
    confirmText: String? = null,
    dismissText: String? = null,
    icon: ImageVector? = null,
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
) {
    AlertDialog(
        icon = {
            icon?.let {
                Icon(icon, contentDescription = "Icon")
            }
        },
        title = {
            if (dialogTitle != null) {
                Text(text = dialogTitle)
            }
        },
        text = {
            if (dialogText != null) {
                Text(text = dialogText)
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            confirmText?.let {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text(confirmText)
                }
            }
        },
        dismissButton = {
            dismissText?.let {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text(dismissText)
                }
            }
        }
    )
}

@Preview
@Composable
private fun AlertDialogViewPreview() {
    AlertDialogView(
        dialogTitle = "Hello",
        dialogText = "Start programming",
        confirmText = "OK",
        dismissText = "Close"
    )
}