package com.example.vkinternshipgiphy.presentation.view.main

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.vkinternshipgiphy.R
import com.example.vkinternshipgiphy.data.model.Validator
import com.example.vkinternshipgiphy.presentation.viewmodel.MainViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gifPagerAdapter = GifPagerAdapter(viewModel)

        view.findViewById<RecyclerView>(R.id.recyclerview).apply {
            layoutManager = StaggeredGridLayoutManager(COLUMNS, LinearLayoutManager.VERTICAL)
            adapter = gifPagerAdapter
        }

        val editText = view.findViewById<TextInputEditText>(R.id.search_input_edit_text)

        val updateButton = view.findViewById<Button>(R.id.updateButton)
        updateButton.isVisible = false

        val progressDialog = view.findViewById<ProgressBar>(R.id.progressDialog)

        gifPagerAdapter.addLoadStateListener { loadState ->
            if ((loadState.refresh is LoadState.Loading)
                || (loadState.append is LoadState.Loading)
            )
                progressDialog.isVisible = true
            else {

                lifecycleScope.launch {
                    delay(DELAY_TIME)
                    progressDialog.isVisible = false
                }
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(context, it.error.toString(), Toast.LENGTH_LONG)
                        .show()
                    updateButton.isVisible = true
                }
            }
        }

        updateButton.setOnClickListener {
            Toast.makeText(context, "updating...", Toast.LENGTH_LONG).show()
            updateButton.isVisible = false
            gifPagerAdapter.retry()
        }

        editText.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {

                    if (Validator(
                            inputText = editText.text.toString(),
                            viewInputText = editText
                        ).validate()
                    ) {

                        viewLifecycleOwner.lifecycleScope.launch {
                            viewModel.getGifsList(editText.text.toString()).observe(viewLifecycleOwner) {
                                it?.let {
                                    gifPagerAdapter.submitData(lifecycle, it)
                                }
                            }
                        }
                    }
                    editText.clearFocus()
                    editText.isCursorVisible = false

                    return true
                }
                return false
            }
        })
    }


    companion object {
        const val DELAY_TIME: Long = 1000
        const val COLUMNS = 2
    }
}