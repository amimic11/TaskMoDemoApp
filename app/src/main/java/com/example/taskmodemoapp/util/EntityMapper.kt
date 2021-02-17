package com.example.taskmodemoapp.util

interface EntityMapper<Entity , DomainModel> {

    fun mapFromEntity(entity: Entity) : DomainModel

    fun mapToEnitity(domainModel: DomainModel) : Entity

}
