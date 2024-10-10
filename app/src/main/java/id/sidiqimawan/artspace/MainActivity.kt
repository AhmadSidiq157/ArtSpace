package id.sidiqimawan.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.sidiqimawan.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceScreen()
            }
        }
    }
}

// Data class untuk menyimpan informasi
data class Artwork(val imageRes: Int, val title: String, val year: String)

@Composable
fun ArtSpaceScreen() {
    // Daftar gambar
    val artworks = listOf(
        Artwork(R.drawable.merapi, "Gunung Merapi", "2020"),
        Artwork(R.drawable.tugu, "Tugu Jogja", "2021"),
        Artwork(R.drawable.prambanan, "Candi Prambanan", "2019")
    )

    // Variabel untuk melacak indeks dari gambar yang sedang ditampilkan
    var currentIndex by remember { mutableStateOf(0) }

    // Gambar, judul, dan tahun dari gambar saat ini
    val currentArtwork = artworks[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize() // Mengisi seluruh layar
            .padding(16.dp)
    ) {
        // Menampilkan gambar, mengambil 3/4 dari layar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f) // Mengambil 3/4 layar
        ) {
            Image(
                painter = painterResource(id = currentArtwork.imageRes),
                contentDescription = currentArtwork.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize() // Gambar memenuhi area yang dialokasikan
            )
        }

        // Bagian informasi dan tombol, mengambil 1/4 layar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // Mengambil 1/4 layar
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //  Teks nama gambar
            Text(
                text = currentArtwork.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )

            //  Teks tahun
            Text(
                text = currentArtwork.year,
                fontSize = 16.sp,
                modifier = Modifier.padding(4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol Previous dan Next
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        // Untuk pindah ke gambar sebelumnya, pastikan indeks tidak kurang dari 0
                        if (currentIndex > 0) {
                            currentIndex--
                        }
                    }
                ) {
                    Text(text = "Previous")
                }

                Button(
                    onClick = {
                        // Untuk pindah ke gambar selanjutnya, pastikan indeks tidak melebihi jumlah gambar
                        if (currentIndex < artworks.size - 1) {
                            currentIndex++
                        }
                    }
                ) {
                    Text(text = "Next")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceScreenPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}
