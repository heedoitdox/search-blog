## QuickStart

1. jar 파일 다운로드: https://github.com/heedoitdox/search-blog/blob/main/search-blog-service-0.0.1-SNAPSHOT.jar

2. 실행

```
java -jar search-blog-service-0.0.1-SNAPSHOT.jar
```


## API 문서
application 실행 후 `/docs/index.html` 에서 조회
<img width="1352" alt="image" src="https://user-images.githubusercontent.com/73086575/226932960-cee5320b-d266-4961-96d1-25aa0cde19e4.png">

## 개발 환경

- IDE: Intellij IDEA 
- gradle: `gradle-6.9.2-bin`
- java: `Java 11`
- spring boot: `2.7.9`

## 사용 라이브러리

- spring-boot-starter-validation
   - 필드 유효성 검사용 라이브러리입니다.
- spring-cloud-starter-openfeign
   - 선언적인 HTTP Client 도구로써, 외부 API 호출을 쉽게할 수 있도록 해줍니다. Open API 호출용으로 사용합니다.
- spring-restdocs-asciidoctor
   - API 문서화를 위해 사용합니다.
- kotest
   - kotlin 환경에서 테스트 도구로 사용합니다.
- wiremock
   - Open API 테스트 시 실제 서버가 아닌 모의 서버로 테스트 하기 위해 사용합니다.
