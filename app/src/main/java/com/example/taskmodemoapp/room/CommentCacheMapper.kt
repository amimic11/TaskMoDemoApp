package com.example.taskmodemoapp.room

import com.example.taskmodemoapp.model.Comment
import com.example.taskmodemoapp.util.EntityMapper
import javax.inject.Inject

class CommentCacheMapper
@Inject
constructor() : EntityMapper<CommentCacheEntity, Comment>{
    override fun mapFromEntity(entity: CommentCacheEntity): Comment {
        return Comment(
            id = entity.id,
            postId = entity.postId,
            name = entity.name,
            email = entity.email,
            body = entity.body
        )
    }

    override fun mapToEnitity(domainModel: Comment): CommentCacheEntity {
        return CommentCacheEntity(
            id = domainModel.id,
            postId = domainModel.postId,
            name = domainModel.name,
            email = domainModel.email,
            body = domainModel.body
        )
    }

    fun mapFromEnityList(cacheComment : List<CommentCacheEntity>) : List<Comment> {
        return cacheComment.map { mapFromEntity(it) }
    }
}