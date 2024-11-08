package com.example.navigation.Modules

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.TripScreenState
import com.example.navigation.models.Bag
import com.example.navigation.models.Item
import com.example.navigation.ui.theme.OurPackingBlue
@Composable
fun BagModule(
    bag: Bag,
    onItemCheckedChange: (Item) -> Unit,
    onAddItem: (String) -> Unit
) {
    val newItemName = remember { mutableStateOf("") }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = bag.getBagName(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = OurPackingBlue
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Display each item with a checkbox
            bag.getItems().forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = item.getIsChecked(),
                        onCheckedChange = {
                            onItemCheckedChange(item)
                        }
                    )
                    Text(
                        text = item.getItemName(),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Text field to enter a new item name and a button to add it
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = newItemName.value,
                    onValueChange = { newItemName.value = it },
                    label = { Text("Item name") },
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = {
                        if (newItemName.value.isNotBlank()) {
                            onAddItem(newItemName.value)
                            newItemName.value = "" // Clear input field after adding
                        }
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Add Item")
                }
            }
        }
    }
}
