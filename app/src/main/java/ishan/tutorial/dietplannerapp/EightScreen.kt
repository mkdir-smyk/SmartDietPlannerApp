package ishan.tutorial.dietplannerapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EightScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    // List of vegetarian dishes
    val vegDishes = listOf(
        "Masala dosa with paneer fillings",
        "Moong dal stuffed cheela/chilla (Moong dal ka cheela/chilla)",
        "Classic club sandwich",
        "Masala dosa with mixed vegetable fillings",
        "Poshtik khichdi/khichri",
        "Split Bengal gram sweet rice (Channa dal sweet rice)",
        "Sprouted moong poha",
        "Vegetable upma",
        "Namkeen daliya",
        "Cracked wheat porridge (Meetha daliya)",
        "Spanish omelette/omlet",
        "Murmura (Puffed rice)",
        "Puffed wheat (Murmure/Moori)",
        "Rice flakes (Chiwda/Aval)",
        "Wheat flakes",
        "Cornflakes with milk",
        "Cheese and mushroom omelette/omlet"
    )
    val vegLunchDishes = listOf(
        listOf(
            "Cream of carrot soup",
            "Poori",
            "Paneer makhana korma",
            "Beans with coconut (Nariyal aur sem/phali; Beans thoran)",
            "Cumin pulao (Jeera/Zeera pulao)"
        ),
        listOf(
            "Green pea soup (Matar ka soup)",
            "Bhatura",
            "Vegetarian nargisi kofta curry",
            "Stuffed capsicum (Bharwa shimla mirch)",
            "Lemon rice (Pulihora, Elumichai sadam, Chitranna)"
        ),
        listOf(
            "Brown stock",
            "Gram flour poori (Besan poori)",
            "Split bengal gram dal (Channa dal)",
            "Stuffed tomatoes (Bharwa tamatar)",
            "Quinoa khichdi/khichri"
        ),
        listOf(
            "Cold tomato soup",
            "Sprouted moong parantha/paratha",
            "Jackfruit kofta curry (Kathal ka kofta curry)",
            "Baked vegetables with spinach",
            "Tamarind rice (Chintapandu pulihora/Puliyodharai/Puli sadam/Huli anna)"
        ),
        listOf(
            "Pumpkin soup",
            "Gram flour poori (Besan poori)",
            "Raw banana kofta curry (Kela kofta curry)",
            "Raw papaya with coconut (Papaya thoran)",
            "Sprouted moong pulao"
        ),
        listOf(
            "Lemon coriander soup",
            "Sprouted moong parantha/paratha",
            "Shahi keema kofta curry",
            "Baked potato with skin",
            "Quinoa khichdi/khichri"
        ),
        listOf(
            "Cold cucumber soup (Thanda kheere ka soup)",
            "Sprouted moong parantha/paratha",
            "Panchmel dal",
            "Dry potato (Sookhe aloo)",
            "Split bengal gram dal and vegetable pulao (Channa dal and vegetable pulao)"
        ),
        listOf(
            "Cold tomato soup",
            "Poori",
            "Ghiya/Lauki Kofta Curry",
            "Carrot and fenugreek leaves (Gajar methi)",
            "Plain pulao"
        )
    )

    val nonVegDishes = listOf(
        "Apple oats chia seed smoothie",
        "Paneer stuffed cheela/chilla",
        "Masala dosa with paneer fillings",
        "Stuffed egg omelette/omlet",
        "Moong dal stuffed cheela/chilla (Moong dal ka cheela/chilla)",
        "Classic club sandwich",
        "Masala dosa with mixed vegetable fillings",
        "Minced meat pancake (with chicken)",
        "Egg nog",
        "Split bengal gram sweet rice (Channa dal sweet rice)",
        "Poshtik khichdi/khichri",
        "Sprouted moong poha",
        "Vegetable upma",
        "Plain omelette/omlet",
        "Namkeen daliya",
        "Cracked wheat porridge (Meetha daliya)",
        "Puffed wheat (Murmure/Moori)",
        "Murmura (Puffed rice)",
        "Wheat flakes",
        "Rice flakes (Chiwda/Aval)"
    )
    val nonVegLunchDishes = listOf(
        listOf(
            "Lemon coriander soup",
            "Keema parantha/paratha",
            "Cauliflower kofta curry (Phoolgobhi kofta curry)",
            "Potato cauliflower (Aloo gobhi)",
            "Tamarind rice (Chintapandu pulihora/Puliyodharai/Puli sadam/Huli anna)"
        ),
        listOf(
            "Spinach soup (Palak ka soup)",
            "Dal stuffed poori",
            "Cabbage rolls (curry) ((Pattagobhi rolls)(curry))",
            "Potato capsicum (Shimla mirch aloo)",
            "Tamarind rice (Chintapandu pulihora/Puliyodharai/Puli sadam/Huli anna)"
        ),
        listOf(
            "Millet soup",
            "Makki ki roti",
            "Bengal fish curry (Bengali machli curry)",
            "Potato capsicum (Shimla mirch aloo)",
            "Vegetable biryani/biriyani"
        ),
        listOf(
            "Millet soup",
            "Methi poori",
            "Jackfruit sabzi (Kathal ki sabzi)",
            "Shepherd's pie (vegetarian)",
            "Curd rice (Dahi bhaat/Dahi chawal/Perugu annam/Daddojanam/Thayir saadam)"
        ),
        listOf(
            "Almond soup (Badam ka soup)",
            "Methi parantha/paratha",
            "Methi malai paneer",
            "Shepherd's pie (vegetarian)",
            "Paneer pulao"
        ),
        listOf(
            "Cold cucumber cream soup",
            "Paneer parantha/paratha",
            "Soyabean curry",
            "Methi chaman",
            "Eggplant/Brinjal rice (Vangi bhat)"
        ),
        listOf(
            "Baked potato soup",
            "Potato stuffed poori (Aloo ki poori)",
            "Pea kofta curry (Matar kofta curry)",
            "Raw turnip with coconut",
            "Chinese fried rice"
        ),
        listOf(
            "Cream of potato soup",
            "Bathua poori",
            "Gravy for kofta",
            "Brinjal bhartha (Baingan ka bhartha)",
            "Mixed vegetable pulao"
        )
    )

    val vegDiabeticBreakfast = listOf(
    "Paneer stuffed cheela/chilla",
    "Moong dal stuffed cheela/chilla (Moong dal ka cheela/chilla)",
    "Classic club sandwich",
    "Sprouted moong daliya",
    "Vegetable upma",
    "Sprouts upma",
    "Rice puttu (Ari puttu)",
    "Masala dosa",
    "Semolina dhokla (Suji/Rava dhokla)",
    "Carrot apple sandwich (Gajar aur seb ka sandwich)",
    "Spanish omelette/omlet",
    "Appam",
    "Vegetable poha",
    "Pulse mix",
    "Vermicelli upma (Semiya/Seviyan upma)",
    "Semolina upma (Suji/Rava upma)",
    "Poshtik chilla/cheela"
    )
    val nonVegDiabeticBreakfast = listOf(
    "Paneer stuffed cheela/chilla",
    "Moong dal stuffed cheela/chilla (Moong dal ka cheela/chilla)",
    "Classic club sandwich",
    "Sprouted moong daliya",
    "Vegetable upma",
    "Sprouts upma",
    "Rice puttu (Ari puttu)",
    "Masala dosa",
    "Semolina dhokla (Suji/Rava dhokla)",
    "Carrot apple sandwich (Gajar aur seb ka sandwich)",
    "Spanish omelette/omlet",
    "Appam",
    "Vegetable poha",
    "Pulse mix",
    "Vermicelli upma (Semiya/Seviyan upma)",
    "Semolina upma (Suji/Rava upma)",
    "Poshtik chilla/cheela"
    )

    val nonVegDiabeticLunchDish = listOf(
        listOf("Clear tomato soup (Tamatar ka soup)", "Onion-green chilli parantha/paratha (Pyaaz aur hari mirch ka parantha/paratha)", "Pea kofta curry (Matar kofta curry)", "Raw papaya with coconut (Papaya thoran)", "Mutton biryani/biriyani"),
        listOf("Cream of green peas soup", "Besan and spinach parantha/paratha (Besan aur palak ka parantha/paratha)", "Cauliflower kofta curry (Phoolgobhi kofta curry)", "Shepherd's pie (vegetarian)", "Mutton biryani/biriyani"),
        listOf("Minestrone soup", "Sprouted moong parantha/paratha", "Lobia curry", "Shepherd's pie (vegetarian)", "Quinoa khichdi/khichri"),
        listOf("Minestrone soup", "Potato parantha/paratha (Aloo ka parantha/paratha)", "Pea potato curry (Aloo matar)", "Raw turnip with coconut", "Quinoa khichdi/khichri"),
        listOf("Consomme au vermicelli", "Sprouted moong parantha/paratha", "Soyabean curry", "Potato fenugreek (Aloo methi)", "Chinese fried rice")
    )
    val vegDiabeticLunchDish = listOf(
        listOf("Pumpkin soup", "Makki ki roti", "Pea kofta curry (Matar kofta curry)", "Cabbage and peas (Pattagobhi aur matar)", "Quinoa khichdi/khichri"),
        listOf("Cream of carrot soup", "Besan and spinach parantha/paratha (Besan aur palak ka parantha/paratha)", "Jackfruit sabzi (Kathal ki sabzi)", "Baked brinjal in tomato sauce", "Chinese fried rice"),
        listOf("Curried Cauliflower soup", "Methi parantha/paratha", "Spinach paneer kofta curry (Palak paneer kofta curry)", "Brinjal bhartha (Baingan ka bhartha)", "Chinese fried rice"),
        listOf("Cream of potato soup", "Besan and spinach parantha/paratha (Besan aur palak ka parantha/paratha)", "Panchmel dal", "Carrot and cabbage with coconut (Nariyal ke saath pattagobhi aur gajar)", "Chinese fried rice"),
        listOf("Cream of tomato soup", "Sprouted moong parantha/paratha", "Pea keema curry (Matar keema ki sabzi)", "Carrot and fenugreek leaves (Gajar methi)", "Quinoa khichdi/khichri"),
        listOf("Lentil soup", "Besan and spinach parantha/paratha (Besan aur palak ka paratha/paratha)", "Dal dhokli", "Beans with coconut (Nariyal aur sem/phali; Beans thoran)", "Quinoa khichdi/khichri")
    )


    var randomVegDish by remember { mutableStateOf(vegDishes.random()) }
    var randomNonVegDish by remember { mutableStateOf(nonVegDishes.random()) }

    var randomVegLunchDish by remember { mutableStateOf(vegLunchDishes.random()) }
    var randomNonVegLunchDish by remember { mutableStateOf(nonVegLunchDishes.random()) }

    var randomVegDiabeticLunchDish by remember { mutableStateOf(vegDiabeticLunchDish.random()) }
    var randomNonVegDiabeticLunchDish by remember { mutableStateOf(nonVegDiabeticLunchDish.random()) }
    var randomNonVegDiabeticBreakfastDish by remember { mutableStateOf(nonVegDiabeticBreakfast.random()) }
    var randomVegDiabeticBreakfastDish by remember { mutableStateOf(vegDiabeticBreakfast.random()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Diet Recommendation") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    "Here are your personalized diet recommendations:",
                    style = MaterialTheme.typography.titleLarge
                )

                // Breakfast Section
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "BREAKFAST:",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = {
                        if (sharedViewModel.isDiabetic.value) {
                            // Diabetic check for breakfast
                            randomVegDiabeticBreakfastDish =
                                if (sharedViewModel.selectedDiet.value == "Veg") vegDiabeticBreakfast.random() else nonVegDiabeticBreakfast.random()
                        } else {
                            // Regular check for breakfast
                            randomVegDish =
                                if (sharedViewModel.selectedDiet.value == "Veg") vegDishes.random() else nonVegDishes.random()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Reload",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (sharedViewModel.isDiabetic.value) {
                        if (sharedViewModel.selectedDiet.value == "Veg") randomVegDiabeticBreakfastDish else randomNonVegDiabeticBreakfastDish
                    } else {
                        if (sharedViewModel.selectedDiet.value == "Veg") randomVegDish else randomNonVegDish
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                // Lunch/Dinner Section
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "LUNCH / DINNER:",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = {
                        if (sharedViewModel.isDiabetic.value) {
                            // Diabetic check for lunch/dinner
                            randomVegDiabeticLunchDish =
                                if (sharedViewModel.selectedDiet.value == "Veg") vegDiabeticLunchDish.random() else nonVegDiabeticLunchDish.random()
                        } else {
                            // Regular check for lunch/dinner
                            randomVegLunchDish =
                                if (sharedViewModel.selectedDiet.value == "Veg") vegLunchDishes.random() else nonVegLunchDishes.random()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Reload",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (sharedViewModel.isDiabetic.value) {
                        if (sharedViewModel.selectedDiet.value == "Veg") {
                            randomVegDiabeticLunchDish.joinToString("\n")
                        } else {
                            randomNonVegDiabeticLunchDish.joinToString("\n")
                        }
                    } else {
                        if (sharedViewModel.selectedDiet.value == "Veg") {
                            randomVegLunchDish.joinToString("\n")
                        } else {
                            randomNonVegLunchDish.joinToString("\n")
                        }
                    },
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}


