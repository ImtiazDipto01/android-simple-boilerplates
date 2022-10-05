package com.example.androidsimpleboilerplates.core.extensions

abstract class EntityMapper<Entity, DomainModel> {

    abstract fun mapFromEntity(entity: Entity): DomainModel

    abstract fun mapToEntity(domainModel: DomainModel): Entity

    open fun mapFromEntityList(entityList: List<Entity>): List<DomainModel> =
        entityList.map { mapFromEntity(it) }

    fun mapToEntityList(dtoList: List<DomainModel>): List<Entity> =
        dtoList.map { mapToEntity(it) }

}
