package com.example.taskmodemoapp.ui.frag.comment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.taskmodemoapp.R
import com.example.taskmodemoapp.databinding.FragmentCommentBinding
import com.example.taskmodemoapp.model.Comment
import com.example.taskmodemoapp.model.Post
import com.example.taskmodemoapp.ui.MainActivity
import com.example.taskmodemoapp.ui.frag.MainStateEvent
import com.example.taskmodemoapp.ui.frag.post.PostAdapter
import com.example.taskmodemoapp.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentFragment : Fragment() {

    private var _binder : FragmentCommentBinding? = null
    private val binder get() = _binder!!
    private val viewModel : CommentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binder = FragmentCommentBinding.inflate(LayoutInflater.from((activity as MainActivity)))
        subscriber()
        viewModel.setStateEvent(MainStateEvent.GetPostEvent)
        return binder.root
    }

    private fun subscriber() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when(dataState) {
                is DataState.Success<List<Comment>> -> {
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

    private fun appendingLogTitle(comments : List<Comment>) {

        val sb = StringBuilder()
        for (comment in comments) {
            sb.append(comment.body + "\n")
        }
        binder.textInfo.text = sb.toString()
    }

    private fun displayProgress(visible: Boolean) {
        binder.progressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }
}