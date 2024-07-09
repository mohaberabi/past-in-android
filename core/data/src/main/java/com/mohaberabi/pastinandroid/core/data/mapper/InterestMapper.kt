package com.mohaberabi.pastinandroid.core.data.mapper

import com.mohaberabi.pastinandroid.core.data.dto.InterestDto
import com.mohaberabi.pastinandroid.core.database.entity.InterestEntity
import com.mohaberabi.pastinandroid.core.database.entity.InterestFtsEntity
import com.mohaberabi.pastinandroid.model.InterestModel
import com.mohaberabi.pastinandroid.model.InterestsFts

fun InterestDto.toInterestModel(): InterestModel {
    return InterestModel(
        id = id,
        description = description,
        title = title,
        image = image

    )
}

fun InterestModel.toInterestEntity(): InterestEntity {
    return InterestEntity(
        id = id,
        description = description,
        title = title,
        image = image
    )


}


fun InterestEntity.toInterestModel(): InterestModel {
    return InterestModel(
        id = id,
        description = description,
        title = title,
        image = image
    )
}

fun InterestModel.toInterestFts(): InterestsFts {
    return InterestsFts(
        id = id,
        title = title,
        body = description
    )
}

fun InterestsFts.toInterestFtsEntity(): InterestFtsEntity {
    return InterestFtsEntity(
        id = id,
        title = title,
        body = body
    )
}

