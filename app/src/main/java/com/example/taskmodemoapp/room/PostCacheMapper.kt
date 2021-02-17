package com.example.taskmodemoapp.room

import com.example.taskmodemoapp.model.Post
import com.example.taskmodemoapp.util.EntityMapper
import javax.inject.Inject

class PostCacheMapper
@Inject
constructor() : EntityMapper<PostCacheEntity, Post> {
    override fun mapFromEntity(entity: PostCacheEntity): Post {
        return Post(
            id = entity.id,
            userId = entity.userId,
            title = entity.title,
            body = entity.body
        )
    }

    override fun mapToEnitity(domainModel: Post): PostCacheEntity {
        return PostCacheEntity(
            id = domainModel.id,
            userId = domainModel.userId,
            title = domainModel.title,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(cachePost: List<PostCacheEntity>): List<Post> {
        return cachePost.map { mapFromEntity(it) }
    }
}