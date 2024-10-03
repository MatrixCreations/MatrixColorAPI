# 🌈 MatrixColorAPI

**MatrixColorAPI** is a powerful and flexible library for processing and applying custom colors, gradients, and text decorations in Minecraft servers. It supports hex colors, legacy Minecraft color codes, and more advanced formatting like gradients and solid colors.

This library simplifies color management while allowing developers to build beautifully formatted text with ease!

## ✨ Features

- 🎨 **Hex and Legacy Color Code Support**
- 🌈 **Gradients**: Apply smooth color transitions across text.
- 🟡 **Solid Colors**: Easily apply static colors to any text.
- 🖋️ **Text Decorations**: Support for bold, italic, underlined, strikethrough, and obfuscated text styles.
- 🚀 **Lightweight**: Minimal impact on performance.
- 🔄 **Easy to Use**: Clean and simple API to integrate into your project.
- 🌍 **Supports All Server Software**: Compatible with Spigot, Paper, Bukkit, Minestom, Fabric, and more.
- 🕹️ **Works with Every Minecraft Version Starting from 1.16.5**.
- 🛠️ **Open to Contributions**: Licensed under Apache 2.0 License for open contributions, modifications, and commercial use!

## 🚀 Getting Started

### Installation

![API Version](https://img.shields.io/jitpack/v/MatrixCreations/MatrixColorAPI.svg?color=512BD4&label=API%20Version&style=for-the-badge)

MatrixColorAPI is hosted on [Jitpack](https://jitpack.io), making it easy to integrate with your Maven or Gradle projects. Follow the steps below to add it to your project:

### Using Gradle
1. **Add the Jitpack repository** to your `build.gradle` file (inside `repositories` block):
   ```gradle
   repositories {
       maven { url 'https://jitpack.io' }
   }
   ```

2. **Add the dependency** in your `dependencies` block:
   ```gradle
   dependencies {
       implementation 'com.github.MatrixCreations:MatrixColorAPI:v1.0.6'
   }
   ```

### Using Maven
1. **Add the Jitpack repository** to your `pom.xml`:
   ```xml
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
   ```

2. **Add the dependency** in your `dependencies` block:
   ```xml
   <dependency>
       <groupId>com.github.MatrixCreations</groupId>
       <artifactId>MatrixColorAPI</artifactId>
       <version>v1.0.6</version>
   </dependency>
   ```

### Additional Information

- For the latest version, please scroll up to know the latest api version.
- Ensure you're using Java 16 or higher to avoid compatibility issues.

### Example Usages

- **Solid Color Example:**

```java
String text = "<SOLID:#FFD700>This is yellow text";
String processed = MatrixColorAPI.process(text);
```

- **Gradient Example:**

```java
String gradientText = "<GRADIENT:#FF0000>This is a red to yellow gradient</GRADIENT:#FFFF00>";
String processedGradient = MatrixColorAPI.process(gradientText);
```

- **Legacy Code Support:**

```java
String legacyText = "&aThis is a green text with &lBOLD";
String processedLegacy = MatrixColorAPI.process(legacyText);
```

- **Hex Code Support:**

```java
String hexText = "&#FFD700This is a yellow text with &lBOLD";
String processedLegacy = MatrixColorAPI.process(hexText);
```

## 🛠️ Available Methods

- `process(String text)`: Process a single string for color codes, gradients, and formatting.
- `process(List<String> texts)`: Process a list of strings for color codes and formatting.

## 📦 Contributing

We welcome contributions! To get started:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Submit a pull request with a clear explanation of your changes.

Make sure to follow the coding standards and include tests where appropriate.

## 📝 License

This project is licensed under the **Apache 2.0 License**. See the [LICENSE](./LICENSE) file for details.

## 📢 Acknowledgments

Thanks to everyone contributing to this project! Your support and feedback help make **MatrixColorAPI** even better.

## 👥 Contact

For any questions or feedback, feel free to open an issue or reach out.
