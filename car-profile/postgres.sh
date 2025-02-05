docker run --rm  --name carsystem-db \
-p 5434:5432 \
-e POSTGRES_DB=carsystem-db \
-e POSTGRES_USER=sa \
-e POSTGRES_PASSWORD=123 \
-e PGDATA=/var/lib/postgresql/data/pgdata \
-v /home/containers/carsystem-db/mount:/var/lib/postgresql/data \
-d postgres