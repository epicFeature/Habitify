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
    override val issue = Issue(  // cуть проблемы - issue
        id = "NoPrintlnInAppCode",
        severity = Severity.Warning, // не очень критично
        description = "Использование 'print' или 'println' запрещено в коде приложения. " +
                "Пожалуйста, используйте подходящую библиотеку для логирования, например, Android Log или Timber.",
        debt = Debt.FIVE_MINS // можно быстро исправить
    )
    private val forbiddenFunctions = setOf( // cписок запрещенных функций
        "kotlin.io.println",
        "kotlin.io.print"
    )

    override fun visit(root: KtFile) { // переопределяем чтобы исключить тест директории
        val filePath = root.virtualFilePath.toString() // получ путь к файлу
        if (filePath.contains("/src/test/") || //проверяем есть ли маркеры тестовых директорий -> прекращаем анализ этого файла
            filePath.contains("/src/androidTest/")
        ) {
            return
        }
        super.visit(root) // продолжаем обход дерева файла
    }

    override fun visitCallExpression(expression: KtCallExpression) { // рассматриваем каждый вызов функции в коде
        super.visitCallExpression(expression)
        val resolvedCall = expression.getResolvedCall(bindingContext) ?: return //полн инфа о вызове
        val functionFqName =
            resolvedCall.resultingDescriptor.fqNameOrNull()?.asString() //полн имя ф-ции
        if (functionFqName in forbiddenFunctions) { //проверяем есть ли она в списке запрещенных
            // если да -> создаем отчет (CodeSmell)
            val errorMessage = "'$functionFqName' является частью запрещенного API. " +
                    "Используйте логгер для лучшей диагностики и управляемости."
            report(CodeSmell(issue, Entity.from(expression), errorMessage))
        }
    }
}

//при новой попытке подключения нужно будет добавить (пока бехуспешно)
//HabitifyCustom:
//  active: true
//  NoPrintlnInAppCode:
//    active: true
//    severity: warning
//Эти строчки в самый низ файла detekt.yml