package io.github.t3m8ch.services

import io.github.t3m8ch.exceptions.ThereAreNoPostsException
import io.github.t3m8ch.models.Post
import io.github.t3m8ch.services.impl.StatisticsServiceImpl
import io.github.t3m8ch.services.interfaces.StatisticService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class TestStatisticsService : FunSpec({
    context("normal situations") {
        val posts = listOf(
            Post(id = 1, text = "I eat fish", views = 10000, likes = 300, dislikes = 10),
            Post(id = 2, text = "Cat", views = 9000, likes = 1000, dislikes = 3),
            Post(id = 3, text = "U-ha-ha", views = 1000, likes = 5, dislikes = 800),
            Post(id = 4, text = "Lorem ipsum... Lorem...", views = 500, likes = 600, dislikes = 8),
        )
        val service: StatisticService = StatisticsServiceImpl(posts)

        test("getTopPost should return a post with max views") {
            service.getTopPost() shouldBe posts[0]
        }

        test("getFavoritePost should return a post with max likes") {
            service.getFavoritePost() shouldBe posts[1]
        }

        test("getHatePost should return a post with max dislikes") {
            service.getHatePost() shouldBe posts[2]
        }

        test("getLongestPost should return a post with max text length") {
            service.getLongestPost() shouldBe posts[3]
        }
    }

    context("abnormal situations") {
        test("methods should throw ThereAreNoPostsException if there are no posts") {
            val service: StatisticService = StatisticsServiceImpl(listOf<Post>())

            forAll(
                row(service::getTopPost),
                row(service::getFavoritePost),
                row(service::getHatePost),
                row(service::getLongestPost),
            ) { func ->
                val exception = shouldThrow<ThereAreNoPostsException> { func() }
                exception.message shouldBe "There are no posts"
            }
        }
    }
})
