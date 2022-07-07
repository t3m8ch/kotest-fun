package io.github.t3m8ch.services.interfaces

import io.github.t3m8ch.models.Post

interface StatisticService {
    fun getTopPost(): Post
    fun getFavoritePost(): Post
    fun getHatePost(): Post
    fun getLongestPost(): Post
}
