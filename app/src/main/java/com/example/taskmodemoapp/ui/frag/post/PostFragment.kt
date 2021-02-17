package com.example.taskmodemoapp.ui.frag.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmodemoapp.databinding.FragmentPostBinding
import com.example.taskmodemoapp.model.Post
import com.example.taskmodemoapp.ui.MainActivity
import com.example.taskmodemoapp.ui.frag.MainStateEvent
import com.example.taskmodemoapp.ui.frag.PostViewModel
import com.example.taskmodemoapp.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment() {

    private lateinit var navController: NavController
    private var _binder : FragmentPostBinding? = null
    private val binder get() = _binder!!
    private val viewModel : PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binder = FragmentPostBinding.inflate(inflater, container, false)
        binder.recyclerview.layoutManager = LinearLayoutManager(activity as MainActivity)
        subscribeOberver()
        viewModel.setStateEvent(MainStateEvent.GetPostEvent)
//        navController = Navigation.findNavController(binder.root.rootView )
        return binder.root
    }

    private fun subscribeOberver() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState) {
                is DataState.Success<List<Post>> -> {
                    displayProgress(false)
                    appendingLogTitle(dataState.data)
                }
                is DataState.Error -> {
                    displayProgress(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgress(true)
                }

            }
        })
    }

    private fun displayError(message : String?) {
        if (message != null) binder.textInfo.text = message else binder.textInfo.text = "Unknown Error"
    }

    private fun appendingLogTitle(posts : List<Post>) {

//        val sb = StringBuilder()
//        for (post in posts) {
//            sb.append(post.title + "\n")
//        }
//        binder.text.text = sb.toString()
        binder.recyclerview.adapter = PostAdapter((activity as MainActivity), ArrayList(posts), navController)
    }

    private fun displayProgress(visible: Boolean) {
        binder.progressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
}