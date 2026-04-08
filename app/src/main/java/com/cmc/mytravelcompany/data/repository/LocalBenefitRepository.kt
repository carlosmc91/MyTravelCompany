package com.cmc.mytravelcompany.data.repository

import com.cmc.mytravelcompany.R
import com.cmc.mytravelcompany.domain.entity.BenefitEntity
import com.cmc.mytravelcompany.domain.repository.BenefitRepository
import javax.inject.Inject

class LocalBenefitRepository @Inject constructor() : BenefitRepository {
    override suspend fun getBenefits(): List<BenefitEntity> {
        return listOf(
            BenefitEntity(
                id = 1,
                title = R.string.benefit_screen_agents_title,
                subtitle = R.string.benefit_screen_agents_subtitle,
                explanation = R.string.benefit_screen_agents_description,
                icon = R.drawable.mundo
            ),
            BenefitEntity(
                id = 2,
                title = R.string.benefit_screen_covid_title,
                subtitle = R.string.benefit_screen_covid_subtitle,
                explanation = R.string.benefit_screen_covid_description,
                icon = R.drawable.medical
            ),
            BenefitEntity(
                id = 3,
                title = R.string.benefit_screen_prices_title,
                subtitle = R.string.benefit_screen_prices_subtitle,
                explanation = R.string.benefit_screen_prices_description,
                icon = R.drawable.hand
            ),
            BenefitEntity(
                id = 4,
                title = R.string.benefit_screen_cancellation_title,
                subtitle = R.string.benefit_screen_cancellation_subtitle,
                explanation = R.string.benefit_screen_cancellation_description,
                icon = R.drawable.hoja
            ),
            BenefitEntity(
                id = 5,
                title = R.string.benefit_screen_airlines_title,
                subtitle = R.string.benefit_screen_airlines_subtitle,
                explanation = R.string.benefit_screen_airlines_description,
                icon = R.drawable.avionmundo
            ),
            BenefitEntity(
                id = 6,
                title = R.string.benefit_screen_resorts_title,
                subtitle = R.string.benefit_screen_resorts_subtitle,
                explanation = R.string.benefit_screen_resorts_description,
                icon = R.drawable.mundo
            ),
            BenefitEntity(
                id = 7,
                title = R.string.benefit_screen_custom_trips_title,
                subtitle = R.string.benefit_screen_custom_trips_subtitle,
                explanation = R.string.benefit_screen_custom_trips_description,
                icon = R.drawable.aguja
            ),
            BenefitEntity(
                id = 8,
                title = R.string.benefit_screen_assistance_title,
                subtitle = R.string.benefit_screen_assistance_subtitle,
                explanation = R.string.benefit_screen_assistance_description,
                icon = R.drawable.chat
            )
        )
    }
}
