package com.example.androidsimpleboilerplates.data.mapper

import com.example.androidsimpleboilerplates.core.extensions.EntityMapper
import com.example.androidsimpleboilerplates.data.remote.dto.GithubUserResponse
import com.example.androidsimpleboilerplates.domain.model.GithubUser
import javax.inject.Inject

class GithubUserMapper @Inject constructor(): EntityMapper<GithubUserResponse, GithubUser>() {

    override fun mapFromEntity(entity: GithubUserResponse): GithubUser {
        return GithubUser(
            login = entity.login,
            avatarUrl = entity.avatarUrl,
            nodeId = entity.nodeId,
            url = entity.url
        )
    }

    override fun mapToEntity(domainModel: GithubUser): GithubUserResponse {
        return GithubUserResponse(
            login = domainModel.login,
            avatarUrl = domainModel.avatarUrl,
            nodeId = domainModel.nodeId,
            url = domainModel.url
        )
    }

    override fun mapFromEntityList(entityList: List<GithubUserResponse>): List<GithubUser> =
        entityList.map { mapFromEntity(it) }

}