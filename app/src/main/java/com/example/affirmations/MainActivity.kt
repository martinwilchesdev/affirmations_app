package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AffirmationsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AffirmationsList(Datasource().loadAffirmations(), modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AffirmationsList(items: List<Affirmation>, modifier: Modifier) {
    LazyColumn(
        modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        items(items) {
            AffirmationCard(it.imageResourceId, it.stringResourceId, modifier = Modifier)
        }
    }
}

@Composable
fun AffirmationCard(imageId: Int, textId: Int, modifier: Modifier) {
    Card(modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(id = textId),
            modifier.padding(16.dp),
            fontSize = 24.sp,
            lineHeight = 24.sp
        )
    }
    Spacer(modifier.height(32.dp))
}

@Preview(showBackground = true)
@Composable
fun AffirmationsPreview() {
    AffirmationsTheme {
        AffirmationsList(Datasource().loadAffirmations(), modifier = Modifier)
    }
}