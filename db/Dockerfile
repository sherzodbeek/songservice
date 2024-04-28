FROM postgres:latest
ENV POSTGRES_USER song_service
ENV POSTGRES_PASSWORD root123
ENV POSTGRES_DB song
COPY init.sql /docker-entrypoint-initdb.d/

# docker build -t songservice ./
# docker run -d --name songservice -p 5434:5432 songservice