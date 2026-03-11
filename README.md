# README.md

The Pizza Planets Android application.

## Commands

```bash
# Build
./gradlew build
./gradlew assembleDebug

# Unit tests
./gradlew test
./gradlew test :app:testDebugUnitTest --tests "com.example.pizzaplanets.data.OfflinePlanetRepositoryTest"

# Instrumented tests (requires connected device/emulator)
./gradlew connectedAndroidTest

# Lint
./gradlew lint

# Clean
./gradlew clean
```

## Architecture

MVVM + Repository pattern, single `:app` module.

**Layer responsibilities:**
- `entity/` — Room entities and data models (`Planet`, `Pizza`, `Order`, `OrderPizzaCrossRef`)
- `data/` — Repository interfaces + `Offline*` implementations backed by Room DAOs; `worker/` contains WorkManager workers that simulate order state transitions
- `di/AppModule.kt` — Koin DI configuration (all ViewModels, repositories, and database wired here)
- `ui/screen/` — One package per screen, each containing a Composable and ViewModel
- `ui/navigation/PizzaPlanetsNavHost.kt` — Type-safe navigation using kotlinx.serialization route objects

**Order state machine (WorkManager):**
`PENDING → CONFIRMED → COOKING → DELIVERING → COMPLETED`
Each state is handled by a separate Worker class in `data/worker/`.

**Navigation flow:**
`HomeScreen → PlanetDetailScreen(planetId) → ReviewOrderScreen(planetId, selectedPizzaIds) → OrderListScreen`

## Key Tech

- **UI**: Jetpack Compose + Material 3
- **DI**: Koin 4.x (with ViewModel and WorkManager integration)
- **Database**: Room with pre-populated SQLite DB from `assets/database/pizza_planets_database.db`
- **Navigation**: AndroidX Navigation Compose with type-safe serializable routes
- **Background**: WorkManager for async order processing
- **Testing**: MockK + coroutines-test for unit tests; Compose UI Test + Espresso for instrumented tests
- **Build**: Gradle Kotlin DSL, version catalog at `gradle/libs.versions.toml`, KSP for Room codegen
