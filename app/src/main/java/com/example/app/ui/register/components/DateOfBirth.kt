package com.example.app.ui.register.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.ui.register.RegisterViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthDateScreen(
    viewModel: RegisterViewModel = viewModel(),
    onBack: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    val bg = Color(0xFF0F1B21)
    val card = Color(0xFF14232B)
    val outline = Color(0xFF2C3E48)
    val blue = Color(0xFF1877F2)
    val sheet = Color(0xFF14232B)

    val sheetBg = Color(0xFF2B3338)
    val centerBand = Color(0xFF3A4248)

    val today = remember { LocalDate.now() }
    val minDate = remember { LocalDate.of(1900, 1, 1) }

    val user by viewModel.user.collectAsState()
    val savedDateString = user.dateOfBirth
    val initialDate = if (savedDateString.isNotEmpty()) {
        LocalDate.parse(savedDateString)
    } else {
        today
    }

    var selectedDate by rememberSaveable { mutableStateOf(today) }
    var showSheet by remember { mutableStateOf(false) }

    val formatter = remember {
        DateTimeFormatter.ofPattern("dd 'tháng' MM, yyyy", Locale("vi", "VN"))
    }

    Surface(modifier = Modifier.fillMaxSize(), color = bg) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 18.dp)
                .clip(RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp))
                .background(sheet)
                .padding(18.dp)
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Ngày sinh của bạn là khi nào?",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Hãy sử dụng ngày sinh của chính bạn, ngay cả khi tài khoản này dành cho doanh nghiệp, thú cưng hay chủ đề khác. " +
                        "Thông tin này sẽ không hiển thị với bất kỳ ai trừ khi bạn chọn chia sẻ.",
                color = Color(0xFFB8C7D1),
                fontSize = 15.sp,
                lineHeight = 20.sp
            )

            Text(
                text = "Tại sao tôi cần cung cấp ngày sinh của mình?",
                color = Color(0xFF4AA3FF),
                fontSize = 15.sp,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier.padding(top = 6.dp)
            )

            Spacer(Modifier.height(16.dp))

            Surface(
                color = card,
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, outline),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showSheet = true }
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    val age = calcAge(selectedDate, today)
                    Text(
                        text = "Ngày sinh ($age tuổi)",
                        color = Color(0xFF9FB2BD),
                        fontSize = 14.sp
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = selectedDate.format(formatter),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.updateDateOfBirth(selectedDate.toString())  // ← Lưu vào ViewModel
                    onNext()
                },
                colors = ButtonDefaults.buttonColors(containerColor = blue),
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier.fillMaxWidth().height(52.dp)
            ) {
                Text("Tiếp", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }

        if (showSheet) {
            var tempDate by remember { mutableStateOf(selectedDate) }

            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                containerColor = sheetBg,
                scrimColor = Color.Black.copy(alpha = 0.55f)
            ) {
                Text(
                    text = "Chọn ngày sinh",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    WheelDatePickerCompose(
                        date = tempDate,
                        onDateChange = { tempDate = it },
                        modifier = Modifier.fillMaxSize(),
                        minDate = minDate,
                        maxDate = today
                    )

                    Box(
                        Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth()
                            .height(44.dp)
                            .background(centerBand.copy(alpha = 0.55f), RoundedCornerShape(12.dp))
                    )

                    Divider(
                        color = Color.White.copy(alpha = 0.10f),
                        modifier = Modifier.align(Alignment.Center).offset(y = (-22).dp)
                    )
                    Divider(
                        color = Color.White.copy(alpha = 0.10f),
                        modifier = Modifier.align(Alignment.Center).offset(y = (22).dp)
                    )
                }

                Button(
                    onClick = {
                        selectedDate = tempDate
                        showSheet = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .height(58.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = blue),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Text("Xong", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun WheelDatePickerCompose(
    date: LocalDate,
    onDateChange: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    minDate: LocalDate = LocalDate.of(1900, 1, 1),
    maxDate: LocalDate = LocalDate.now()
) {
    var day by remember(date) { mutableIntStateOf(date.dayOfMonth) }
    var month by remember(date) { mutableIntStateOf(date.monthValue) }
    var year by remember(date) { mutableIntStateOf(date.year) }

    fun daysInMonth(y: Int, m: Int): Int = LocalDate.of(y, m, 1).lengthOfMonth()

    fun clampAll(y: Int, m: Int, d: Int): LocalDate {
        val safeDay = d.coerceIn(1, daysInMonth(y, m))
        var candidate = LocalDate.of(y, m, safeDay)
        if (candidate.isBefore(minDate)) candidate = minDate
        if (candidate.isAfter(maxDate)) candidate = maxDate
        return candidate
    }

    LaunchedEffect(day, month, year) {
        val clamped = clampAll(year, month, day)
        if (clamped.year != year) year = clamped.year
        if (clamped.monthValue != month) month = clamped.monthValue
        if (clamped.dayOfMonth != day) day = clamped.dayOfMonth
        onDateChange(clamped)
    }

    val minYear = minDate.year
    val maxYear = maxDate.year

    val dayItems = remember(year, month) { (1..daysInMonth(year, month)).map { it.toString() } }
    val monthItems = remember { (1..12).map { "tháng $it" } }
    val yearItems = remember(minYear, maxYear) { (minYear..maxYear).map { it.toString() } }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        WheelColumnSimple(
            items = dayItems,
            selectedIndex = (day - 1).coerceIn(0, dayItems.lastIndex),
            modifier = Modifier.weight(1f).widthIn(min = 90.dp),
            onSelected = { idx -> day = idx + 1 }
        )
        WheelColumnSimple(
            items = monthItems,
            selectedIndex = month - 1,
            modifier = Modifier.weight(1f).widthIn(min = 130.dp),
            onSelected = { idx -> month = idx + 1 }
        )
        WheelColumnSimple(
            items = yearItems,
            selectedIndex = (year - minYear).coerceIn(0, yearItems.lastIndex),
            modifier = Modifier.weight(1f).widthIn(min = 100.dp),
            onSelected = { idx -> year = minYear + idx }
        )
    }
}

@Composable
private fun WheelColumnSimple(
    items: List<String>,
    selectedIndex: Int,
    modifier: Modifier,
    onSelected: (Int) -> Unit
) {
    val itemHeight = 44.dp
    val viewportHeight = 180.dp

    val listState = rememberLazyListState(initialFirstVisibleItemIndex = selectedIndex)

    var centerIndex by remember { mutableIntStateOf(selectedIndex) }

    LaunchedEffect(selectedIndex) {
        listState.animateScrollToItem(selectedIndex)
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .collect { visible ->
                val layout = listState.layoutInfo
                val center = (layout.viewportStartOffset + layout.viewportEndOffset) / 2
                val closest = visible.minByOrNull { info ->
                    val itemCenter = info.offset + info.size / 2
                    abs(itemCenter - center)
                } ?: return@collect
                centerIndex = closest.index
            }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.isScrollInProgress }
            .map { it }
            .distinctUntilChanged()
            .collect { scrolling ->
                if (!scrolling) {
                    listState.animateScrollToItem(centerIndex)
                    if (centerIndex in items.indices) onSelected(centerIndex)
                }
            }
    }

    Box(modifier = modifier.height(viewportHeight)) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(vertical = (viewportHeight - itemHeight) / 2),
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(items) { index, text ->
                val isSelected = index == centerIndex
                Text(
                    text = text,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeight)
                        .wrapContentHeight(Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                    color = if (isSelected) Color.White else Color.White.copy(alpha = 0.28f),
                    fontSize = if (isSelected) 22.sp else 18.sp,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                )
            }
        }
    }
}

private fun calcAge(birthDate: LocalDate, today: LocalDate = LocalDate.now()): Int {
    var age = today.year - birthDate.year
    if (today < birthDate.plusYears(age.toLong())) age--
    return age.coerceAtLeast(0)
}
