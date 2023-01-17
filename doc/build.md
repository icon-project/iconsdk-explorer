# ICONSDK-EXPLORER

## Platform preparation

* Java

    **Mac OSX**
    ```
    OpenJDK (>= 11.0.6)
    OpenJDK Runtime Environment AdoptOpenJDK (build (>= 11.0.6))
    OpenJDK 64-Bit Server VM AdoptOpenJDK (build (>= 11.0.6), mixed mode)
    ```
  
* Node

    **Mac OSX**
    ```
    NodeJs (>= 10.0.0)
    ```
---

## 1. Frontend Build
A front end NPM build is required before the backend build.
- [Fruntend Build Guide](../frontend/README.md)

1. CLI
```bash
- cd ${PROJECT_DIR}/frontend
- ./gradlew build
```
2. intellij
```bash
ui > gradle > Tasks > build > build
```
Output binaries are placed under `${PROJECT_DIR}/frontend/target/` directory.
## 2. Backend Build

### Config
Change the configuration before building.
-  [Config Guide](config.md)

### Build executables

1. CLI
```bash
cd ${PROJECT_DIR}/build
./gradlew -Pversion=$(git describe --tags $(git rev-list --tags --max-count=1)) build
```
2. intellij
```bash
backend > gradle > Tasks > build > build
```
`/Build` directory is created when the build is completed

## 3. Docker Build

1. build
```bash
cd ${PROJECT_DIR}/
docker build --build-arg VERSION=0.0.1 --tag iconsdk-explorer:${VERSION} .
```


## Quick start

1. CLI
```bash
cd ${PROJECT_DIR}/build
./gradlew bootRun
```
2. intellij
```bash
gradle > Tasks > application > bootRun
```

## Tutorial
-  [Tutorial](../backend/README.md)





