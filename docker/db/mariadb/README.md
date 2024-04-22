# mariadb

## Version - LTS 10.11.7

## Description
- docker compose 를 활용하여 mariadb 를 구축합니다.

### 1. 사전 준비
- Docker 설치

### 2. 테스트 방법
#### docker compose 명령어 실행

- container 실행
```shell
docker-compose up -d
```
- 실행 확인
```shell
# 실행 확인
docker ps
docker images
docker volume ls
docker network ls
```
- container 종료 (바인딩 볼륨이 아닌 익명 또는 네임드 볼륨 사용 시 -v 옵션 선택)
```shell
docker-compose down
```

#### 아래 경로 접속 후 정상적으로 접속되는지 확인
- http://localhost:18080
- 접속정보 ([docker-compose.yml](./docker-compose.yml) 파일 참고)
  - 서버: docker-compose.yml 내 mariadb service name
  - 사용자이름: root or docker-compose.yml 내 mariadb service 환경변수 MYSQL_ROOT_USER
  - 비밀번호: docker-compose.yml 내 mariadb service 환경변수 MYSQL_ROOT_PASSWORD or MYSQL_PASSWORD
  - 데이터베이스: docker-compose.yml 내 mariadb service 환경변수 MYSQL_DATABASE (root 는 공백)