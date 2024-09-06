package com.example.moviesjet.screens.home.details


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.moviesjet.model.Movie
import com.example.moviesjet.model.getMovies
import com.example.moviesjet.widgets.MovieRow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, movieId :String?) {

    val newMovieList = getMovies().filter { movie ->
        movie.id == movieId
    }
    Scaffold(topBar = {
        TopAppBar(title = {},colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.errorContainer),
            navigationIcon = { MyBack(navController) })
    }) {it
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(top = 60.dp), horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top) {

                MovieRow(movie = newMovieList[0])
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text("Movie Images")
                HorizontalScrollableImageView(newMovieList)
            }

        }
    }
}

@Composable
private fun HorizontalScrollableImageView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList[0].images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp)
            ) {
                Image(rememberImagePainter(data = image), "Movie Poster")
            }
        }
    }
}

@Composable
fun MyBack(navController: NavController) {
    Row(horizontalArrangement = Arrangement.Start) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back",
            modifier = Modifier.clickable {
                navController.popBackStack()
            })
    }}