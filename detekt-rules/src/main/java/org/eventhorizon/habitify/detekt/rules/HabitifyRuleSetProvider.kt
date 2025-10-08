package org.eventhorizon.habitify.detekt.rules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class HabitifyRuleSetProvider : RuleSetProvider {
    override val ruleSetId: String = "HabitifyCustom"
    override fun instance(config: Config): RuleSet {
        return RuleSet(
            id = ruleSetId,
            rules = listOf(
                NoPrintlnInAppCodeRule(config)             // Новое правило
            )
        )
    }
}