package com.nihongoacademy.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nihongoacademy.model.CommunityPost
import com.nihongoacademy.model.CommunitySampleData
import com.nihongoacademy.ui.theme.AcademyColors

@Composable
fun CommunityScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("Preguntas") }
    var showComposer by remember { mutableStateOf(false) }
    var draft by remember { mutableStateOf("") }
    var localPosts by remember { mutableStateOf(CommunitySampleData.questions) }
    val posts = if (selectedTab == "Preguntas") localPosts else CommunitySampleData.forum

    if (showComposer) {
        AlertDialog(
            onDismissRequest = { showComposer = false },
            containerColor = AcademyColors.Surface,
            title = { Text("Nueva publicación", color = AcademyColors.TextPrimary) },
            text = {
                OutlinedTextField(value = draft, onValueChange = { draft = it },
                    label = { Text("Escribe tu pregunta o aporte") }, modifier = Modifier.fillMaxWidth())
            },
            confirmButton = {
                TextButton(onClick = {
                    if (draft.isNotBlank()) {
                        localPosts = listOf(CommunityPost("UsuarioKun", "Ahora", draft.trim(), 0, 0, "狐")) + localPosts
                        draft = ""
                        showComposer = false
                        selectedTab = "Preguntas"
                    }
                }) { Text("Publicar", color = AcademyColors.Sakura) }
            },
            dismissButton = { TextButton(onClick = { showComposer = false }) { Text("Cancelar") } }
        )
    }

    Box(Modifier.fillMaxSize().background(AcademyColors.Background)) {
        Column(Modifier.fillMaxSize().padding(horizontal = 20.dp, vertical = 18.dp)) {
            Text("Comunidad", color = AcademyColors.TextPrimary, style = MaterialTheme.typography.headlineLarge)
            Spacer(Modifier.height(18.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                CommunityTab("Preguntas", selectedTab == "Preguntas") { selectedTab = "Preguntas" }
                CommunityTab("Foro", selectedTab == "Foro") { selectedTab = "Foro" }
            }
            Spacer(Modifier.height(18.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(13.dp), contentPadding = PaddingValues(bottom = 88.dp)) {
                items(posts) { post -> CommunityPostCard(post) }
            }
        }
        FloatingActionButton(onClick = { showComposer = true },
            modifier = Modifier.align(Alignment.BottomEnd).padding(22.dp),
            containerColor = AcademyColors.ButtonPrimary, contentColor = AcademyColors.ButtonPrimaryText,
            shape = CircleShape) { Text("+", fontSize = 27.sp, fontWeight = FontWeight.Light) }
    }
}

@Composable
private fun CommunityTab(label: String, selected: Boolean, onClick: () -> Unit) {
    Box(Modifier.clip(RoundedCornerShape(14.dp))
        .background(if (selected) AcademyColors.ButtonPrimary else AcademyColors.Background)
        .border(1.dp, if (selected) AcademyColors.ButtonPrimary else AcademyColors.Border, RoundedCornerShape(14.dp))
        .clickable(onClick = onClick).padding(horizontal = 20.dp, vertical = 11.dp)) {
        Text(label, color = if (selected) AcademyColors.ButtonPrimaryText else AcademyColors.TextPrimary,
            style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
private fun CommunityPostCard(post: CommunityPost) {
    Card(colors = CardDefaults.cardColors(containerColor = AcademyColors.Surface),
        shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(42.dp).clip(CircleShape).background(AcademyColors.SurfaceVariant),
                    contentAlignment = Alignment.Center) {
                    Text(post.avatar, color = AcademyColors.Sakura, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Column(Modifier.padding(start = 11.dp).weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(post.author, color = AcademyColors.TextPrimary, style = MaterialTheme.typography.titleSmall)
                    Text(post.time, color = AcademyColors.TextSecondary, style = MaterialTheme.typography.bodySmall)
                }
                Text("•••", color = AcademyColors.TextSecondary)
            }
            Text(post.message, color = AcademyColors.TextPrimary, style = MaterialTheme.typography.bodyLarge)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("♡  ${post.likes}", color = AcademyColors.TextSecondary, style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.width(18.dp))
                Text("▢  ${post.comments}", color = AcademyColors.TextSecondary, style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.weight(1f))
                Text("Responder", color = AcademyColors.Sakura, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}
