#!/bin/bash

# Stop all running containers
docker-compose down

# # Remove all containers
# docker-compose rm -f

# # Remove all images related to the project
# docker rmi $(docker images -a -q)

# # Remove all dangling images, volumes, networks, and build cache
# docker system prune -f --volumes

# Build without cache
docker-compose build --no-cache

# Start the containers
docker-compose up -d

# Check Jenkins master logs
docker logs jenkins_master
