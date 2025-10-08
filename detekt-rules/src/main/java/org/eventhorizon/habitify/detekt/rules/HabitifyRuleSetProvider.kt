package org.eventhorizon.habitify.detekt.rules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class HabitifyRuleSetProvider : RuleSetProvider {
    override val ruleSetId: String = "HabitifyCustom" //это название используем в detekt
    override fun instance(config: Config): RuleSet {
        return RuleSet(
            id = ruleSetId,
            rules = listOf(
                NoPrintlnInAppCodeRule(config)  //тут добавляем свои правила
            )
        )
    }
}