package com.example.navigation.Screens

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navigation.DataStoreManager
import com.example.navigation.R
import com.example.navigation.states.HomeScreenState
import com.example.navigation.states.PreferencesHelper
import com.example.navigation.updateLanguage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class Language(
    val code: String,
    val name: String,
    @DrawableRes val flag: Int
)

@Composable
fun LanguageListItem(selectedItem: Language, isSelected: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(if (isSelected) Color.LightGray else Color.Transparent)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            painter = painterResource(selectedItem.flag),
            contentScale = ContentScale.Crop,
            contentDescription = selectedItem.code
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = selectedItem.name,
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.onBackground,
            )
        )
    }
}

@Composable
fun LanguagesDropdown(
    modifier: Modifier = Modifier,
    languagesList: List<Language>,
    currentLanguage: String,
    onCurrentLanguageChange: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedItem = languagesList.first { it.code == currentLanguage }

    Box(
        modifier = modifier
            .padding(end = 16.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .width(200.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.surface)
                .clickable {
                    expanded = !expanded
                }
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(60.dp),
                    //.clip(CircleShape),
                painter = painterResource(selectedItem.flag),
                contentScale = ContentScale.Crop,
                contentDescription = selectedItem.code
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = selectedItem.name,
                fontSize = 18.sp,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )

            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            languagesList.forEach { item ->
                DropdownMenuItem(
                    text = {
                        LanguageListItem(selectedItem = item, isSelected = item == selectedItem)
                    },
                    onClick = {
                        expanded = false
                        onCurrentLanguageChange(item.code)
                    }
                )
            }
        }
    }
}


@Composable
fun SettingsScreen(
    onGoBack: () -> Unit
) {
    val allLanguages = listOf(
        Language("en", "English", R.drawable.uk),
        Language("sk", "Slovenčina", R.drawable.slovakia),
        Language("es", "Español", R.drawable.spain),
    )

    val context = LocalContext.current

    var name by remember { mutableStateOf(PreferencesHelper.getName(context) ?: "") }
    val dataStoreManager = remember { DataStoreManager(context) }
    val activity = context as? Activity


    val initial = context.resources.configuration.locales[0].language
    val currentLanguageCode by dataStoreManager.getLanguage().collectAsState(initial = initial)


    val onCurrentLanguageChange: (String) -> Unit = { newLanguage ->
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreManager.saveLanguage(newLanguage)
        }
        updateLanguage(context = context, languageCode = newLanguage)
        activity?.recreate()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back home",
                tint = Color.Black,
                modifier = Modifier.clickable { onGoBack() }
            )
            Text(
                text = stringResource(R.string.settings_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Nothing",
                tint = Color.Transparent,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Gray,
            thickness = 1.dp
        )

            Spacer(modifier = Modifier.height(45.dp))

            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.settings_change_language),
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.height(8.dp))


            LanguagesDropdown(
                modifier = Modifier.fillMaxWidth(),
                languagesList = allLanguages,
                currentLanguage = currentLanguageCode,
                onCurrentLanguageChange = onCurrentLanguageChange
            )
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth().padding(35.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            Text(
                modifier = Modifier.padding(16.dp),
                text = stringResource(R.string.name_edit_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal
            )
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    HomeScreenState.setName(context, it)
                    PreferencesHelper.setName(context, it)
                },
                label = { Text(stringResource(R.string.change_name_inbox)) },
                modifier = Modifier.width(300.dp),
                shape = MaterialTheme.shapes.medium,

            )
            OutlinedButton(
                onClick = { onGoBack() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = stringResource(R.string.change_name_button))
            }


    }
}
