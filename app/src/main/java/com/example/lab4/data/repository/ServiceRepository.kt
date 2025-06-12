package com.example.lab4.data.repository

import com.example.lab4.data.dao.ServiceDao
import com.example.lab4.data.model.Service
import com.example.lab4.data.model.ServiceCategory
import kotlinx.coroutines.flow.Flow

class ServiceRepository(private val serviceDao: ServiceDao) {
    fun getAllServices(): Flow<List<Service>> = serviceDao.getAllServices()

    fun getServicesByCategory(category: ServiceCategory): Flow<List<Service>> =
        serviceDao.getServicesByCategory(category)

    fun searchServices(query: String): Flow<List<Service>> = serviceDao.searchServices(query)

    fun getServicesByPriceAsc(): Flow<List<Service>> = serviceDao.getServicesByPriceAsc()

    fun getServicesByPriceDesc(): Flow<List<Service>> = serviceDao.getServicesByPriceDesc()

    fun getServicesByRating(): Flow<List<Service>> = serviceDao.getServicesByRating()

    suspend fun insertService(service: Service) = serviceDao.insertService(service)

    suspend fun insertServices(services: List<Service>) = serviceDao.insertServices(services)

    suspend fun updateService(service: Service) = serviceDao.updateService(service)

    suspend fun deleteService(service: Service) = serviceDao.deleteService(service)

    suspend fun deleteAllServices() = serviceDao.deleteAllServices()

