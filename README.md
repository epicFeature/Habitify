# Habitify - трекер полезных привычек

<p align="center">
  <img src="app/src/main/ic_launcher-playstore.png" width="200" alt="Логотип приложения">
</p>

## 📱 Основной функционал
🎯 **Создание привычек**  
Легко добавляйте новые привычки, задавая им название, цвет и продолжительность (в днях)

📊 **Отслеживание прогресса**  
Отмечайте выполнение привычек на главном экране. Интерактивная сетка показывает ваш прогресс за последние 5 дней

💫 **Мотивация**  
Получайте вдохновляющие цитаты на главном экране, чтобы сохранять мотивацию

🎨 **Визуализация**  
Наглядное представление привычек и их выполнения с помощью цветовой кодировки

🎉 **Поздравления**  
Приложение поздравит вас, когда вы успешно завершите формирование привычки!


## 🛠 Архитектура и стек технологий

### ✅ Реализованные требования

| Требование | Статус |
|------------|--------|
| **Single Activity + Jetpack Compose** | ✅ |
| **Многомодульность** | ✅ |
| **MVI (самописный) + ViewModel** | ✅ |
| **Слоистая архитектура Data/Domain/Presentation** | ✅|
| **DI - Hilt** | ✅ |
| **Coroutines, Flow** | ✅ |
| **Retrofit + Gson** | ✅ |
| **Room + DataStore** | ✅ |
| **5 Unit Tests + 1 VM Test + 1 UI Test** | ✅ |
| **Статический анализатор Detekt** | ✅ |


## 📁 Структура модулей
<p align="start">
  <img src="screenshots/onboarding_1.png" width="600" alt="Структура модулей">
</p>


## 🖼 Скриншоты

### Онбординг
<p align="start">
  <img src="screenshots/onboarding_1.png" width="200" alt="Экран 1">
  <img src="screenshots/onboarding_2.png" width="200" alt="Экран 2">
  <img src="screenshots/onboarding_3.png" width="200" alt="Экран 3">
</p>

### Основные экраны
| HomeScreen | NewHabitScreen | HabitInfoScreen |
|---------------|------------|---------|
| <img src="screenshots/main_dark.png" width="200"> | <img src="screenshots/stats.png" width="200"> | <img src="screenshots/profile.png" width="200"> |
| <img src="screenshots/main_light.png" width="200"> | <img src="screenshots/stats_detailed.png" width="200"> | <img src="screenshots/settings.png" width="200"> |


## 🌟 API Мотивационных цитат

### 📡 Источник цитат
Приложение использует [API-Ninjas Quotes API](https://api-ninjas.com/api/quotes) для получения случайных мотивационных цитат, которые отображаются на главном экране.
