# springboot

## Version
- springboot 3.2.5
- java 21

## Description
- Springboot + Spring Data Jpa Demo Application

### 1. 사전 준비
- Docker 설치

### 2. 테스트 방법

#### docker compose 명령어 실행
- container 실행
```shell
docker compose up -d
```

- 실행 확인
```shell
# 실행 확인
docker ps
docker images
docker volume ls
docker network ls
```

- container 종료
```shell
docker-compose down
```

#### http 테스트
- [API 테스트 정상 확인 - test.http](./src/test/test.http)