    suspend fun insertInitialData() {
        val services = listOf(
            Service(
                name = "Репетитор по математике",
                description = "Опытный преподаватель с 10-летним стажем. Подготовка к ЕГЭ, ОГЭ, помощь с домашними заданиями.",
                category = ServiceCategory.TUTORING,
                pricePerHour = 1500.0,
                rating = 4.8f,
                imageUrl = "https://example.com/math.jpg",
                providerName = "Иванов Иван",
                providerPhone = "+7 (999) 123-45-67"
            ),
            Service(
                name = "Ремонт компьютеров",
                description = "Профессиональный ремонт компьютеров и ноутбуков. Диагностика, чистка, замена комплектующих.",
                category = ServiceCategory.REPAIR,
                pricePerHour = 2000.0,
                rating = 4.9f,
                imageUrl = "https://example.com/computer.jpg",
                providerName = "Петров Петр",
                providerPhone = "+7 (999) 234-56-78"
            ),
            Service(
                name = "Уборка квартиры",
                description = "Генеральная и поддерживающая уборка квартир. Использование профессиональной химии.",
                category = ServiceCategory.CLEANING,
                pricePerHour = 800.0,
                rating = 4.7f,
                imageUrl = "https://example.com/cleaning.jpg",
                providerName = "Сидорова Анна",
                providerPhone = "+7 (999) 345-67-89"
            ),
            Service(
                name = "Маникюр и педикюр",
                description = "Профессиональный маникюр и педикюр. Дизайн ногтей, наращивание.",
                category = ServiceCategory.BEAUTY,
                pricePerHour = 1200.0,
                rating = 4.9f,
                imageUrl = "https://example.com/nails.jpg",
                providerName = "Козлова Мария",
                providerPhone = "+7 (999) 456-78-90"
            ),
            Service(
                name = "Персональный тренер",
                description = "Индивидуальные тренировки в зале. Составление программы питания и тренировок.",
                category = ServiceCategory.FITNESS,
                pricePerHour = 2500.0,
                rating = 4.8f,
                imageUrl = "https://example.com/fitness.jpg",
                providerName = "Смирнов Алексей",
                providerPhone = "+7 (999) 567-89-01"
            ),
            Service(
                name = "Разработка сайтов",
                description = "Создание сайтов любой сложности. Frontend и backend разработка.",
                category = ServiceCategory.IT,
                pricePerHour = 3000.0,
                rating = 4.9f,
                imageUrl = "https://example.com/web.jpg",
                providerName = "Кузнецов Дмитрий",
                providerPhone = "+7 (999) 678-90-12"
            ),
            Service(
                name = "Дизайн интерьера",
                description = "Проектирование и дизайн интерьеров. 3D визуализация.",
                category = ServiceCategory.DESIGN,
                pricePerHour = 2800.0,
                rating = 4.8f,
                imageUrl = "https://example.com/design.jpg",
                providerName = "Новикова Елена",
                providerPhone = "+7 (999) 789-01-23"
            ),
            Service(
                name = "Такси",
                description = "Пассажирские перевозки по городу и области. Комфортные автомобили.",
                category = ServiceCategory.TRANSPORT,
                pricePerHour = 1000.0,
                rating = 4.7f,
                imageUrl = "https://example.com/taxi.jpg",
                providerName = "Морозов Сергей",
                providerPhone = "+7 (999) 890-12-34"
            ),
            Service(
                name = "Курсы английского",
                description = "Индивидуальные и групповые занятия английским языком. Подготовка к международным экзаменам.",
                category = ServiceCategory.EDUCATION,
                pricePerHour = 1800.0,
                rating = 4.9f,
                imageUrl = "https://example.com/english.jpg",
                providerName = "Волкова Ольга",
                providerPhone = "+7 (999) 901-23-45"
            ),
            Service(
                name = "Массаж",
                description = "Лечебный и расслабляющий массаж. Выезд на дом.",
                category = ServiceCategory.HEALTH,
                pricePerHour = 2200.0,
                rating = 4.8f,
                imageUrl = "https://example.com/massage.jpg",
                providerName = "Лебедев Андрей",
                providerPhone = "+7 (999) 012-34-56"
            ),
            Service(
                name = "Юридические консультации",
                description = "Консультации по гражданскому, семейному и трудовому праву.",
                category = ServiceCategory.LEGAL,
                pricePerHour = 3500.0,
                rating = 4.9f,
                imageUrl = "https://example.com/law.jpg",
                providerName = "Соколов Игорь",
                providerPhone = "+7 (999) 123-45-67"
            ),
            Service(
                name = "Ремонт квартир",
                description = "Капитальный и косметический ремонт квартир. Отделочные работы.",
                category = ServiceCategory.CONSTRUCTION,
                pricePerHour = 2500.0,
                rating = 4.7f,
                imageUrl = "https://example.com/repair.jpg",
                providerName = "Попов Николай",
                providerPhone = "+7 (999) 234-56-78"
            ),
            Service(
                name = "Ландшафтный дизайн",
                description = "Проектирование и благоустройство садовых участков.",
                category = ServiceCategory.GARDENING,
                pricePerHour = 2000.0,
                rating = 4.8f,
                imageUrl = "https://example.com/garden.jpg",
                providerName = "Васильева Татьяна",
                providerPhone = "+7 (999) 345-67-89"
            ),
            Service(
                name = "Дрессировка собак",
                description = "Профессиональная дрессировка собак. Коррекция поведения.",
                category = ServiceCategory.PETS,
                pricePerHour = 1800.0,
                rating = 4.9f,
                imageUrl = "https://example.com/dog.jpg",
                providerName = "Козлов Михаил",
                providerPhone = "+7 (999) 456-78-90"
            ),
            Service(
                name = "Фотосессия",
                description = "Семейная, свадебная, детская фотосессия. Обработка фотографий.",
                category = ServiceCategory.PHOTOGRAPHY,
                pricePerHour = 3000.0,
                rating = 4.8f,
                imageUrl = "https://example.com/photo.jpg",
                providerName = "Иванова Анастасия",
                providerPhone = "+7 (999) 567-89-01"
            ),
            Service(
                name = "Кулинарные курсы",
                description = "Индивидуальные и групповые кулинарные мастер-классы.",
                category = ServiceCategory.COOKING,
                pricePerHour = 2000.0,
                rating = 4.9f,
                imageUrl = "https://example.com/cooking.jpg",
                providerName = "Петрова Екатерина",
                providerPhone = "+7 (999) 678-90-12"
            ),
            Service(
                name = "Курсы китайского языка",
                description = "Индивидуальные занятия китайским языком. Подготовка к HSK.",
                category = ServiceCategory.LANGUAGE,
                pricePerHour = 2500.0,
                rating = 4.8f,
                imageUrl = "https://example.com/chinese.jpg",
                providerName = "Чжан Ли",
                providerPhone = "+7 (999) 789-01-23"
            ),
            Service(
                name = "Уроки фортепиано",
                description = "Индивидуальные уроки игры на фортепиано для детей и взрослых.",
                category = ServiceCategory.MUSIC,
                pricePerHour = 1800.0,
                rating = 4.9f,
                imageUrl = "https://example.com/piano.jpg",
                providerName = "Смирнова Юлия",
                providerPhone = "+7 (999) 890-12-34"
            ),
            Service(
                name = "Танцевальная студия",
                description = "Групповые занятия по различным направлениям танцев.",
                category = ServiceCategory.DANCE,
                pricePerHour = 1200.0,
                rating = 4.7f,
                imageUrl = "https://example.com/dance.jpg",
                providerName = "Кузнецова Анна",
                providerPhone = "+7 (999) 901-23-45"
            ),
            Service(
                name = "Йога",
                description = "Групповые и индивидуальные занятия йогой. Медитации.",
                category = ServiceCategory.YOGA,
                pricePerHour = 1500.0,
                rating = 4.8f,
                imageUrl = "https://example.com/yoga.jpg",
                providerName = "Ом Намасте",
                providerPhone = "+7 (999) 012-34-56"
            )
        )
        insertServices(services)
    }
} 