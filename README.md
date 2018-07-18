# lazybones-maven-layered
A lazybones template for creating new Java projects with maven.  It creates several Maven modules for a layered build structure.

### NOTE: This is under heavy development and not for general use.

### Install template locally
./gradlew installAllTemplates

### Test
rm -rf "my-proj/" && lazybones create maven-layered 1.0-SNAPSHOT my-proj