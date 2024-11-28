package com.example.navigation.Modules


import TripModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.models.BagModel
import com.example.navigation.models.ItemModel
import com.example.navigation.ui.theme.OurPackingBlue


@Composable
fun BagModule(
    bagModel: BagModel,
    onItemCheckedChange: (ItemModel) -> Unit,
    onAddItem: (String) -> Unit,
    onDeleteBag: (BagModel) -> Unit
) {
    val newItemName = remember { mutableStateOf("") }
    val checkedCount = bagModel.itemModels.count { it.getIsChecked() }
    val totalCount = bagModel.itemModels.size
    val percentage = if (totalCount > 0) (checkedCount * 100) / totalCount else 0

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = bagModel.bagName + " - $checkedCount/$totalCount",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = OurPackingBlue
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete bag",
                    tint = OurPackingBlue,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onDeleteBag(bagModel) }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            bagModel.itemModels.forEach { item ->
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

            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = newItemName.value,
                    onValueChange = { newItemName.value = it },
                    label = { Text("Item name") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
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
                        contentDescription = "Add Item",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Text("Add Item")
                }
            }
        }
    }
}
