# DTO Sample - Android Clean Architecture Example

A sample Android application demonstrating the **Data Transfer Object (DTO)** pattern and **Clean Architecture** principles using Kotlin, Jetpack Compose, Retrofit, and MVVM architecture.

## 📋 Overview

This project showcases how to properly separate API response models (DTOs) from domain models in an Android application. It follows Clean Architecture principles with clear separation between data, domain, and presentation layers.

## 🏗️ Architecture

The project follows **Clean Architecture** with three main layers:

```
┌─────────────────────────────────────┐
│      Presentation Layer (UI)       │
│  - MainActivity                     │
│  - QuoteViewModel                   │
│  - QuoteUIState                     │
└─────────────────────────────────────┘
              ↕
┌─────────────────────────────────────┐
│        Domain Layer                 │
│  - Quote (Domain Model)             │
└─────────────────────────────────────┘
              ↕
┌─────────────────────────────────────┐
│        Data Layer                   │
│  - QuoteDto (API Response Model)    │
│  - QuoteMapper (DTO → Domain)       │
│  - QuoteRepository                  │
│  - QuoteApi (Retrofit Interface)    │
└─────────────────────────────────────┘
```

## 📁 Project Structure

```
app/src/main/java/demo/amjadkhan/dtosample/
│
├── data/                          # Data Layer
│   ├── mapper/
│   │   └── QuoteMapper.kt         # Converts DTO to Domain Model
│   ├── remote/
│   │   ├── api/
│   │   │   └── QuoteApi.kt        # Retrofit API Interface
│   │   ├── dto/
│   │   │   └── QuoteDto.kt        # API Response Model (DTO)
│   │   └── RetrofitClient.kt      # Retrofit Configuration
│   └── repository/
│       └── QuoteRepository.kt     # Data Repository
│
├── domain/                        # Domain Layer
│   └── model/
│       └── Quote.kt               # Domain Model (Business Logic)
│
└── ui/                            # Presentation Layer
    ├── main/
    │   ├── MainActivity.kt        # Main Activity with Compose UI
    │   ├── QuoteViewModel.kt      # ViewModel (MVVM)
    │   └── QuoteUIState.kt        # UI State Sealed Class
    ├── common/
    │   └── UiExtension.kt         # Common UI Extension Functions
    └── theme/                     # Material 3 Theme
```

## 🔑 Key Concepts

### 1. DTO (Data Transfer Object)
**QuoteDto** represents the exact structure of the API response:
```kotlin
data class QuoteDto(
    val id: Int,
    val quote: String,      // API uses "quote" field
    val author: String,
)
```

### 2. Domain Model
**Quote** represents the business logic model used throughout the app:
```kotlin
data class Quote(
    val id: Int,
    val text: String,       // Domain uses "text" field
    val author: String,
)
```

### 3. Mapper Pattern
**QuoteMapper** converts DTO to Domain Model, handling field name differences:
```kotlin
fun QuoteDto.toDomain(): Quote {
    return Quote(
        id = id,
        text = quote,        // Maps "quote" → "text"
        author = author
    )
}
```

### Why Use DTOs?

1. **API Independence**: Domain models don't depend on API structure
2. **Flexibility**: API can change without affecting business logic
3. **Type Safety**: Clear separation between API and domain contracts
4. **Testability**: Easy to mock DTOs for testing
5. **Maintainability**: Changes in API don't ripple through the app

## 🚀 Features

- ✅ **Clean Architecture** - Separation of concerns
- ✅ **MVVM Pattern** - ViewModel with LiveData
- ✅ **Jetpack Compose** - Modern UI toolkit
- ✅ **Retrofit** - Type-safe HTTP client
- ✅ **Coroutines** - Asynchronous operations
- ✅ **Sealed Classes** - Type-safe UI states
- ✅ **Extension Functions** - Kotlin idiomatic code

## 🛠️ Technologies Used

- **Kotlin** - Programming language
- **Jetpack Compose** - UI framework
- **Retrofit** - HTTP client
- **Gson** - JSON serialization
- **Coroutines** - Asynchronous programming
- **LiveData** - Observable data holder
- **ViewModel** - UI-related data holder
- **Material 3** - Design system

## 📱 API Endpoint

The app fetches quotes from [DummyJSON API](https://dummyjson.com/):

```
GET https://dummyjson.com/quotes/{id}
```

Example Response:
```json
{
  "id": 1,
  "quote": "The only way to do great work is to love what you do.",
  "author": "Steve Jobs"
}
```

## 🔄 Data Flow

1. **UI Layer** calls `QuoteViewModel.fetchQuote(id)`
2. **ViewModel** calls `QuoteRepository.getQuote(id)`
3. **Repository** calls `QuoteApi.getQuote(id)` → Returns `QuoteDto`
4. **Mapper** converts `QuoteDto` → `Quote` (Domain Model)
5. **ViewModel** updates `QuoteUIState` with result
6. **UI** observes state and updates Compose UI

## 📝 Usage Example

```kotlin
// In ViewModel
fun fetchQuote(id: Int) {
    _state.value = QuoteUIState.Loading
    
    viewModelScope.launch {
        try {
            val quote = repository.getQuote(id)
            _state.value = QuoteUIState.Success(quote)
        } catch (e: Exception) {
            _state.value = QuoteUIState.Error("Error: ${e.message}")
        }
    }
}

// In Activity/Composable
val uiState = viewModel.state.observeAsState()

when (val state = uiState.value) {
    is QuoteUIState.Loading -> LoadingIndicator()
    is QuoteUIState.Success -> QuoteCard(quote = state.quote)
    is QuoteUIState.Error -> ErrorMessage(message = state.message)
}
```

## 🧪 Testing Strategy

### Unit Tests
- Test mapper functions (DTO → Domain)
- Test ViewModel logic
- Test repository methods

### Integration Tests
- Test API calls with mock responses
- Test data flow through layers

### UI Tests
- Test Compose UI components
- Test state changes

## 🔮 Future Enhancements

- [ ] Add caching with Room Database
- [ ] Implement pagination for quotes list
- [ ] Add offline support
- [ ] Add unit tests and integration tests
- [ ] Implement dependency injection (Hilt/Koin)
- [ ] Add more API endpoints (users, products, etc.)
- [ ] Implement error handling strategies
- [ ] Add loading animations
- [ ] Support for multiple quote categories

## 📚 Learning Resources

- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Android Architecture Components](https://developer.android.com/topic/architecture)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Retrofit Documentation](https://square.github.io/retrofit/)

## 👤 Author

**Amjad Khan**

## 📄 License

This project is for educational purposes.

---

## 🎯 Key Takeaways

1. **Always separate API models (DTOs) from domain models**
2. **Use mappers to convert between layers**
3. **Keep domain models independent of external APIs**
4. **Follow Clean Architecture principles**
5. **Use sealed classes for type-safe state management**

