import androidx.room.Room
import com.example.demovieexpert.core.data.source.local.room.MovieDatabase
import com.example.demovieexpert.core.data.source.remote.RemoteDataSource
import com.example.demovieexpert.core.data.source.remote.network.ApiService
import com.example.demovieexpert.core.domain.repository.IMovieRepository
import com.example.demovieexpert.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "Movie.db"
        ).fallbackToDestructiveMigration().
            openHelperFactory(factory).
        build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/NPIMWkzcNG/MyZsVExrC6tdy5LTZzeeKg2UlnGG55UY=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { com.example.demovieexpert.core.data.source.local.LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        com.example.demovieexpert.core.data.MovieRepository(
            get(),
            get(),
            get()
        )
    }
}