<h1 align="center">Hola!! 👋🏾</h1>

###

<h3 align="left">Soy Mateo Arroyave</h3>

###

<h2 align="left">Estudiante del Cesde y practicante de TCC</h2>

###

<p align="left">✨ Creando errores desde el 2023<br>📚Actualmente estoy aprendiendo Java y JavaScript <br>🎯 Me gustaría aprender Backend y poder trabajar de mi pasión <br>🎲 Desde muy pequeño me ha gustado todo el tema de la programación y en algún punto me gustaría aprender ciberseguridad</p>

###

<h2 align="left">Este Repo esta hecho especialmente con Java</h2>

###

<div align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kotlin/kotlin-original.svg" height="30" alt="kotlin logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="30" alt="java logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" height="30" alt="docker logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg" height="30" alt="git logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/github/github-original.svg" height="30" alt="github logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/html5/html5-original.svg" height="30" alt="html5 logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/typescript/typescript-original.svg" height="30" alt="typescript logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/javascript/javascript-original.svg" height="30" alt="javascript logo"  />
  <img width="12" />
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/css3/css3-original.svg" height="30" alt="css3 logo"  />
</div>

###

<h3 align="left">Archivos</h3>


# AppScanner

## Descripción
AppScanner es una aplicación Android diseñada para escanear códigos de barras y códigos QR utilizando la cámara del dispositivo o una imagen seleccionada desde la galería. Los resultados del escaneo se formatean y se muestran en una actividad separada.

## Características

1. **Escaneo de códigos de barras y QR**: Utiliza la cámara del dispositivo para escanear.
2. **Escaneo desde imágenes**: Permite seleccionar una imagen de la galería y escanear el código de barras o QR contenido en ella.
3. **Formato de resultado**: Los resultados del escaneo se formatean para una mejor presentación.
4. **Navegación de resultados**: Muestra los resultados en una nueva actividad.

## Estructura del Código

### `MainActivity`
1. **Configuración del binding**:
   ```kotlin
   private lateinit var binding: ActivityMainBinding
   ```
2. **Configuración de lanzadores de actividades**:
   ```kotlin
   private lateinit var scanLauncher: ActivityResultLauncher<ScanOptions>
   private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>
   ```
3. **Método `onCreate`**:
    - Inicializa el binding y configura los botones de escaneo.
    - Configura los lanzadores de actividades para escanear con la cámara y seleccionar imágenes de la galería.

4. **Método `navigateToResult`**:
    - Navega a `ResultScannerActivity` con el resultado escaneado.

5. **Método `initScanner`**:
    - Configura las opciones de escaneo y lanza el escáner.

6. **Método `selectImageFromGallery`**:
    - Lanza la galería de imágenes para seleccionar una.

7. **Método `scanImage`**:
    - Procesa la imagen seleccionada y escanea el código de barras o QR en ella.

8. **Método `formatResult`**:
    - Formatea el resultado del escaneo para una mejor presentación.

## Ejemplo de Formateo de Resultados

```kotlin
private fun formatResult(result: String): String {
    var formattedResult = result.trim()
    formattedResult = formattedResult.replace("\n", " ")
    val parts = formattedResult.split(":")
    if (parts.size >= 5) {
        val code = parts[0]
        val idNumber = parts[1]
        val uniqueId = parts[2]
        val issueDate = parts[3]
        val expiryDate = parts[4]
        val additionalData = parts[5]
        formattedResult = """
            Placa: $code
            Número de Identificación: $idNumber
            Cedula: $uniqueId
            Fecha de expedicion: $issueDate
            Fecha de Expiración: $expiryDate
            Datos Adicionales: $additionalData
        """.trimIndent()
    }
    return formattedResult
}
```

## Dependencias
- ZXing Library: Utilizada para la funcionalidad de escaneo de códigos de barras y QR.

## Cómo Usar

1. **Escanear con la cámara**:
    - Presiona el botón de escanear para iniciar la cámara y escanear un código de barras o QR.

2. **Escanear desde una imagen**:
    - Presiona el botón de seleccionar imagen para elegir una imagen de la galería y escanear el código contenido en ella.

## Contribuciones
Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request para sugerencias y mejoras.


