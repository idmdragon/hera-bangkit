package com.hera.bangkit.ui.main.post.story

import androidx.lifecycle.ViewModel
import com.hera.bangkit.data.source.remote.response.StoryResponse
import com.hera.bangkit.data.repositories.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PostStoryViewModel @Inject constructor(
    private val repository: DefaultRepository
) : ViewModel() {
    fun insertStory (storyEntity: StoryResponse) = repository.insertStory(storyEntity)
}