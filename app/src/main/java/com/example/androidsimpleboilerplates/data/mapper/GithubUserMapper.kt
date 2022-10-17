package com.example.androidsimpleboilerplates.data.mapper

import com.example.androidsimpleboilerplates.core.extensions.EntityMapper
import com.example.androidsimpleboilerplates.data.remote.dto.GithubUserResponse
import com.example.androidsimpleboilerplates.data.local.db.entity.GithubUser
import javax.inject.Inject

class GithubUserMapper @Inject constructor(): EntityMapper<GithubUserResponse, GithubUser>() {

    override fun mapFromEntity(entity: GithubUserResponse): GithubUser {
        return GithubUser(
            login = entity.login!!,
            avatarUrl = entity.avatarUrl,
            url = entity.url,
            id = entity.id!!
        )
    }

    override fun mapToEntity(domainModel: GithubUser): GithubUserResponse {
        return GithubUserResponse(
            login = domainModel.login,
            avatarUrl = domainModel.avatarUrl,
            url = domainModel.url,
            id = domainModel.id
        )
    }

    override fun mapFromEntityList(entityList: List<GithubUserResponse>): List<GithubUser> =
        entityList.map { mapFromEntity(it) }

}