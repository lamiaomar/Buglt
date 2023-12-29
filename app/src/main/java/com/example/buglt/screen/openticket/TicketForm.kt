package com.example.buglt.screen.openticket

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.buglt.R
import com.example.buglt.extension.dashedBorder
import com.example.buglt.ui.theme.Purple80

@Composable
fun TicketForm(context: Context) {

    var selectedOption by remember { mutableStateOf("Android") }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(449.dp)
            .clipToBounds()
            .border(
                width = 1.dp,
                color = Purple80,
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp)
            )
            .background(Color.White)
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Summery EditText
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(context.getString(R.string.summery_edit_text_label)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                )
            )

            // Upload Screenshot EditText
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .dashedBorder(1.dp, Color.Gray, 8.dp)
                    .height(60.dp)
                    .clickable { /*TODO*/ },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = context.getString(R.string.upload_screenshot_box_text_label),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 14.dp)
                )
            }

            // Description EditText
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(context.getString(R.string.description_edit_text_label)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
            )

            // Radio buttons with icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = context.resources.getString(R.string.button_radio_platform_title),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp, end = 11.dp, top = 7.dp),
                    textAlign = TextAlign.Center,
                )
                listOf(
                    context.getString(R.string.button_radio_android),
                    context.getString(R.string.button_radio_ios),
                    context.getString(R.string.button_radio_pwa),
                ).forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        RadioButton(
                            selected = option == selectedOption,
                            onClick = { selectedOption = option },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Purple80,
                                unselectedColor = Color.Gray
                            )
                        )
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}