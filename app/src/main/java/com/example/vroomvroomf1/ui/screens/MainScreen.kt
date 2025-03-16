package com.example.vroomvroomf1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import com.example.vroomvroomf1.ui.viewmodel.F1ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, viewModel: F1ViewModel = viewModel()) {
    val races by viewModel.races.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("F1 Race Tracker") },
                actions = {
                    // Add an Info IconButton on the top bar
                    IconButton(onClick = { navController.navigate("info") }) {
                        Icon(Icons.Filled.Info, contentDescription = "Info")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(races) { race ->
                        RaceItem(race)
                    }
                }
            }
        }
    }
}

@Composable
fun RaceItem(race: com.example.vroomvroomf1.data.model.Race) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "🏁 ${race.raceName}", style = MaterialTheme.typography.titleMedium)
            Text(text = "📅 Date: ${race.date}")
            Text(text = "📍 Circuit: ${race.Circuit.circuitName}")
            Text(text = "🌍 Location: ${race.Circuit.Location.locality}, ${race.Circuit.Location.country}")
        }
    }
}
