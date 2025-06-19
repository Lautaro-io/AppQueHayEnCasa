# 🏠 ¿Qué Hay En Casa?

**¿Qué Hay En Casa?** es una app Android desarrollada en Jetpack Compose que permite llevar el control de los productos que tenés en casa, con categorías, fechas de vencimiento y filtros personalizados. Ideal para reducir el desperdicio de comida o simplemente organizar tu casa.

<div align="center">
  <img src="![image](https://github.com/user-attachments/assets/c1e19358-a5c2-4d3c-bb96-d6cb8f61ee3e)"/>
  <img src="![image](https://github.com/user-attachments/assets/d3d8309c-3da2-4960-a9e5-6310c739e8fc)"/>
  <img src="![image](https://github.com/user-attachments/assets/d5700516-921c-421d-9dfa-26da8158ee2d)"/>
</div>

---

## ✨ Características

- 📋 Agregá productos con nombre, categoría y fecha de vencimiento.
- 🗂️ Clasificá productos por categoría.
- 📅 Visualizá qué productos están por vencer.
- 🔍 Filtrá productos por categoría.
- 💥 Eliminá productos o categorías fácilmente.
- 🎨 Interfaz moderna hecha 100% en Jetpack Compose.
- 🔔 Notificaciones para vencimientos próximos.

---

## 🧠 Arquitectura

La app sigue el patrón **MVVM (Model-View-ViewModel)** utilizando:

- `ViewModel` para lógica de negocio.
- `Repository` para acceso a datos.
- `StateFlow` y `Flow` para manejo de estado reactivo.
- `Hilt` para inyección de dependencias.

---

## 🛠️ Tech Stack

| Herramienta        | Descripción                            |
|--------------------|----------------------------------------|
| Kotlin             | Lenguaje principal                     |
| Jetpack Compose    | UI moderna y declarativa               |
| Room               | Persistencia local                     |
| Hilt               | Inyección de dependencias              |
| Kotlin Coroutines  | Programación asíncrona                 |
| Navigation Compose | Manejo de navegación                   |
| StateFlow / Flow   | Flujos de datos reactivos              |
| WorkManager        | Notificaciones persistentes            |
| Pager              | Onboarding screen                      |
| DataStore          | Control de OnboardingScreen            |
| CameraX            | Manejo de Hardware(Camera)             |


---

## 🚀 Instalación

1. Cloná este repositorio:
   ```bash
   git clone https://github.com/Lautaro-io/AppQueHayEnCasa.git
Abrilo en Android Studio (Flamingo o más reciente).

Asegurate de tener configurado el entorno Android correctamente.

Ejecutá la app en un emulador o dispositivo físico.

🧬 Estructura del Proyecto

├── data/
│   ├── entities/
│   ├── repository/
│   └── db/
├── ui/
│   ├── screens/
│   ├── components/
│   └── theme/
├── viewmodel/
├── navigation/
└── model/
📌 Próximas mejoras
📱 Adaptación a pantallas grandes y tablets.

☁️ Sincronización en la nube.



🛒 Lista de compras automática con productos que faltan.

🤝 Contribuciones
¿Querés colaborar? ¡Toda ayuda es bienvenida!

Hacé un fork del proyecto

Creá una nueva rama (git checkout -b feature/nueva-feature)

Hacé commit de tus cambios (git commit -m 'Agregada nueva feature')

Push a la rama (git push origin feature/nueva-feature)

Abrí un Pull Request

📄 Licencia
Este proyecto está licenciado bajo la MIT License.

📬 Contacto
Desarrollado por Lautaro.
