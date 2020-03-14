package yovi.putra.hackernews.features.favorite

import androidx.lifecycle.MutableLiveData
import yovi.putra.hackernews.core.base.BaseViewModel
import yovi.putra.hackernews.data.repository.HackerNewsRepository

class FavoriteViewModel : BaseViewModel() {
    var storys = MutableLiveData<String>()
}