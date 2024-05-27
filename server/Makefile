# Define variables
BUILDER_NAME=bikeinfo-app
CONTAINER_NAME=bikeinfo-runtime
RUNTIME_NAME=bikeinfo
CONTAINER_RUNTIME_NAME=bikeinfo-running

docker-build:
	sudo docker build -t bikeinfo-app .

app-copy:
	mkdir -p ./dist/
	sudo docker cp bikeinfo-app:/app/bin/out.jar ./dist

app-build: docker-build app-copy

# Build the Docker image
build:
	sudo docker build -f Dockerfile.builder -t $(BUILDER_NAME) .

# Run the container in the background
run-builder:
	sudo docker run -d --name $(CONTAINER_NAME) $(BUILDER_NAME) tail -f /dev/null

# Copy a file from the container to the host
copy:
	mkdir -p ./dist
	sudo docker cp $(CONTAINER_NAME):/app/bin/out.jar ./dist

# Stop and remove the container
clean:
	sudo docker stop $(CONTAINER_NAME)
	sudo docker rm $(CONTAINER_NAME)

# Makefile default command
dist: build run-builder copy clean

build-app:
	sudo docker rmi $(RUNTIME_NAME) && \
	sudo docker build -f Dockerfile.app -t $(RUNTIME_NAME) .

run-app:
	sudo docker run --rm -p 8080:8080 -d --name $(CONTAINER_RUNTIME_NAME) $(RUNTIME_NAME)

stop-app:
	sudo docker stop $(CONTAINER_RUNTIME_NAME)

shell:
	sudo docker exec -it $(CONTAINER_RUNTIME_NAME) /bin/sh
