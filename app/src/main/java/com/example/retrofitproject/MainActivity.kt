package com.example.retrofitproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import com.example.retrofitproject.model.Blog
import com.example.retrofitproject.ui.MainStateEvent
import com.example.retrofitproject.ui.MainViewModel
import com.example.retrofitproject.ui.theme.RetrofitProjectTheme
import com.example.retrofitproject.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)

        setContent {
            RetrofitProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }

    private fun subscribeObservers(){
        viewModel.datastate.observe(this, Observer { dataState ->
            when(dataState){
                is DataState.Success<List<Blog>> ->{
                    displayResult(dataState.data)
                }
                is DataState.Error ->{
                    displayError(dataState.exception.localizedMessage)
                }

                is DataState.Loading ->{
                    displayProgessBar(true)
                }
            }
        })
    }


    private fun displayError(message : String?){
        if(message != null){
            Timber.i(message)
        }else{
            Timber.i("Unknown Error")
        }
    }


    private fun displayProgessBar(isDisplayed : Boolean){
        Timber.i(isDisplayed.toString())
    }

    private fun displayResult(blogs : List<Blog>){
        for(blog in blogs){
            Timber.i(blog.title)
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RetrofitProjectTheme {
        Greeting("Android")
    }
}