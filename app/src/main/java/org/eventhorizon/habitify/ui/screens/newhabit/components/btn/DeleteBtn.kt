package org.eventhorizon.habitify.ui.screens.newhabit.components.btn

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.eventhorizon.habitify.R
import org.eventhorizon.habitify.ui.screens.main.components.IconData
import org.eventhorizon.habitify.ui.screens.main.components.LargeRoundBtn

@Composable
fun DeleteBtn(modifier: Modifier = Modifier, onClick: ()->Unit ) {
    LargeRoundBtn(
        modifier = modifier,
        iconData = IconData(R.drawable.ic_delete_new, "trash"),
        onClick = onClick,
        iconSizeCoef = 0.4
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewDeleteBtn() {
    DeleteBtn(onClick = {})
}