# MALL

## PRE RUN
```shell
  docker compose -f ./docker/docker-compose.yml up
```

## RUN ADMIN
```shell
  ./gradlew admin:bootrun
```

## RUN API
```shell
  ./gradlew api:bootrun
```


# Guava
- checkArgument → 생성자에서
- checkStatus → 각 메서드에서