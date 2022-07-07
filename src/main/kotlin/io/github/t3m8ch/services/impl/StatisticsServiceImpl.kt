package io.github.t3m8ch.services.impl

import io.github.t3m8ch.exceptions.ThereAreNoPostsException
import io.github.t3m8ch.models.Post
import io.github.t3m8ch.services.interfaces.StatisticService

class StatisticsServiceImpl(private val posts: Collection<Post>) : StatisticService {
    override fun getTopPost(): Post {
        ensureThereArePosts()
        return posts.maxByOrNull { p -> p.views }!!
    }

    override fun getFavoritePost(): Post {
        ensureThereArePosts()
        return posts.maxByOrNull { p -> p.likes }!!
    }

    override fun getHatePost(): Post {
        ensureThereArePosts()
        return posts.maxByOrNull { p -> p.dislikes }!!
    }

    override fun getLongestPost(): Post {
        ensureThereArePosts()
        return posts.maxByOrNull { p -> p.text.length }!!
    }

    private fun ensureThereArePosts() {
        if (!posts.any()) throw ThereAreNoPostsException()
    }
}
