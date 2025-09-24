package org.eventhorizon.habitify.ui.screens.home.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.eventhorizon.habitify.R
import org.eventhorizon.habitify.ui.screens.main.components.IconData
import org.eventhorizon.habitify.ui.screens.main.components.LargeRoundBtn

@Composable
fun PlusBtn(modifier: Modifier = Modifier, onClick: ()->Unit ) {
    LargeRoundBtn(
        modifier = modifier,
        iconData = IconData(R.drawable.ic_add, "add"),
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewPlusBtn() {
    PlusBtn(onClick = {})
}