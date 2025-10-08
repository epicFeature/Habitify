package org.eventhorizon.habitify.detekt.rules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.resolve.calls.util.getResolvedCall
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameOrNull

class NoPrintlnInAppCodeRule(config: Config) : Rule(config) {

    // 1. Описываем суть проблемы (Issue)
    override val issue = Issue(
        id = "NoPrintlnInAppCode",
        severity = Severity.Warning, // Это скорее проблема стиля и поддержки, чем баг
        description = "Usage of 'print' or 'println' is forbidden in app code. " +
                "Please use a proper logging library like Android's Log or Timber.",
        debt = Debt.FIVE_MINS // Исправить это очень быстро
    )

    // 2. Список запрещенных функций
    private val forbiddenFunctions = setOf(
        "kotlin.io.println",
        "kotlin.io.print"
    )

    // 3. Переопределяем метод visit для файла, чтобы отсечь тестовые директории
    override fun visit(root: KtFile) {
        // Получаем путь к файлу
        val filePath = root.virtualFilePath.toString()

        // Если путь содержит маркеры тестовых директорий, прекращаем анализ этого файла
        if (filePath.contains("/src/test/") ||
            filePath.contains("/src/androidTest/")) {
            return
        }

        // Если это не тест, продолжаем обход дерева файла
        super.visit(root)
    }

    // 4. "Посещаем" каждый вызов функции в коде
    override fun visitCallExpression(expression: KtCallExpression) {
        super.visitCallExpression(expression)

        // Используем bindingContext для получения полной информации о вызове
        val resolvedCall = expression.getResolvedCall(bindingContext) ?: return

        // Получаем полное имя функции (например, "kotlin.io.println")
        val functionFqName = resolvedCall.resultingDescriptor.fqNameOrNull()?.asString()

        // 5. Проверяем, есть ли вызванная функция в нашем списке запрещенных
        if (functionFqName in forbiddenFunctions) {
            // Если да, создаем отчет (CodeSmell)
            val errorMessage = "'$functionFqName' is a part of forbidden API. " +
                    "Use a logger for better diagnostics and manageability."
            report(CodeSmell(issue, Entity.from(expression), errorMessage))
        }
    }
}

//при новой попытке подключения нужно будет добавить
//HabitifyCustom:
//  active: true
//  NoPrintlnInAppCode:
//    active: true
//    severity: warning
//Эти строчки в самый низ файла detekt.yml