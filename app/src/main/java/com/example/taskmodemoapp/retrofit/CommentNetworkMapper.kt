package com.example.taskmodemoapp.retrofit

import com.example.taskmodemoapp.model.Comment
import com.example.taskmodemoapp.util.EntityMapper
import javax.inject.Inject

class CommentNetworkMapper
@Inject
constructor() : EntityMapper<CommentNetworkEntity, Comment> {
    override fun mapFromEntity(entity: CommentNetworkEntity): Comment {
        return Comment(
            id = entity.id,
            postId = entity.postId,
            name = entity.name,
            email = entity.email,
            body = entity.body
        )
    }

    override fun mapToEnitity(domainModel: Comment): CommentNetworkEntity {
        return CommentNetworkEntity(
            id = domainModel.id,
            postId = domainModel.postId,
            name = domainModel.name,
            email = domainModel.email,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(network : List<CommentNetworkEntity>) : List<Comment> {
        return network.map { mapFromEntity(it) }
    }
}