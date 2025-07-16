import org.gradle.api.tasks.Exec

tasks.register("port") {
    dependsOn("portLanguages")
    dependsOn("portThemes")
}

// Definisikan task custom dengan tipe Exec
tasks.register<Exec>("nodeHighlight") {
    group = "build"
    description = "Run node highlight.js"
    workingDir = file("tool/js")
    commandLine("node", "highlight.js")
}

tasks.register<Exec>("dartFormatLanguages") {
    group = "build"
    description = "Format Dart language files"
    commandLine(
        "dart",
        "format",
        "highlighting/lib/languages",
        "highlighting/lib/src/common_modes.g.dart",
        "highlighting/lib/src/languages/mathematica_symbols.g.dart"
    )
}

tasks.register<Exec>("nodeFlutterHighlight") {
    group = "build"
    description = "Run node flutter-highlight.js"
    workingDir = file("tool/js")
    commandLine("node", "flutter-highlight.js")
}

tasks.register<Exec>("dartFormatThemes") {
    group = "build"
    description = "Format Dart theme files"
    commandLine(
        "dart",
        "format",
        "flutter_highlighting/lib/themes",
        "flutter_highlighting/lib/theme_map.dart"
    )
}

tasks.register("portLanguages") {
    dependsOn("tool:npxTsc")
    dependsOn("nodeHighlight")
    dependsOn("dartFormatLanguages")
}

tasks.register("portThemes") {
    dependsOn("tool:npxTsc")
    dependsOn("nodeFlutterHighlight")
    dependsOn("dartFormatThemes")
}