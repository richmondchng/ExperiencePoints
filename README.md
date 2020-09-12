# Experience Points

To setup application
1. Clone repositiory to local directory (I will refer to the root of the repository as `root`)
2. From `root`, navigate to `xpmanagement` directory.
   - `cd xpmanagement`
3. Run command to build Spring Boot Jar file
   - `.\mvnw clean install`
4. When completed, navigate back to the `root` directory.
   - `cd ..`
5. Run Docker compose to build image and launch containers.
   - `docker-compose up`
6. The application can be accessed on port `8080`
   - `http://localhost:8080`
7. The Postgres instance can be accessed on port `5432`
