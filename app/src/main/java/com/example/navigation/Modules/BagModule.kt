package com.example.navigation.Modules


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

            bag.getItems().forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = item.getIsChecked(),
                        onCheckedChange = {
                            item.setIsChecked(it)
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

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = newItemName.value,
                    onValueChange = { newItemName.value = it },
                    label = { Text("Item name") },
                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                    shape = RoundedCornerShape(15.dp),
                )
                Button(
                    onClick = {
                        if (newItemName.value.isNotBlank()) {
                            onAddItem(newItemName.value)
                            newItemName.value = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = OurPackingBlue),
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Bag",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Text("Add Item")
                }
            }
        }
    }
}
