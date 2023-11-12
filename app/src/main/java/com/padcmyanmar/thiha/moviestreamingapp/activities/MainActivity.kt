package com.padcmyanmar.thiha.moviestreamingapp.activities


import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.thiha.moviestreamingapp.R
import com.padcmyanmar.thiha.moviestreamingapp.adapters.BannerAdapter
import com.padcmyanmar.thiha.moviestreamingapp.adapters.CarouselAdapter
import com.padcmyanmar.thiha.moviestreamingapp.adapters.ShowCaseAdapter
import com.padcmyanmar.thiha.moviestreamingapp.data.models.MovieModel
import com.padcmyanmar.thiha.moviestreamingapp.data.models.MovieModelImpl
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.ActorVO
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.GenreVO
import com.padcmyanmar.thiha.moviestreamingapp.data.vos.MovieVO
import com.padcmyanmar.thiha.moviestreamingapp.databinding.ActivityMainBinding
import com.padcmyanmar.thiha.moviestreamingapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.thiha.moviestreamingapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.thiha.moviestreamingapp.delegates.ShowCaseViewHolderDelegate
import com.padcmyanmar.thiha.moviestreamingapp.dummy.dummyGenreList
import com.padcmyanmar.thiha.moviestreamingapp.mvp.presenters.MainPresenter
import com.padcmyanmar.thiha.moviestreamingapp.mvp.presenters.MainPresenterImpl
import com.padcmyanmar.thiha.moviestreamingapp.mvp.views.MainView
import com.padcmyanmar.thiha.moviestreamingapp.viewpods.ActorListViewPod
import com.padcmyanmar.thiha.moviestreamingapp.viewpods.MovieListViewPod


class MainActivity : AppCompatActivity(), MainView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mBannerAdapter: BannerAdapter
    private lateinit var mShowCaseAdapter: ShowCaseAdapter
    private lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    private lateinit var mMovieByGenreViewPod: MovieListViewPod
    private lateinit var mActorListViewPod: ActorListViewPod
    private lateinit var mCarouselAdapter: CarouselAdapter
    private var mGenres: List<GenreVO>? = null
    private lateinit var mPresenter: MainPresenter

    //model
    private val mMovieModel: MovieModel = MovieModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpPresenter()

        setUpToolbarMenu()
        setUpViewPod()
        setUpCarouselShowCase()
        setUpShowCase()


        setUpListeners()
       // mPresenter.onUiReady(this)
        requestData()
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mPresenter.initView(this)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }


    //request network data
    private fun requestData() {
        mMovieModel.getNowPlayingMovies {
            showError(it)
        }?.observe(this) {
            mCarouselAdapter.setNewData(it)
        }



        mMovieModel.getPopularMovies {
            showError(it)
        }?.observe(this) {
            mBestPopularMovieListViewPod.setData(it)
        }

        mMovieModel.getGenre(
            onSuccess = { genreId ->
                mGenres = genreId
                setUpGenreTabLayout(genreId)

                genreId.firstOrNull()?.id?.let {
                    getMovieByGenre(it)

                }
            },
            onFailure = {

            }

        )

        mMovieModel.getTopRatedMovies {
            showError(it)
        }?.observe(this) {
            mShowCaseAdapter.setNewData(it)
        }

        mMovieModel.getPopularActors(
            onSuccess = {
                mActorListViewPod.setData(it)
            },
            onFailure = {

            }
        )
    }


    //getMovieByGenre
    private fun getMovieByGenre(genreId: Int) {
        mMovieModel.getMoviesByGenres(genreId.toString(), onSuccess = {
            mMovieByGenreViewPod.setData(it)
        },
            onFailure = {

            })
    }

    //setUpMenuFromToolbar
    private fun setUpToolbarMenu() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    private fun setUpCarouselShowCase() {
        mCarouselAdapter = CarouselAdapter()
        val carousel = Carousel(this, binding.carouselShowCase, mCarouselAdapter)
        carousel.setOrientation(CarouselView.HORIZONTAL, false)
        carousel.scaleView(true)
        //  dotsIndicatorBanner.attachTo(carouselShowCase)

    }

//    //setUpBanner
//    private fun setUpBanner() {
//        mBannerAdapter = BannerAdapter(this)
//        binding.viewPagerBanner.adapter = mBannerAdapter
//        binding.dotsIndicatorBanner.attachTo(binding.viewPagerBanner)
//    }

    //setUpGenreTabLayout
    private fun setUpGenreTabLayout(genreList: List<GenreVO>) {
        genreList.forEach {
            binding.tabLayoutGenre.newTab().apply {
                text = it.name
                binding.tabLayoutGenre.addTab(this)


            }
        }

    }


    //setUpViewPod
    private fun setUpViewPod() {
        val vpBestPopularMovieList = findViewById<MovieListViewPod>(R.id.vpBestPopularMovieList)
        mBestPopularMovieListViewPod = vpBestPopularMovieList as MovieListViewPod
        mBestPopularMovieListViewPod.setUpMovieListViewPod(mPresenter)
        val vpMovieByGenre = findViewById<MovieListViewPod>(R.id.vpMovieByGenre)
        mMovieByGenreViewPod = vpMovieByGenre as MovieListViewPod
        mMovieByGenreViewPod.setUpMovieListViewPod(mPresenter)
        val vpActorList = findViewById<ActorListViewPod>(R.id.vpActorList)
        mActorListViewPod = vpActorList as ActorListViewPod
    }

    //setUpShowCase
    private fun setUpShowCase() {
        mShowCaseAdapter = ShowCaseAdapter(mPresenter)
        binding.rvShowCases.adapter = mShowCaseAdapter
        binding.rvShowCases.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


    }

    private fun setUpListeners() {
        // Genre Tab Layout
        binding.tabLayoutGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Snackbar.make(window.decorView, tab?.text ?: "", Snackbar.LENGTH_SHORT).show()
                mGenres?.get(tab?.position ?: 0)?.id?.let {
                    getMovieByGenre(it)
                }


            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }


    override fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>) {
        mBannerAdapter.setNewData(nowPlayingMovies)
    }

    override fun showPopularMovies(popularMovies: List<MovieVO>) {
        mBestPopularMovieListViewPod.setData(popularMovies)
    }

    override fun showTopRatedMovies(topRatedMovies: List<MovieVO>) {
        mShowCaseAdapter.setNewData(topRatedMovies)
    }

    override fun showGenres(genreList: List<GenreVO>) {
        setUpGenreTabLayout(genreList)
    }

    override fun showMoviesByGenre(moviesByGenre: List<MovieVO>) {
        mMovieByGenreViewPod.setData(moviesByGenre)
    }

    override fun showActor(actors: List<ActorVO>) {
        mActorListViewPod.setData(actors)
    }

    override fun navigateToMovieDetailsScreen(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this, movieId = movieId))
    }

    override fun showError(errorString: String) {
        Toast.makeText(this, errorString, Toast.LENGTH_SHORT).show()
    }


}