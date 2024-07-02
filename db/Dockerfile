FROM postgres:latest
ENV POSTGRES_USER song_service
ENV POSTGRES_PASSWORD root123
ENV POSTGRES_DB song
COPY init.sql /docker-entrypoint-initdb.d/

# docker build -t songservicedb ./
# docker run -d --name songservicedb -p 5434:5432 songservicedb