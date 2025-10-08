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
<p align="center">
  <img src="https://github.com/user-attachments/assets/a38784a1-003e-4e64-8640-cada09824872" />
</p>

## 📁 Структура приложение
<p align="center">
  <img src="https://github.com/user-attachments/assets/ad94281c-3abd-4d2d-aca4-6785280b9fca" />
</p>


## 🖼 Скриншоты


### Онбординг
<p align="center">
  <img src="https://github.com/user-attachments/assets/2007e1d5-3584-45a3-b038-9215ddba5851" width="300" alt="Экран 1">
  <img src="https://github.com/user-attachments/assets/fd0ae05d-0da7-466d-a587-36d067df941a" width="300" alt="Экран 2">
  <img src="https://github.com/user-attachments/assets/e389ceda-4453-45c9-a6b8-152c536c57c4" width="300" alt="Экран 3">
</p>

### Основные экраны
| HomeScreen | NewHabitScreen | HabitInfoScreen |
|---------------|------------|---------|
| <img src="https://github.com/user-attachments/assets/d6b89fdc-afe3-43ac-a804-46cf18ae33db" width="300"> | <img src="https://github.com/user-attachments/assets/e6939e19-2e63-4bb6-a715-69a20618d47f" width="300"> | <img src="https://github.com/user-attachments/assets/90ac5a02-c05e-4ea6-bf83-e01ae80b969d" width="300"> |
| <img src="https://github.com/user-attachments/assets/a8f25241-bc5b-455e-bca8-d425c71457ae" width="300"> | <img src="https://github.com/user-attachments/assets/c05e12a1-25fa-4ce0-ac55-0afc335cb39a" width="300"> | <img src="https://github.com/user-attachments/assets/b82f5e1b-781f-4646-9ee1-774bcc682170" width="300"> |
| <img src="https://github.com/user-attachments/assets/5426af46-e5b0-4d64-9793-e6f065efc5b6" width="300"> | <img src="https://github.com/user-attachments/assets/dca3df32-f286-45a9-b750-9cca67a7e74b" width="300"> | <img src="https://github.com/user-attachments/assets/f9a24582-fc58-48f7-9a4e-5f8bbf2361fa" width="300"> |
| <img src="https://github.com/user-attachments/assets/cdcbd46b-2ba0-4969-aa41-31a2d4d3e1cf" width="300"> | <img src="https://github.com/user-attachments/assets/c750c1a3-fed9-4457-9d86-e5813ea88a22" width="300"> | <img src="https://github.com/user-attachments/assets/bb32a799-5106-447b-8b76-7834d38ef7e5" width="300"> |


### Log Detekt
<p align="start">
  <img src="https://github.com/user-attachments/assets/3e9068b3-3ee6-4764-b6a2-033f942eff80" />
</p>


### Log Unit Test
<p align="start">
  <img src="https://github.com/user-attachments/assets/5b1ca507-f599-4746-9b96-db70a6eff238" />
  <img src="https://github.com/user-attachments/assets/1bc54411-3afd-4fa9-921b-dff0cc82f774" />
  <img src="https://github.com/user-attachments/assets/f026c2d4-4caa-4bf6-b2c1-b98d3a7d6c42" />
  <img src="https://github.com/user-attachments/assets/c4dd89f9-692c-474e-b183-21b685245d6c" />
  <img src="https://github.com/user-attachments/assets/c04db9e6-1f45-472d-bca3-843b2dd53a94" />
</p>


### Log UI Test
<p align="start">
  <img src="https://github.com/user-attachments/assets/b6423a1e-b9e3-4b1b-89a7-c1404add62b1" />
</p>


### Log VM Test
<p align="start">
  <img src="https://github.com/user-attachments/assets/91f33536-e8a0-4ab1-a233-c1be973aba3f" />
</p>


## 🌟 API Мотивационных цитат

### 📡 Источник цитат
Приложение использует [API-Ninjas Quotes API](https://api-ninjas.com/api/quotes) для получения случайных мотивационных цитат, которые отображаются на главном экране.
