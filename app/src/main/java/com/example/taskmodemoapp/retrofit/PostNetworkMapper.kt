package com.example.taskmodemoapp.retrofit

import com.example.taskmodemoapp.model.Post
import com.example.taskmodemoapp.util.EntityMapper
import javax.inject.Inject

class PostNetworkMapper
@Inject
constructor() : EntityMapper<PostNetworkEntity, Post> {
    override fun mapFromEntity(entity: PostNetworkEntity): Post {
        return Post(
            id = entity.id,
            userId = entity.userId,
            title = entity.title,
            body = entity.title
        )
    }

    override fun mapToEnitity(domainModel: Post): PostNetworkEntity {
        return PostNetworkEntity(
            id = domainModel.id,
            userId = domainModel.userId,
            title = domainModel.title,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(networkPost: List<PostNetworkEntity>): List<Post> {
        return networkPost.map { mapFromEntity(it) }
    }

}