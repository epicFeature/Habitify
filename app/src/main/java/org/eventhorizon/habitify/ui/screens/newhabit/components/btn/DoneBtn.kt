package org.eventhorizon.habitify.ui.screens.newhabit.components.btn

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.eventhorizon.habitify.R
import org.eventhorizon.habitify.ui.screens.main.components.IconData
import org.eventhorizon.habitify.ui.screens.main.components.LargeRoundBtn

@Composable
fun DoneBtn(modifier: Modifier = Modifier, onClick: ()->Unit ) {
    LargeRoundBtn(
        modifier = modifier,
        iconData = IconData(R.drawable.ic_done, "tick"),
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewDoneBtn() {
    DoneBtn(onClick = {})
}