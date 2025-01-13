//package com.example.navigation.Modules.packing
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.navigation.ui.theme.OurPackingBlue
//
//
//@Composable
//fun TripModule(
//    name: String, dateFrom: String, dateTo: String, percentage: Int, onDelete: () -> Unit
//) {
//
//    Card(
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = Color.White,
//        ),
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxWidth()
//            .wrapContentHeight(),
//        border = BorderStroke(4.dp, Color.White)
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth(),
//            horizontalAlignment = Alignment.Start
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//
//            ) {
//                Text(
//                    text = "$dateFrom - $dateTo",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Left,
//                    color = OurPackingBlue
//                )
//                Icon(
//                    imageVector = Icons.Default.Delete,
//                    contentDescription = "Remove",
//                    tint = OurPackingBlue,
//                    modifier = Modifier.clickable {
//                        onDelete()
//                    }
//                )
//            }
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = name,
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Start,
//                    color = OurPackingBlue
//                )
//
//                Text(
//                    text = "$percentage%",
//                    fontSize = 35.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = OurPackingBlue,
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//
//        }
//    }
//}