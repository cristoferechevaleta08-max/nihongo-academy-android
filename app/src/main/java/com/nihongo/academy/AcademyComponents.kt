package com.nihongo.academy

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight

@Composable
internal fun AcademyPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth().heightIn(min = 48.dp),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) { Text(text, style = MaterialTheme.typography.labelLarge) }
}

@Composable
internal fun AcademySecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth().heightIn(min = 48.dp),
        shape = MaterialTheme.shapes.large
    ) { Text(text, style = MaterialTheme.typography.labelLarge) }
}

@Composable
internal fun AcademyLabeledField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    singleLine: Boolean = true
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(label, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurface)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { if (placeholder.isNotEmpty()) Text(placeholder) },
            leadingIcon = leadingContent,
            trailingIcon = trailingContent,
            singleLine = singleLine,
            shape = MaterialTheme.shapes.medium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )
    }
}

@Composable
internal fun AcademyListCard(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    selected: Boolean = false,
    leading: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(if (selected) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface)
            .border(1.dp, if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant, MaterialTheme.shapes.medium)
            .clickable(onClick = onClick)
            .heightIn(min = 56.dp)
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leading != null) { leading(); Spacer(Modifier.width(12.dp)) }
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            if (subtitle != null) Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
internal fun AcademyProgressRing(
    progress: Float,
    centerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    description: String = "Progreso"
) {
    Box(
        modifier = modifier.size(112.dp).semantics { contentDescription = description },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = { progress.coerceIn(0f, 1f) },
            modifier = Modifier.matchParentSize(),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            strokeWidth = 7.dp,
            strokeCap = StrokeCap.Round
        )
        centerContent()
    }
}

@Composable
internal fun AcademyProgressPanel(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseSurface,
            contentColor = MaterialTheme.colorScheme.inverseOnSurface
        )
    ) { Box(Modifier.padding(18.dp)) { content() } }
}

@Composable
internal fun AcademyQuickAction(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leading: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = modifier.clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, MaterialTheme.shapes.medium)
            .clickable(onClick = onClick)
            .heightIn(min = 64.dp)
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leading != null) { leading(); Spacer(Modifier.width(10.dp)) }
        Text(title, modifier = Modifier.weight(1f), style = MaterialTheme.typography.labelLarge)
        Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
internal fun AcademyTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null
) {
    Row(modifier.fillMaxWidth().heightIn(min = 56.dp), verticalAlignment = Alignment.CenterVertically) {
        if (onBack != null) {
            androidx.compose.material3.IconButton(onClick = onBack, modifier = Modifier.size(48.dp)) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
            }
            Spacer(Modifier.width(4.dp))
        }
        Text(title, modifier = Modifier.weight(1f), style = MaterialTheme.typography.titleLarge)
        if (trailing != null) trailing()
    }
}

@Composable
internal fun AcademyBottomNavigationItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit
) {
    Column(
        modifier = modifier.clip(RoundedCornerShape(12.dp)).clickable(onClick = onClick)
            .heightIn(min = 56.dp).padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        icon()
        Text(label, style = MaterialTheme.typography.labelSmall, color = if (selected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
