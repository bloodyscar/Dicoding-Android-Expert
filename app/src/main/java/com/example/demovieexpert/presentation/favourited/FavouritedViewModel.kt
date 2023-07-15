
import androidx.lifecycle.ViewModel
import com.example.demovieexpert.core.domain.usecase.MovieUseCase

class FavouritedViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    var getAllFav = movieUseCase.getFavoriteMovie()

}